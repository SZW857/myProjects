package com.szw.commonweal.entity.View;

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
    String userId;
    String email;
    String verifyStatus;
    String telephone;
}
