package com.szw.commonweal.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "remark")
public class Remark {
    @TableId(value = "remark_num",type = IdType.AUTO)
    private Long remarkNum;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "content")
    private String content;
    @TableField(value = "admin_name")
    private String adminName;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
