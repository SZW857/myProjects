package com.szw.commonweal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remark {
    private int flag;
    private String title;
    private String content;
    private String remarkNum;

}
