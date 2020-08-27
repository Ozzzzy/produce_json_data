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
     * @param dataRequest
     * @return
     */
    List<ProduceData> getDataListByFunction(DataRequest dataRequest);

    /**
     * 8. 获取同产品下设备名称同名数 用于添加
     * @param deviceRequest
     * @return
     */
    int getSameNameCount(DeviceRequest deviceRequest);

    /**
     * 9. 获取同产品下除此设备外设备名称同名数 用于修改
     * @param deviceRequest
     * @return
     */
    int getSameNameCount2(DeviceRequest deviceRequest);

    /**
     * 10. 通过产品Id获取deviceId列表
     * @param produceId
     * @return
     */
    int[] getDeviceIdListByProduceId(int produceId);
}
