package com.example.orca.okhttp.exception;

/**
 * Created by orca on 2017/4/2.
 */

public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1L;
    private int ecode;
    private Object msg;
    public OkHttpException(int ecode,Object msg){
        this.ecode=ecode;
        this.msg=msg;
    }
    public int getEcode(){return  ecode;}
    public Object getMsg(){return  msg;}
}
