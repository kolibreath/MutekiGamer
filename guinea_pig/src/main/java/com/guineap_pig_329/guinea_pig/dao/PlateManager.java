package com.guineap_pig_329.guinea_pig.dao;

public  class PlateManager {
    private int plateId;
    private int userId;

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PlateManager(int plateId, int userId) {
        this.plateId = plateId;
        this.userId = userId;
    }
}