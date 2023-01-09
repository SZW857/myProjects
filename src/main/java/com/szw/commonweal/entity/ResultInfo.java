package com.szw.commonweal.entity;

import java.io.Serializable;

/**
 * 统一响应格式类型
 * */
public class ResultInfo<T> implements Serializable {

    public static final String EXCEPTION = "exception";

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    public static final String RESEND = "resend";

    public static final String CONTINUE = "continue";

    private String status = SUCCESS; //处理状态

    private String errorCode = "none";   //错误代码

    private T data;//响应结果

    private String extra = "none";

    public ResultInfo() {
        this.status = SUCCESS;
    }

    public ResultInfo(String status, T data){
        this.status = status;
        this.data = data;
    }

    public ResultInfo(String status, String errorCode, T result) {
        this.status = SUCCESS;
        this.errorCode = errorCode;
        this.data = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "status='" + status + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", result=" + data +
                '}';
    }
}
