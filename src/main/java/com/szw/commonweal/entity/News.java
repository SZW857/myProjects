package com.szw.commonweal.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @TableId(value = "newsId",type = IdType.AUTO)
    private Integer newsId;
    private String title;
    private String content;
    private String type;
    private Date date;
    private String adminId;
}
