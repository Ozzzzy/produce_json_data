package com.example.produce.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.produce.entity.ProduceInformation;
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



//    public WeatherRequest addToListWeatherRequest(JSONObject jo){
//        WeatherRequest weatherRequest = new WeatherRequest();
//        if (jo.get("createTime") != null && jo.get("createTime") != ""){
//            weatherRequest.setCreateTime(getDateTime(jo.get("createTime").toString()));
//        }
//        return weatherRequest;
//    }



    /**
     * 生成 List<>Weather>
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


}
