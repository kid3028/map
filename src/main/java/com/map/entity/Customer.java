package com.map.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String localPosition;

    // 经度
    private Double lon;

    // 纬度
    private double lat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalPosition() {
        return localPosition;
    }

    public void setLocalPosition(String localPosition) {
        this.localPosition = localPosition;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Customer(String name, String localPosition, Double lon, double lat) {
        this.name = name;
        this.localPosition = localPosition;
        this.lon = lon;
        this.lat = lat;
    }

    /**
     *  由于jpa底层是使用反射进行orm，所以必须要有无参构造
     */
    public Customer() {
    }

//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", localPosition='" + localPosition + '\'' +
//                ", lon=" + lon +
//                ", lat=" + lat +
//                '}';
//    }
}
