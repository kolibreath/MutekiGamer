package com.guineap_pig_329.guinea_pig.dao;

public class ResponseBean {
    int code ;
    String message;

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


    public ResponseBean(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
