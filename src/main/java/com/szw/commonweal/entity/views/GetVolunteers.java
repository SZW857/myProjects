package com.szw.commonweal.entity.views;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("getVolunteers")
public class GetVolunteers {
    String idCard ;
    @TableId(type= IdType.NONE)
    String userId;
    String email;
    String verifyStatus;
    String telephone;
}
