package com.map.repository;

import com.map.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     *
     *  根据经纬度范围进行搜索周边
     * @param lonFrom
     * @param lonTo
     * @param latFrom
     * @param latTo
     * @return
     */
    @Query("from Customer c where lon between ?1 and ?2 and lat between ?3 and ?4")
//    List<Customer> findByLonAndLatRange(Double lonFrom, Double lonTo, Double latFrom, Double latTo);
    List<Customer> findByLonAndLatRange(Object lonFrom, Object lonTo, Object latFrom, Object latTo);

}
