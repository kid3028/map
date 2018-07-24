package com.map.entity;

public class CustomerVO extends Customer {

    private double distance;

    private double[] lnglat;

    public CustomerVO() {
    }

    public CustomerVO(String name, String localPosition, Double lon, double lat, double distance) {
        super(name, localPosition, lon, lat);
        this.distance = distance;
    }

//    @Override
//    public String toString() {
//        return "CustomerVO{" +
//                "id=" + super.getId() +
//                ", name='" + super.getName() + '\'' +
//                ", localPosition='" + super.getLocalPosition() + '\'' +
//                ", lon=" + super.getLon() +
//                ", lat=" + super.getLat() +
//                ", distance=" + this.getDistance() +
//                '}';
//
//    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double[] getLnglat() {
        return lnglat;
    }

    public void setLnglat(double[] lnglat) {
        this.lnglat = lnglat;
    }
}
