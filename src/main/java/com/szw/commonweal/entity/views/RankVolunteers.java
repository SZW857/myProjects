package com.szw.commonweal.entity.views;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("rankVolunteer")
public class RankVolunteers implements Serializable {
   private Integer age;
   private Integer signNum;
   private String sex;
   private Integer verifyStatus;
   private String email;
   @TableId(type = IdType.NONE)
   private String userId;
}
