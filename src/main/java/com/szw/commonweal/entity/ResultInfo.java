package com.szw.commonweal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一响应格式类型
 * */
@Data
@AllArgsConstructor
public class ResultInfo<T> implements Serializable {

    public static final String EXCEPTION = "exception";

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    public static final String RESEND = "resend";

    public static final String CONTINUE = "continue";

    public static final int INTERCEPT_CODE=102;

    private String status = SUCCESS; //处理状态

    private int Code = 0;   //状态码

    private T data;//响应结果

    private String extra = "none"; //附加信息

    private String ID = "NONE";

    private String adminName = "none";

    private String email = "none";

    private String telephone = "none";

    public  long total = 0;

    public ResultInfo() {
        this.status = SUCCESS;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "status='" + status + '\'' +
                ", CODE='" + INTERCEPT_CODE + '\'' +
                ", result=" + data +
                '}';
    }
}
