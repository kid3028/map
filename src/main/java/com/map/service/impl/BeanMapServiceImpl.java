package com.map.service.impl;

import com.map.mapper.BeanMapMapper;
import com.map.service.BeanMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * @Author qull
 * @Discirption
 * @Date 2018/7/27 15:29
 */
@Service
public class BeanMapServiceImpl implements BeanMapService {
    private final BeanMapMapper beanMapMapper;

    @Autowired
    public BeanMapServiceImpl(BeanMapMapper beanMapMapper) {
        Assert.notNull(beanMapMapper, "beanMapper must not be null");
        this.beanMapMapper = beanMapMapper;
    }

    @Override
    public List<Map<String, Object>> findByLonAndLatRange(Map<String, Object> mapRange) {
        return beanMapMapper.findByLonAndLatRange(mapRange);
    }
}
