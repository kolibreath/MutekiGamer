package com.guineap_pig_329.guinea_pig.dao;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;

/*
聊天组群
 */
@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue
    private int groupId;

    @Column
    private String name;

    @Column
    private long time;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
