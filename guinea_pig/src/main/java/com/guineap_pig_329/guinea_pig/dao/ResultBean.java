package com.guineap_pig_329.guinea_pig.dao;

import java.util.Collection;

public class ResultBean<T> {

    private T data;
    private int code ;
    private String message;

    private static int success_code = 200;
    private static int internal_error = 500;
    private static int resources_not_found = 400;

    public ResultBean(){

    }

    public ResultBean(T data, int code ){
        this.data = data;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultBean error(int code , String message) {
        ResultBean bean = new ResultBean();
        bean.setCode(code);
        bean.setMessage(message);
        return bean;
    }

    public static <V> ResultBean success(V data){
        ResultBean bean = new ResultBean();
        bean.setCode(success_code);
        bean.setMessage("success");
        bean.setData(data);
        return bean;
    }


}
