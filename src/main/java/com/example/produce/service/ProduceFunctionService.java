package com.example.produce.service;

import com.example.produce.entity.ProduceFunction;
import com.example.produce.entity.SelectRequest;
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
public class ProduceFunctionService {

    @Autowired
    private ProduceFunctionMapper produceFunctionMapper;

    @Autowired
    private ProduceInformationMapper produceInformationMapper;

    /**
     * 1. 添加功能
     * @param produceFunction
     * @return
     */
    public String addFunction(ProduceFunction produceFunction){
        int produceId = produceFunction.getProduceId();
        String functionName = produceFunction.getFunctionName();
        String[] functionNameList = produceFunctionMapper.getNameListByProduceId(produceId);
        if(produceInformationMapper.details(produceId) != null){
            if(Arrays.asList(functionNameList).contains(functionName)){
                return "功能添加失败，失败原因：此功能的名称已存在。";
            }else {
                produceFunctionMapper.add(produceFunction);
                return "功能添加成功！";
            }
        }else {
            return "功能添加失败，失败原因：此功能的产品Id找不到。";
        }
    }

    /**
     * 2. 修改功能
     * @param produceFunction
     * @return
     */
    public String modifyFunction(ProduceFunction produceFunction){

        int produceId = produceFunction.getProduceId();
        int functionId = produceFunction.getFunctionId();
        SelectRequest selectRequest = new SelectRequest(produceId,functionId);
        String functionName = produceFunction.getFunctionName();
        String[] functionNameList = produceFunctionMapper.getNameListByProduceId2(selectRequest);
        if(Arrays.asList(functionNameList).contains(functionName)){
            return "功能信息修改失败，失败原因：此产品下的功能名称已存在！";
        }else {
            produceFunctionMapper.modify(produceFunction);
            return "功能信息修改成功！";
        }
    }

    /**
     * 3. 查看功能详情
     * @param functionId
     * @return
     */
    public ProduceFunction detailsFunction(int functionId){
        return this.produceFunctionMapper.details(functionId);
    }

    /**
     * 4. 获取功能列表
     * @return
     */
    public List<ProduceFunction> functionList(){
        return this.produceFunctionMapper.functionList();
    }

    /**
     * 5. 删除功能
     * @param functionId
     * @return
     */
    public String deleteFunction(int functionId){
        produceFunctionMapper.delete(functionId);
        return "删除功能成功！";
    }

    /**
     * 6. 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo findPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ProduceFunction> produceFunctionList = this.produceFunctionMapper.functionList();
        PageInfo pageInfo = new PageInfo(produceFunctionList);
        return pageInfo;
    }

}
