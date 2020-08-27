package com.example.produce.mapper;

import com.example.produce.entity.ProduceData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hou
 * @date 2020/8/21
 */
@Component("ProduceDataMapper")
public interface ProduceDataMapper {

    /**
     * 添加数据
     * @param produceData
     * @return
     */
    int add(ProduceData produceData);

    /**
     * 批量插入数据
     * @param dataRequests
     * @return
     */
    int batchInsert(List<ProduceData> dataRequests);

    /**
     * 删除设备Id为 deviceId 下的数据
     * @param deviceId
     * @return
     */
    int deleteByDeviceId(int deviceId);

    /**
     * 删除功能Id为 functionId 下的数据
     * @param functionId
     * @return
     */
    int deleteByFunctionId(int functionId);
}
