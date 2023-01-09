package com.szw.commonweal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer implements Serializable{
    @TableId(type = IdType.NONE)//指定主键使用数据库ID自增策略
    private String userId;
    private String passwd;
    private String address;
    private int age;
    private int signNum;
    private String idCard;
    private String sex;
    private String telephone;
    private String idCardAdmin;

}
