package com.szw.commonweal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "project")
public class Project {
    @TableId(type = IdType.AUTO)
    private String serialNum;
    @TableField(value = "start_date")
    private Date startDate;
    @TableField(value = "p_status")
    private String pStatus;
    @TableField(value = "finish_date")
    private Date finishDate;
    @TableField(value = "sponsor")
    private String sponsor;
    @TableField(value = "people_num")
    private Integer peopleNum;
    @TableField(value = "state")
    private String state;
    @TableField(value = "title")
    private String title;
    @TableField(value = "content")
    private String content;
    @TableField(value = "sign_status")
    private String signStatus;
    @TableField(value = "sign_num")
    private Integer signNum;
    @TableField(value = "adminId")
    private String adminId;
    @TableField(value = "userId")
    private String userId;
}
