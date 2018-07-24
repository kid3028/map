package com.map.service.impl;

import com.map.entity.Customer;
import com.map.entity.CustomerVO;
import com.map.repository.CustomerRepository;
import com.map.service.CustomerService;
import com.map.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerVO> getCustomerList(double currentLon, double currentLat, double raduis) {
        long start = System.currentTimeMillis();
        System.out.println("---------------------搜索开始---------------");
//        Map<String, Double> mapRange = MapUtil.getLonAndLatRange(currentLon, currentLat, raduis);
        Map<String, Object> mapRange = MapUtil.getLonAndLatRange(currentLon, currentLat, raduis);
        List<Customer> customerList = customerRepository.findByLonAndLatRange(mapRange.get("minX"), mapRange.get("maxX"), mapRange.get("minY"),mapRange.get("maxY"));
        List<CustomerVO> sortDistanceList = MapUtil.getDistance(currentLon, currentLat, customerList);
        long end = System.currentTimeMillis();
        System.out.println("---------------------搜索结束---------------");
        System.out.println("---------------------共耗时" + (end - start) + "ms---------------------");
        return sortDistanceList;
    }
}
