package com.szw.commonweal.entity.views;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("emails_and_telephone")
public class DistinctInformation {
  private  String email;
  private  String telephone;
  @TableId
  private  String idCard;
}
