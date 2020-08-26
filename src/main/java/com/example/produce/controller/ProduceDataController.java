package com.example.produce.controller;

import com.example.produce.entity.DataRequest;
import com.example.produce.entity.ProduceData;
import com.example.produce.service.ProduceDeviceService;
import com.example.produce.service.ProduceFunctionService;
import org.apache.commons.io.FileUtils;
import com.example.produce.service.ProduceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;


/**
 * @author hou
 * @date 2020/8/24
 */
@RestController
public class ProduceDataController {

    @Autowired
    private ProduceDataService produceDataService;

    @Autowired
    private ProduceFunctionService produceFunctionService;

    @Autowired
    private ProduceDeviceService produceDeviceService;

    @RequestMapping(value = "/addData",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String addData(String jsonString){

        try {
            JsonReader jsonReader = new JsonReader();
            DataRequest dataRequest = jsonReader.getDataRequest(jsonString).get(0);
            return produceDataService.addData(dataRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/addDataExcel",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String addDataExcel(MultipartFile file, @RequestParam(value = "deviceId") int deviceId){

        try {
            FileUtils.writeByteArrayToFile(new File("D:/upload/" + file.getOriginalFilename()), file.getBytes());
            String fileAddress = "D:/upload/" + file.getOriginalFilename();
            //String fileAddress = "D:\\upload\\weather.xlsx";
            int produceId = produceDeviceService.detailsDevice(deviceId).getProduceId();
            HashMap<String,Integer> hashTableNameAndId = produceFunctionService.getHashTableNameAndId(produceId);
            List<ProduceData> produceDataList = ExcelReader.readExcel(fileAddress,deviceId,hashTableNameAndId);
            int batch = 5 * hashTableNameAndId.size();
            return produceDataService.batchInsert(produceDataList,batch);

        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }
}
