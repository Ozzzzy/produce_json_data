package com.example.produce.service;

import com.example.produce.entity.ProduceInformation;
import com.example.produce.mapper.ProduceInformationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public boolean addProduce(ProduceInformation produceInformation){
        return this.produceInformationMapper.add(produceInformation) > 0;
    }

    public boolean modifyProduce(ProduceInformation produceInformation){
        return this.produceInformationMapper.modify(produceInformation) > 0;
    }

    public ProduceInformation detailsProduce(int produceId){
        return this.produceInformationMapper.details(produceId);
    }

    public List<ProduceInformation> produceList(){
        return this.produceInformationMapper.produceList();
    }
    public boolean deleteProduce(int produceId){
        return this.produceInformationMapper.delete(produceId) > 0;
    }

    public PageInfo findPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ProduceInformation> list = this.produceList();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
