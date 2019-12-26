package com.guineap_pig_329.guinea_pig.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;

@Entity

@Table(name ="plate")
public class Plate {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //从管理员的表中查询出
    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getPlateThumbnail() {
        return plateThumbnail;
    }

    public void setPlateThumbnail(String plateThumbnail) {
        this.plateThumbnail = plateThumbnail;
    }

    @Column
    private String plateManager;
    //KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length=32)
    private String plateName;
    @Column
    private String plateThumbnail;

    public Plate(){}

    public Plate( String plateName, String plateThumbnail) {
        this.plateName = plateName;
        this.plateThumbnail = plateThumbnail;
    }



    public String getPlateManager() {
        return plateManager;
    }

    public void setPlateManager(String plateManager) {
        this.plateManager = plateManager;
    }

}
