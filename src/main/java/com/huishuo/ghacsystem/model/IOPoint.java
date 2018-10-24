package com.huishuo.ghacsystem.model;

public class IOPoint {
    private int id;
    private String address;
    private String type;
    private int placeId;
    private int lineId;
    private int factoryId;

    public IOPoint(int id, String address, String type, int placeId, int lineId, int factoryId) {
        this.id = id;
        this.address = address;
        this.type = type;
        this.placeId = placeId;
        this.lineId = lineId;
        this.factoryId = factoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
