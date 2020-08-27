package com.example.produce.controller;

import com.example.produce.entity.*;
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
@RequestMapping(value = "/device", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
public class ProduceDeviceController {

    @Autowired
    private ProduceDeviceService produceDeviceService;

    @RequestMapping(value = "/addDevice",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String add(@RequestBody DeviceRequest deviceRequest)throws Exception{
        try {
            return produceDeviceService.addDevice(deviceRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/modifyDevice",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public @ResponseBody String modify(@RequestBody DeviceRequest deviceRequest)throws Exception{
        try {
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
    public @ResponseBody List<ProduceDevice> deviceList(@RequestBody DeviceRequest deviceRequest)throws Exception{
        try {
            return produceDeviceService.deviceList(deviceRequest);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/pageDeviceInfo",produces = "application/json; charset=UTF-8",
            method = RequestMethod.POST)
    public PageInfo pageProduceInfo(@RequestBody DeviceRequest deviceRequest) throws Exception{
        try {
            return produceDeviceService.findPage(deviceRequest);
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
    public PageInfo getDataListByFunction(@RequestBody DataRequest DataRequest){
        try {
            return produceDeviceService.findPageData(DataRequest);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
