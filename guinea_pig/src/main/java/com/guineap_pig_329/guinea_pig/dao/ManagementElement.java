package com.guineap_pig_329.guinea_pig.dao;

public  class ManagementElement{

    private int userId;
    private int plateId;

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    public ManagementElement(int plateId) {
        this.plateId = plateId;
    }
}