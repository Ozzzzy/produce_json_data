package com.example.produce.mapper;

import com.example.produce.entity.ProduceInformation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hou
 * @date 2020/8/21
 */

@Component("ProduceInformationMapper")
public interface ProduceInformationMapper {

    /**
     * 1. 添加产品信息
     * @param produceInformation
     * @return
     */
    int add(ProduceInformation produceInformation);

    /**
     * 2. 修改产品信息
     * @param produceInformation
     * @return
     */
    int modify(ProduceInformation produceInformation);

    /**
     * 3. 查看 id 为 produceId 的产品详情
     * @param produceId
     * @return
     */
    ProduceInformation details(int produceId);

    /**
     * 4. 查看产品列表
     * @return
     */
    List<ProduceInformation> produceList();

    /**
     * 5. 删除产品
     * @param produceId
     * @return
     */
    int delete(int produceId);

    /**
     * 6. 获取与此产品名称相同的数目 用于添加
     * @param produceName
     * @return
     */
    int getSameNameCount(String produceName);

    /**
     * 7. 获取除此产品名称以外的名称同名数 用于修改
     * @param produceInformation
     * @return
     */
    int getSameNameCount2(ProduceInformation produceInformation);
}
