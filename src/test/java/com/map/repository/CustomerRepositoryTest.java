package com.map.repository;

import com.map.entity.Customer;
import com.map.entity.CustomerVO;
import com.map.util.MapUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;


    private static final double raduis  = 2;   // 搜索半径 单位km

    private static double lon = 102.719467;

    private static double lat = 25.038207;

    @Test
    public void testFindByLonAndLatRange() {
//        Map<String, Double> mapRange = MapUtil.getLonAndLatRange(lon, lat, raduis);
        Map<String, Object> mapRange = MapUtil.getLonAndLatRange(lon, lat, raduis);
        List<Customer> customerList = customerRepository.findByLonAndLatRange(mapRange.get("minX"), mapRange.get("maxX"), mapRange.get("minY"),mapRange.get("maxY"));
        System.out.println(customerList);
        List<CustomerVO> sortDistanceList = MapUtil.getDistance(lon, lat, customerList);
        System.out.println(sortDistanceList);
    }

    public static void main(String[] args) {

        SpatialContext geo = SpatialContext.GEO;
        double distance = geo.calcDistance(new PointImpl(lon, lat, geo), geo.makePoint(lon, lat))
                * DistanceUtils.DEG_TO_KM;
        System.out.println(distance);
    }





}
