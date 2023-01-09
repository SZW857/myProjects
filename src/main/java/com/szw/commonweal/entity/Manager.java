package com.szw.commonweal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager<V> {
    private String adminName;
    private int age;
    @TableId(type = IdType.NONE)//指定主键
    private String adminId;
    private String passwd;

}
