package com.szw.commonweal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Help implements Serializable {
    @TableId(value = "help_num",type = IdType.AUTO)
    private Long helpNum;
    @TableField(value = "start_date")
    @DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
    private String startDate;
    @TableField(value = "finish_date")
    @DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
    private String finishDate;
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
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "adminId")
    private String adminId;
    @TableField(value = "remaining")
    private Integer remaining;
}
