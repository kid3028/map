package com.map.service;

import com.map.entity.CustomerVO;

import java.util.List;

public interface CustomerService {
    List<CustomerVO> getCustomerList(double currentLon, double currentLat, double raduis);
}
