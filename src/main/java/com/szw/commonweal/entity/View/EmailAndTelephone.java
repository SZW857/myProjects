package com.szw.commonweal.entity.View;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("emails_and_telephone")
public class EmailAndTelephone {
  String email;
  String telephone;
}
