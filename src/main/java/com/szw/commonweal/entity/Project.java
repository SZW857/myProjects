package com.szw.commonweal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String pNum;
    private String pStatus;
    private Date sTime;
    private Date fTime;
    private int human;
    private String sponsor;
    private String title;
    private String content;
    private int siginNum;
    private String siginStatus;
    private String idCardAdmin;
    private String userId;
}
