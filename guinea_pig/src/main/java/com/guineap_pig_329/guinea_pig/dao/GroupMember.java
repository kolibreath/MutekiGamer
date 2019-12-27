package com.guineap_pig_329.guinea_pig.dao;

import javax.persistence.*;

/*
群组成员表
 */
@Entity
@Table(name = "group_member")
public class GroupMember {
    @Id
    @GeneratedValue
    private int groupMemberId;

    @Column
    private int groupId;

    @Column
    private int memberId;

    public int getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(int groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
