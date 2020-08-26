package com.example.produce.service;

import com.example.produce.entity.ProduceInformation;
import com.example.produce.mapper.ProduceDataMapper;
import com.example.produce.mapper.ProduceDeviceMapper;
import com.example.produce.mapper.ProduceFunctionMapper;
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
public class ProduceInformationService {

    @Autowired
    private ProduceInformationMapper produceInformationMapper;

    @Autowired
    private ProduceFunctionMapper produceFunctionMapper;

    @Autowired
    private ProduceDeviceMapper produceDeviceMapper;

    @Autowired
    private ProduceDataMapper produceDataMapper;

    /**
     * 1. 添加产品
     * @param produceInformation  统计名称的数量=0 根据产品名字搜产品是否存在，传那个参数用哪个检索
     * @return
     */
    public String addProduce(ProduceInformation produceInformation){
        String produceName = produceInformation.getProduceName();
        String[] produceNameList = produceInformationMapper.getProduceNameList();
        if(Arrays.asList(produceNameList).contains(produceName)){
            return "产品添加失败，失败原因：此产品名称已存在。";
        }else {
            produceInformationMapper.add(produceInformation);
            return "产品添加成功";
        }
    }

    /**
     * 2. 修改产品信息
     * @param produceInformation
     * @return
     */
    public String modifyProduce(ProduceInformation produceInformation){
        String produceName = produceInformation.getProduceName();
        int produceId = produceInformation.getProduceId();
        String[] produceNameList = produceInformationMapper.getProduceNameList2(produceId);
        if(Arrays.asList(produceNameList).contains(produceName)){
            return "产品信息修改失败，失败原因：此产品名称已存在。";
        }else {
            produceInformationMapper.modify(produceInformation);
            return "产品信息修改成功";
        }
    }

    /**
     * 3. 查看产品详情
     * @param produceId
     * @return
     */
    public ProduceInformation detailsProduce(int produceId){
        return this.produceInformationMapper.details(produceId);
    }

    /**
     * 4. 获取产品信息列表
     * @return
     */
    public List<ProduceInformation> produceList(){
        return this.produceInformationMapper.produceList();
    }

    /**
     * 5. 删除产品
     * @param produceId
     * @return
     */
    public String deleteProduce(int produceId){
        int[] functionIdList = produceFunctionMapper.getFunctionIdListByProduceId(produceId);
        for (int value : functionIdList) {
            produceFunctionMapper.delete(value);
            produceDataMapper.deleteByFunctionId(value);
        }
        int[] deviceIdList = produceDeviceMapper.getDeviceIdListByProduceId(produceId);
        for(int value : deviceIdList){
            produceDeviceMapper.delete(value);
            produceDataMapper.deleteByDeviceId(value);
        }
        produceInformationMapper.delete(produceId);
        return "删除产品成功！";
    }

    /**
     * 6. 分页展示产品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo findPage(int pageNum, int pageSize){
        //放一起
        PageHelper.startPage(pageNum,pageSize);
        List<ProduceInformation> produceInformationList = this.produceInformationMapper.produceList();
        PageInfo pageInfo = new PageInfo(produceInformationList);
        return pageInfo;
    }
}
