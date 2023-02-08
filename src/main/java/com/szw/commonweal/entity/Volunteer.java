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
    @TableId(type = IdType.NONE)
    private String userId;
    private String passwd;
    private String address;
    private Integer age;
    private Integer verifyStatus;
    private Integer signNum;
    private String idCard;
    private String sex;
    private String telephone;
    private String email;
    private String idCardAdmin;
    private String avatar;
}
