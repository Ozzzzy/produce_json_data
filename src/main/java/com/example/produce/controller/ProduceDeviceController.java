package com.example.produce.controller;

import com.example.produce.entity.DeviceRequest;
import com.example.produce.entity.ProduceData;
import com.example.produce.entity.ProduceDevice;
import com.example.produce.entity.ProduceFunction;
import com.example.produce.service.ProduceDeviceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hou
 * @date 2020/8/24
 */

@RestController
public class ProduceDeviceController {

    @Autowired
    private ProduceDeviceService produceDeviceService;

    @RequestMapping(value = "/addDevice",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String add(String jsonString)throws Exception{
        try {
            JsonReader jsonReader = new JsonReader();
            DeviceRequest deviceRequest = jsonReader.getDeviceRequest(jsonString).get(0);
            return produceDeviceService.addDevice(deviceRequest);

        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/modifyDevice",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String modify(String jsonString)throws Exception{
        try {
            JsonReader jsonReader = new JsonReader();
            DeviceRequest deviceRequest = jsonReader.getDeviceRequest(jsonString).get(0);
            return produceDeviceService.modifyDevice(deviceRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/detailsDevice",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody ProduceDevice details(int deviceId)throws Exception{
        try {
            return produceDeviceService.detailsDevice(deviceId);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/deleteDevice",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String deleteDevice(int deviceId)throws Exception{
        try {
            return produceDeviceService.deleteDevice(deviceId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/deviceList",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody List<ProduceDevice> deviceList(String jsonString)throws Exception{
        try {
            JsonReader jsonReader = new JsonReader();
            DeviceRequest deviceRequest = jsonReader.getDeviceRequest(jsonString).get(0);
            return produceDeviceService.deviceList(deviceRequest);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/pageDeviceInfo",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public PageInfo pageProduceInfo(@RequestParam int pageNum, @RequestParam int pageSize ,String jsonString)
            throws Exception{
        try {
            JsonReader jsonReader = new JsonReader();
            DeviceRequest deviceRequest = jsonReader.getDeviceRequest(jsonString).get(0);
            return produceDeviceService.findPage(pageNum,pageSize,deviceRequest);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getFunctionByDevice" ,produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public List<ProduceFunction> getFunctionByDevice(int deviceId){
        return produceDeviceService.getFunctionByDevice(deviceId);
    }

    @RequestMapping(value = "/getDataListByFunction" ,produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public PageInfo getDataListByFunction(int deviceId, int functionId, int pageNum, int pageSize){
        try {
            return produceDeviceService.findPageData(pageNum,pageSize,deviceId,functionId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
