package com.guineap_pig_329.guinea_pig.model;

public class UserSession {
    private int id;
    private String name;
    private String password;

    public UserSession(int id,String name,String pass)
    {
        this.id=id;
        this.name=name;
        this.password=pass;
    }
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
