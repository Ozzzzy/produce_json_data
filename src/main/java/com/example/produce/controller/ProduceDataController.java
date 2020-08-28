package com.example.produce.controller;

import com.example.produce.entity.ProduceData;
import com.example.produce.service.ProduceDeviceService;
import com.example.produce.service.ProduceFunctionService;
import org.apache.commons.io.FileUtils;
import com.example.produce.service.ProduceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author hou
 * @date 2020/8/24
 */
@RestController
@RequestMapping(value = "/data", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
public class ProduceDataController {

    @Autowired
    private ProduceDataService produceDataService;

    @Autowired
    private ProduceFunctionService produceFunctionService;

    @Autowired
    private ProduceDeviceService produceDeviceService;

    @Value("${uploadFilePath}")
    private String filePath;

    @RequestMapping(value = "/addData",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String addData(@RequestBody ProduceData produceData){

        try {
            return produceDataService.addData(produceData);
        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/addDataByExcel",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String addDataExcel(MultipartFile file, @RequestParam(value = "deviceId") int deviceId){
        Long dateStart = System.currentTimeMillis();
        try {
            FileUtils.writeByteArrayToFile(new File(filePath + file.getOriginalFilename()), file.getBytes());
            String fileAddress = filePath + file.getOriginalFilename();
            int produceId = produceDeviceService.detailsDevice(deviceId).getProduceId();
            HashMap<String,Integer> hashTableNameAndId = produceFunctionService.getHashTableNameAndId(produceId);
            List<ProduceData> produceDataList = ExcelReader.readExcel(fileAddress,deviceId,hashTableNameAndId);
            int batch = 5 * hashTableNameAndId.size();
            return produceDataService.batchInsert(dateStart,produceDataList,batch);

        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }
}
