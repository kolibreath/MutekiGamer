package com.guineap_pig_329.guinea_pig.dao;

import com.guineap_pig_329.guinea_pig.Constants;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSession {
    private int id;
    private String name;
    private String password;
    private int gameId;//关注的第一个游戏的Id
    /**
     * @param session
     * @return 带userId 返回值的
     */
    public static ResultBean getInstance(HttpSession session){
        UserSession user = (UserSession) session.getAttribute(Constants.USE_SESSION_KEY);
        if(user == null){
            return ResultBean.error(ResultBean.SESSION_OUT_OF_DATE,"用户session 过期");
        }
        return ResultBean.successWithCode(null,user.getId());
    }

    /**
     * @param response
     * @param code
     * 自动判断是否需要跳转到登陆
     */
    public static void autoRedirect(HttpServletResponse response, int code){
        if(code == ResultBean.SESSION_OUT_OF_DATE) {
            try {
                response.sendRedirect("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public UserSession(int id, String name, String password, int gameId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gameId = gameId;
    }

    public UserSession(int id, String name, String pass)
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
