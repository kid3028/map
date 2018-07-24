package com.map.controller;

import com.map.entity.ResultVO;
import com.map.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/list")
    @ResponseBody
    public ResultVO<List> getCustomerList(double currentLon, double currentLat, double radius) {
        return new ResultVO<List>(true, customerService.getCustomerList(currentLon, currentLat, radius));
    }

    @GetMapping
    public String getCustomer() {
        return "map";
    }





}
