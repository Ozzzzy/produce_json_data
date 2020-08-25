package com.example.produce.mapper;

import com.example.produce.entity.DataRequest;
import org.springframework.stereotype.Component;

/**
 * @author hou
 * @date 2020/8/21
 */
@Component("ProduceDataMapper")
public interface ProduceDataMapper {

    /**
     * 添加数据
     * @param dataRequest
     * @return
     */
    int add(DataRequest dataRequest);
}
