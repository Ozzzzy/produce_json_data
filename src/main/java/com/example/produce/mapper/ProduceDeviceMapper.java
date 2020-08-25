package com.example.produce.mapper;

import com.example.produce.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hou
 * @date 2020/8/21
 */
@Component("ProduceDeviceMapper")
public interface ProduceDeviceMapper {

    /**
     * 1. 添加设备
     * @param deviceRequest
     * @return
     */
    int add(DeviceRequest deviceRequest);

    /**
     * 2. 修改设备
     * @param deviceRequest
     * @return
     */
    int modify(DeviceRequest deviceRequest);

    /**
     * 3. 查看 id 为 deviceId 的设备详情
     * @param deviceId
     * @return
     */
    ProduceDevice details(int deviceId);

    /**
     * 4. 删除设备
     * @param deviceId
     * @return
     */
    int delete(int deviceId);

    /**
     * 5. 通过查询条件分页查看设备列表
     * @param deviceRequest
     * @return
     */
    List<ProduceDevice> deviceList(DeviceRequest deviceRequest);

    /**
     * 6. 查看设备的功能点列表
     * @param deviceId
     * @return
     */
    List<ProduceFunction> getFunctionByDevice(int deviceId);

    /**
     * 7. 查看设备某一功能点数据列表
     * @param deviceId
     * @param functionId
     * @return
     */
    List<ProduceData> getDataListByFunction(int deviceId, int functionId);

    /**
     * 8. 通过产品Id获取设备名称列表
     * @param produceId
     * @return
     */
    String[] getNameListByProduceId(int produceId);

    /**
     * 9. 通过产品Id获取除此deviceId之外的设备名称列表
     * @param selectRequest
     * @return
     */
    String[] getNameListByProduceId2(SelectRequest selectRequest);

    /**
     * 10. 通过产品Id获取deviceId列表
     * @param produceId
     * @return
     */
    int[] getDeviceIdListByProduceId(int produceId);
}
