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
@TableName(value = "reply")
public class Reply {
    @TableId(value = "reply_num",type = IdType.AUTO)
    private Long replyNum;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "content")
    private String content;
    @TableField(value = "remark_num")
    private Long remarkNum;
    @TableField(value = "speaker")
    private String speaker;
    @TableField(value = "admin_name")
    private String adminName;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
