package com.example.produce.controller;

import com.example.produce.entity.ProduceData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Description: 读取Excel内容
 *
 * @author 学无止境~冲
 */
public class ExcelReader {

    private static Logger logger = Logger.getLogger(ExcelReader.class.getName());
    // 日志打印类

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     *
     * @param inputStream 读取文件的输入流
     * @param fileType    文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 读取Excel文件内容
     *
     * @param fileName 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public static List<ProduceData> readExcel(String fileName, int deviceId, HashMap<String,Integer> hashTableNameAndId) {

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                logger.warning("指定的Excel文件不存在！");
                return null;
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            List<ProduceData> resultDataList = excelDataList(deviceId,workbook,hashTableNameAndId);

            return resultDataList;
        } catch (Exception e) {
            logger.warning("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }

    /**
     * 将单元格内容转换为double
     *
     * @param cell
     * @return
     */
    private static double convertCellValueToDouble(Cell cell) {
        if (cell == null) {
            return 0;
        }

        return cell.getNumericCellValue();
    }

    /**
     * 时间格式转换
     * @param cell
     * @return
     */
    public static Date convertCellValueToDate(Cell cell) {
        if (cell == null || cell.toString().trim().equals("")) {
            return null;
        }
        Date cellValue = new Date();
        if (cell.getCellType() == CellType.NUMERIC) {
            // 数字
            short format = cell.getCellStyle().getDataFormat();
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = null;
                if (format == 20 || format == 32) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                } else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                try {
                    cellValue = cell.getDateCellValue();
                } catch (Exception e) {
                    try {
                        throw new Exception("exception on get date data !".concat(e.toString()));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } finally {
                    sdf = null;
                }
            }
        }
        return cellValue;
    }

    public static List<ProduceData> excelDataList(int deviceId, Workbook workbook, HashMap<String, Integer> hashTableNameAndId){
        Sheet sheet = workbook.getSheetAt(0);
        List<ProduceData> dataRequests = new ArrayList<>();
        int firstRowNum = sheet.getFirstRowNum();
        Row firstRow = sheet.getRow(firstRowNum);
        int lastRowNum = sheet.getLastRowNum();
        ProduceData produceData = new ProduceData();

        // 遍历表一的所有行
        for(int i = firstRowNum + 1 ; i < lastRowNum + 1 ; i ++){

            Cell cellFirstRow,cellRow;
            Row row = sheet.getRow(i);
            int createTimeLocation = row.getLastCellNum();

            // 遍历行的所有列
            for(int cellNum= 0 ; cellNum < createTimeLocation-1 ; cellNum++){

                cellRow = row.getCell(cellNum);
                cellFirstRow = firstRow.getCell(cellNum);
                String functionName = convertCellValueToString(cellFirstRow);
                String dataString = convertCellValueToString(cellRow);
                int functionId = hashTableNameAndId.get(functionName);
                Date createTime = convertCellValueToDate(row.getCell(createTimeLocation-1));
                dataRequests.add(new ProduceData(deviceId,functionId,dataString,createTime));
            }
        }

        return dataRequests;
    }

    /**
     * 将单元格内容转换为字符串
     *
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                //数字
                Double doubleValue = cell.getNumericCellValue();
                // 格式化科学计数法，取一位整数
                //DecimalFormat df = new DecimalFormat("0");
                returnValue = doubleValue.toString();
                break;
            case STRING:
                //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:
                // 空值
                break;
            case FORMULA:
                // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:
                // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

}