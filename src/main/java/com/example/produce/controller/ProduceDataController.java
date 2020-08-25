package com.example.produce.controller;

import com.example.produce.entity.DataRequest;
import com.example.produce.entity.ProduceDevice;
import com.example.produce.entity.ProduceFunction;
import com.example.produce.service.ProduceDataService;
import com.example.produce.service.ProduceDeviceService;
import com.example.produce.service.ProduceFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author hou
 * @date 2020/8/24
 */
@RestController
public class ProduceDataController {

    @Autowired
    private ProduceDataService produceDataService;

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
}
