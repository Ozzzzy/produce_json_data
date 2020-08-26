package com.example.produce.mapper;

import com.example.produce.entity.ProduceFunction;
import org.springframework.stereotype.Component;

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
     * 6. 获取同产品下功能名称同名数 用于添加
     * @param produceFunction
     * @return
     */
    int getSameNameCount(ProduceFunction produceFunction);

    /**
     * 7. 获取同产品下除此功能名称之外的同名数 用于修改
     * @param produceFunction
     * @return
     */
    int getSameNameCount2(ProduceFunction produceFunction);

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
