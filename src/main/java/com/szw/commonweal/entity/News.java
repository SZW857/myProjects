package com.szw.commonweal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private int newsNmu;
    private String idCard_Admin;
    private String content;
    private String title;
    private String type;
    private Date date;

}
