package com.example.produce.service;

import com.example.produce.entity.DataRequest;
import com.example.produce.entity.ProduceData;
import com.example.produce.entity.ProduceDevice;
import com.example.produce.entity.ProduceFunction;
import com.example.produce.mapper.ProduceDataMapper;
import com.example.produce.mapper.ProduceDeviceMapper;
import com.example.produce.mapper.ProduceFunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hou
 * @date 2020/8/21
 */
@Service
@Transactional(rollbackFor = Exception.class)

public class ProduceDataService {

    @Autowired
    private ProduceDataMapper produceDataMapper;

    @Autowired
    private ProduceFunctionMapper produceFunctionMapper;

    @Autowired
    private ProduceDeviceMapper produceDeviceMapper;

    /**
     * 添加数据
     * @param dataRequest
     * @return
     */
    public String addData(DataRequest dataRequest){

        int deviceId = Integer.parseInt(dataRequest.getDeviceId());
        int functionId = Integer.parseInt(dataRequest.getFunctionId());
        ProduceFunction produceFunction = produceFunctionMapper.details(functionId);
        ProduceDevice produceDevice = produceDeviceMapper.details(deviceId);
        if(produceFunction != null){
            if(produceDevice != null){
                int pdId = produceDevice.getProduceId();
                int pfId = produceFunction.getProduceId();
                if(pdId == pfId && pdId != 0){
                    this.produceDataMapper.add(dataRequest);
                    return "数据添加成功！";
                }else {
                    return "数据添加失败，失败原因：功能与设备所对应的产品Id不同。";
                }
            }else {
                return "数据添加失败，失败原因：设备不存在。";
            }
        }
        return "数据添加失败，失败原因：功能不存在。";
    }

    /**
     * 批量插入
     * @param listAll
     * @param batchSize
     * @return
     */
    public String batchInsert(List<ProduceData> listAll, int batchSize){
        int listAllSize = listAll.size();
        List<ProduceData> produceDataList = new ArrayList<>();
        for(int j = 0 ; j < listAllSize; j += batchSize){
            if( j + batchSize <= listAllSize ){
                produceDataList.addAll(listAll.subList(j,j + batchSize));
                try {
                    produceDataMapper.batchInsert(produceDataList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                produceDataList.clear();
            }
            else {
                produceDataList.addAll(listAll.subList(j,listAllSize));
                try {
                    produceDataMapper.batchInsert(produceDataList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                produceDataList.clear();
            }
        }
        return "批量插入成功!";
    }
}
