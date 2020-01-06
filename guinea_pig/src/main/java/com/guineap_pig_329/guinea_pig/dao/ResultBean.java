package com.guineap_pig_329.guinea_pig.dao;

public class ResultBean<T> {

    private T data;
    private int code ;
    private String message;

    public static int success_code = 200;
    public static int internal_error = 500;
    public static int resources_not_found = 404;
    public static int bad_request = 400;
    public static int SESSION_OUT_OF_DATE  = 401;

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
        return getResultBean(data, success_code);
    }

    public static <V> ResultBean successWithCode(V data,int code){
        return getResultBean(data, code);
    }

    private static <V> ResultBean getResultBean(V data, int code) {
        ResultBean bean = new ResultBean();
        bean.setCode(code);
        bean.setMessage("success");
        bean.setData(data);
        return bean;
    }

    public static ResultBean relogin(String message){
        ResultBean bean = new ResultBean();
        bean.setCode(ResultBean.SESSION_OUT_OF_DATE);
        bean.setMessage(message);
        return bean;
    }


}
