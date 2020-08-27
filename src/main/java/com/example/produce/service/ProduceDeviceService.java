package com.example.produce.service;

import com.example.produce.entity.*;
import com.example.produce.mapper.ProduceDataMapper;
import com.example.produce.mapper.ProduceDeviceMapper;
import com.example.produce.mapper.ProduceInformationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author hou
 * @date 2020/8/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProduceDeviceService {

    @Autowired
    private ProduceDeviceMapper produceDeviceMapper;

    @Autowired
    private ProduceInformationMapper produceInformationMapper;

    @Autowired
    private ProduceDataMapper produceDataMapper;

    /**
     * 1. 添加设备
     * @param deviceRequest
     * @return
     */
    public String addDevice(DeviceRequest deviceRequest) {
        int produceId = deviceRequest.getProduceId();
        int sameNameCount = produceDeviceMapper.getSameNameCount(deviceRequest);
        if(produceInformationMapper.details(produceId) != null){
            if(sameNameCount > 0){
                return "设备添加失败，失败原因：此设备的名称已存在。";
            }else {
                produceDeviceMapper.add(deviceRequest);
                return "设备添加成功！";
            }
        }else {
            return "设备添加失败，失败原因：此设备的产品Id找不到。";
        }
    }

    /**
     * 2. 修改设备
     * @param deviceRequest
     * @return
     */
    public String modifyDevice(DeviceRequest deviceRequest) {
        int produceId = deviceRequest.getProduceId();
        int sameNameCount = produceDeviceMapper.getSameNameCount2(deviceRequest);
        if(produceInformationMapper.details(produceId) != null){
            if(sameNameCount > 0){
                return "设备信息修改失败，失败原因：此产品下的设备名称已存在！";
            }else {
                produceDeviceMapper.modify(deviceRequest);
                return "设备信息修改成功！";
            }
        }else {
            return "设备修改失败，失败原因：此设备的产品Id找不到。";
        }
    }

    /**
     * 3. 查看 id 为 deviceId 的设备详情
     * @param deviceId
     * @return
     */
    public ProduceDevice detailsDevice(int deviceId){
        return this.produceDeviceMapper.details(deviceId);
    }

    /**
     * 4. 删除设备
     * @param deviceId
     * @return
     */
    public String deleteDevice(int deviceId){
        produceDeviceMapper.delete(deviceId);
        produceDataMapper.deleteByDeviceId(deviceId);
        return "删除设备成功！";
    }

    /**
     * 5. 通过查询条件分页查看设备列表
     * @param deviceRequest
     * @return
     */
    public List<ProduceDevice> deviceList(DeviceRequest deviceRequest){
        return this.produceDeviceMapper.deviceList(deviceRequest);
    }

    /**
     * 6. 查看设备的功能点列表
     * @param deviceId
     * @return
     */
    public List<ProduceFunction> getFunctionByDevice(int deviceId){
        return this.produceDeviceMapper.getFunctionByDevice(deviceId);
    }

    /**
     * 7. 查看设备某一功能点数据列表
     * @param dataRequest
     * @return
     */
    public List<ProduceData> getDataListByFunction(DataRequest dataRequest){
        return this.produceDeviceMapper.getDataListByFunction(dataRequest);
    }

    /**
     * 分页查询设备
     * @param deviceRequest
     * @return
     */
    public PageInfo findPage(DeviceRequest deviceRequest){
        int pageNum = deviceRequest.getPageNum();
        int pageSize = deviceRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<ProduceDevice> produceDeviceList = this.produceDeviceMapper.deviceList(deviceRequest);
        PageInfo pageInfo = new PageInfo(produceDeviceList);
        return pageInfo;
    }

    /**
     * 分页查询设备某功能点下的数据
     * @param dataRequest
     * @return
     */
    public PageInfo findPageData(DataRequest dataRequest){
        int pageNum = dataRequest.getPageNum();
        int pageSize = dataRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<ProduceData> produceDataList = produceDeviceMapper.getDataListByFunction(dataRequest);
        PageInfo pageInfo = new PageInfo(produceDataList);
        return pageInfo;
    }
}
