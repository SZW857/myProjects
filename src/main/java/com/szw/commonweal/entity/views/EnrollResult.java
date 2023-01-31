package com.szw.commonweal.entity.views;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("enroll_result")
public class EnrollResult {
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "serial_num")
    private String serialNum;
    private String imageUrl;
    private String telephone;
    private String email;
    private String contact;

}
