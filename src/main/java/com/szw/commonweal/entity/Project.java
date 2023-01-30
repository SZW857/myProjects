package com.szw.commonweal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "project")
public class Project {
    @TableId(type = IdType.AUTO)
    private String serialNum;
    @TableField(value = "start_date")
    @DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
    private Date startDate;
    @TableField(value = "finish_date")
    @DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
    private Date finishDate;
    @TableField(value = "people_num")
    private Integer peopleNum;
    @TableField(value = "title")
    private String title;
    @TableField(value = "content")
    private String content;
    @TableField(value = "type")
    private String type;
    @TableField(value = "imageUrl")
    private String imageUrl;
    @TableField(value = "email")
    private String email;
    @TableField(value = "telephone")
    private String telephone;
    @TableField(value = "contact")
    private String contact;
    @TableField(value = "adminId")
    private String adminId;
    @TableField(value = "remaining")
    private Integer remaining;


}
