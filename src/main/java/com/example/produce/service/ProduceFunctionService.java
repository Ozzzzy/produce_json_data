package com.example.produce.service;

import com.example.produce.entity.ProduceFunction;
import com.example.produce.mapper.ProduceDataMapper;
import com.example.produce.mapper.ProduceFunctionMapper;
import com.example.produce.mapper.ProduceInformationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

    @Autowired
    private ProduceDataMapper produceDataMapper;

    /**
     * 1. 添加功能
     * @param produceFunction
     * @return
     */
    public String addFunction(ProduceFunction produceFunction){
        int produceId = produceFunction.getProduceId();
        int sameNameCount = produceFunctionMapper.getSameNameCount(produceFunction);
        if(produceInformationMapper.details(produceId) != null){
            if(sameNameCount > 0){
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

        int sameNameCount = produceFunctionMapper.getSameNameCount2(produceFunction);
        if(sameNameCount > 0){
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
        produceDataMapper.deleteByFunctionId(functionId);
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


    /**
     * 7. 通过产品 Id 获取功能的 name 和 Id 键值对
     * @param produceId
     * @return
     */
    public HashMap<String, Integer> getHashTableNameAndId(int produceId){
        HashMap<String, Integer> functionNameAndId = new HashMap<>();
        List<ProduceFunction> produceFunctionList = produceFunctionMapper.functionListOfOne(produceId);
        for(int i = 0 ; i < produceFunctionList.size() ; i ++){
            ProduceFunction produceFunction = produceFunctionList.get(i);
            String functionName = produceFunction.getFunctionName();
            Integer functionId = produceFunction.getFunctionId();
            functionNameAndId.put(functionName,functionId);
        }
        return functionNameAndId;
    }

}
