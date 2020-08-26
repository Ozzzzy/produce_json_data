package com.example.produce.mapper;

import com.example.produce.entity.ProduceFunction;
import com.example.produce.entity.ProduceInformation;
import com.example.produce.entity.SelectRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * @author hou
 * @date 2020/8/21
 */
@Component("ProduceFunctionMapper")
public interface ProduceFunctionMapper {

    /**
     * 1. 添加产品功能
     * @param produceFunction
     * @return
     */
    int add(ProduceFunction produceFunction);

    /**
     * 2. 修改功能信息
     * @param produceFunction
     * @return
     */
    int modify(ProduceFunction produceFunction);

    /**
     * 3. 查看 id 为 functionId 的功能详情
     * @param functionId
     * @return
     */
    ProduceFunction details(int functionId);

    /**
     * 4. 查看功能列表
     * @return
     */
    List<ProduceFunction> functionList();

    /**
     * 5. 删除功能
     * @param functionId
     * @return
     */
    int delete(int functionId);

    /**
     * 6. 通过产品Id获取功能名称列表
     * @param produceId
     * @return
     */
    String[] getNameListByProduceId(int produceId);

    /**
     * 7. 通过产品Id获取除此functionId之外的功能名称列表
     * @param selectRequest
     * @return
     */
    String[] getNameListByProduceId2(SelectRequest selectRequest);

    /**
     * 8. 通过产品Id获取功能Id列表
     * @param produceId
     * @return
     */
    int[] getFunctionIdListByProduceId(int produceId);

    /**
     * 9. 通过产品Id获取功能列表
     * @param produceId
     * @return
     */
    List<ProduceFunction> functionListOfOne(int produceId);

}
