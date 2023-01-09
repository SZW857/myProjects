package com.szw.commonweal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class helpInfo {
    private Date date;
    private String type;
    private String title;
    private String content;
    private String infoNum;
    private String who;
}
