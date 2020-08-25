package com.example.produce.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.produce.entity.*;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.alibaba.fastjson.JSONObject;

/**
 * @author hou
 * @date 2020/8/14
 */
public class JsonReader {

    /**
     * String -->  Date
     * @param time
     * @return
     */
    public static Date getDateTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 读取 JSON 到 ProduceInformation 实体
     * @param jo
     * @return
     */
    public ProduceInformation getProduceInformation(JSONObject jo){

        ProduceInformation produceInformation = new ProduceInformation();

        if (jo.get("produceId") != null && jo.get("produceId") != ""){
            produceInformation.setProduceId(Integer.parseInt(jo.get("produceId").toString()));
        }
        if (jo.get("produceName") != null && jo.get("produceName") != ""){
            produceInformation.setProduceName(jo.get("produceName").toString());
        }
        if (jo.get("produceIndustry") != null && jo.get("produceIndustry") != ""){
            produceInformation.setProduceIndustry(jo.get("produceIndustry").toString());
        }
        if (jo.get("deviceNode") != null && jo.get("deviceNode") != ""){
            produceInformation.setDeviceNode(jo.get("deviceNode").toString());
        }
        if (jo.get("accessProtocol") != null && jo.get("accessProtocol") != ""){
            produceInformation.setAccessProtocol(jo.get("accessProtocol").toString());
        }
        if (jo.get("networkingMode") != null && jo.get("networkingMode") != ""){
            produceInformation.setNetworkingMode(jo.get("networkingMode").toString());
        }
        if (jo.get("produceModel") != null && jo.get("produceModel") != ""){
            produceInformation.setProduceModel(jo.get("produceModel").toString());
        }
        if (jo.get("produceDescription") != null && jo.get("produceDescription") != ""){
            produceInformation.setProduceDescription(jo.get("produceDescription").toString());
        }

        return produceInformation;
    }

