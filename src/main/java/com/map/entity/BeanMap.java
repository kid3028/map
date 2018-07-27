package com.map.entity;

public class BeanMap {
    private Long id;

    private String name;

    private String localPosition;

    private Double lon;

    private Double lat;

    private Long geohash;

    private String remarks;

    public BeanMap() {
    }

    public BeanMap(String name, String localPosition, Double lon, Double lat, Long geohash, String remarks) {
        this.name = name;
        this.localPosition = localPosition;
        this.lon = lon;
        this.lat = lat;
        this.geohash = geohash;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocalPosition() {
        return localPosition;
    }

    public void setLocalPosition(String localPosition) {
        this.localPosition = localPosition == null ? null : localPosition.trim();
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Long getGeohash() {
        return geohash;
    }

    public void setGeohash(Long geohash) {
        this.geohash = geohash;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String toString() {
        return "BeanMap{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", localPosition='" + localPosition + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", geohash=" + geohash +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}