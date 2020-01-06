package com.guineap_pig_329.guinea_pig.dao;

import com.guineap_pig_329.guinea_pig.Constants;

import javax.servlet.http.HttpSession;

public class UserSession {
    private int id;
    private String name;
    private String password;

    public static ResultBean getInstance(HttpSession session){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        if(user == null){
            return ResultBean.error(ResultBean.SESSION_OUT_OF_DATE,"用户session 过期");
        }
        return ResultBean.successWithCode(null,user.getId());
    }

    public static
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