    /**
     * 生成 List<>ProductionInformation>
     * @param jsonString
     * @return
     */
    public List<ProduceInformation> getProduceList(String jsonString){
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<ProduceInformation> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jo = JSONObject.fromObject(jsonArray.getJSONObject(i));
            list.add(getProduceInformation(jo));
        }
        return list;
    }

    /**
     * 读取 JSON 到 ProduceFunction 实体
     * @param jo
     * @return
     */
    public ProduceFunction getProduceFunction(JSONObject jo){

        ProduceFunction produceFunction = new ProduceFunction();

        if (jo.get("functionId") != null && jo.get("functionId") != ""){
            produceFunction.setFunctionId(Integer.parseInt(jo.get("functionId").toString()));
        }
        if (jo.get("functionType") != null && jo.get("functionType") != ""){
            produceFunction.setFunctionType(jo.get("functionType").toString());
        }
        if (jo.get("functionName") != null && jo.get("functionName") != ""){
            produceFunction.setFunctionName(jo.get("functionName").toString());
        }
        if (jo.get("fieldName") != null && jo.get("fieldName") != ""){
            produceFunction.setFieldName(jo.get("fieldName").toString());
        }
        if (jo.get("transportType") != null && jo.get("transportType") != ""){
            produceFunction.setTransportType(jo.get("transportType").toString());
        }
        if (jo.get("functionDescription") != null && jo.get("functionDescription") != ""){
            produceFunction.setFunctionDescription(jo.get("functionDescription").toString());
        }
        if (jo.get("produceId") != null && jo.get("produceId") != ""){
            produceFunction.setProduceId(Integer.parseInt(jo.get("produceId").toString()));
        }


        return produceFunction;
    }

    /**
     * 生成 List<ProductionFunction>
     * @param jsonString
     * @return
     */
    public List<ProduceFunction> getFunctionList(String jsonString){
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<ProduceFunction> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jo = JSONObject.fromObject(jsonArray.getJSONObject(i));
            list.add(getProduceFunction(jo));
        }
        return list;
    }

    /**
     * 读取 JSON 到 ProduceDevice 实体
     * @param jo
     * @return
     */
    public ProduceDevice getProduceDevice(JSONObject jo){

        ProduceDevice produceDevice = new ProduceDevice();

        if (jo.get("deviceId") != null && jo.get("deviceId") != ""){
            produceDevice.setDeviceId(Integer.parseInt(jo.get("deviceId").toString()));
        }
        if (jo.get("deviceIdentification") != null && jo.get("deviceIdentification") != ""){
            produceDevice.setDeviceIdentification(jo.get("deviceIdentification").toString());
        }
        if (jo.get("deviceName") != null && jo.get("deviceName") != ""){
            produceDevice.setDeviceName(jo.get("deviceName").toString());
        }
        if (jo.get("onlineStatus") != null && jo.get("onlineStatus") != ""){
            produceDevice.setOnlineStatus(jo.get("onlineStatus").toString());
        }
        if (jo.get("activationState") != null && jo.get("activationState") != ""){
            produceDevice.setActivationState(jo.get("activationState").toString());
        }
        if (jo.get("createTime") != null && jo.get("createTime") != ""){
            produceDevice.setCreateTime(getDateTime(jo.get("createTime").toString()));
        }
        if (jo.get("activeTime") != null && jo.get("activeTime") != ""){
            produceDevice.setActiveTime(getDateTime(jo.get("activeTime").toString()));
        }
        if (jo.get("enableStatus") != null && jo.get("enableStatus") != ""){
            produceDevice.setEnableStatus(jo.get("enableStatus").toString());
        }
        if (jo.get("deviceAddress") != null && jo.get("deviceAddress") != ""){
            produceDevice.setDeviceAddress(jo.get("deviceAddress").toString());
        }
        if (jo.get("produceId") != null && jo.get("produceId") != ""){
            produceDevice.setProduceId(Integer.parseInt(jo.get("produceId").toString()));
        }
        return produceDevice;
    }

    /**
     * 生成 List<ProductionDevice>
     * @param jsonString
     * @return
     */
    public List<ProduceDevice> getDeviceList(String jsonString){
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<ProduceDevice> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jo = JSONObject.fromObject(jsonArray.getJSONObject(i));
            list.add(getProduceDevice(jo));
        }
        return list;
    }

    /**
     * 读取 JSON 到 DeviceRequest 实体
     * @param jo
     * @return
     */
    public DeviceRequest getDeviceRequest(JSONObject jo){

        DeviceRequest deviceRequest = new DeviceRequest();

        if (jo.get("deviceId") != null && jo.get("deviceId") != ""){
            deviceRequest.setDeviceId(jo.get("deviceId").toString());
        }
        if (jo.get("deviceIdentification") != null && jo.get("deviceIdentification") != ""){
            deviceRequest.setDeviceIdentification(jo.get("deviceIdentification").toString());
        }
        if (jo.get("deviceName") != null && jo.get("deviceName") != ""){
            deviceRequest.setDeviceName(jo.get("deviceName").toString());
        }
        if (jo.get("onlineStatus") != null && jo.get("onlineStatus") != ""){
            deviceRequest.setOnlineStatus(jo.get("onlineStatus").toString());
        }
        if (jo.get("activationState") != null && jo.get("activationState") != ""){
            deviceRequest.setActivationState(jo.get("activationState").toString());
        }
        if (jo.get("createTime") != null && jo.get("createTime") != ""){
            deviceRequest.setCreateTime(jo.get("createTime").toString());
        }
        if (jo.get("activeTime") != null && jo.get("activeTime") != ""){
            deviceRequest.setActiveTime(jo.get("activeTime").toString());
        }
        if (jo.get("enableStatus") != null && jo.get("enableStatus") != ""){
            deviceRequest.setEnableStatus(jo.get("enableStatus").toString());
        }
        if (jo.get("deviceAddress") != null && jo.get("deviceAddress") != ""){
            deviceRequest.setDeviceAddress(jo.get("deviceAddress").toString());
        }
        if (jo.get("produceId") != null && jo.get("produceId") != ""){
            deviceRequest.setProduceId(jo.get("produceId").toString());
        }
        return deviceRequest;
    }

    /**
     * 生成 List<DeviceRequest>
     * @param jsonString
     * @return
     */
    public List<DeviceRequest> getDeviceRequest(String jsonString){
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<DeviceRequest> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jo = JSONObject.fromObject(jsonArray.getJSONObject(i));
            list.add(getDeviceRequest(jo));
        }
        return list;
    }

    /**
     * 读取 JSON 对象到 DataRequest 实体
     * @param jo
     * @return
     */
    public DataRequest getDataRequest(JSONObject jo){

        DataRequest dataRequest = new DataRequest();

        if (jo.get("dataId") != null && jo.get("dataId") != ""){
            dataRequest.setDataId(jo.get("dataId").toString());
        }
        if (jo.get("deviceId") != null && jo.get("deviceId") != ""){
            dataRequest.setDeviceId(jo.get("deviceId").toString());
        }
        if (jo.get("functionId") != null && jo.get("functionId") != ""){
            dataRequest.setFunctionId(jo.get("functionId").toString());
        }
        if (jo.get("dataString") != null && jo.get("dataString") != ""){
            dataRequest.setDataString(jo.get("dataString").toString());
        }
        if (jo.get("createTime") != null && jo.get("createTime") != ""){
            dataRequest.setCreateTime(jo.get("createTime").toString());
        }
        return dataRequest;
    }

    /**
     * 生成 List<DataRequest>
     * @param jsonString
     * @return
     */
    public List<DataRequest> getDataRequest(String jsonString){
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<DataRequest> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jo = JSONObject.fromObject(jsonArray.getJSONObject(i));
            list.add(getDataRequest(jo));
        }
        return list;
    }
}
