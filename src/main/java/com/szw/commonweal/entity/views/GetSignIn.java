package com.szw.commonweal.entity.views;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.util.test.FixedSecureRandom;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "get_sign_in_volunteers")
public class GetSignIn implements Serializable {

    @TableField(value = "serial_num")
    private Long serialNum;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "voucher")
    private String voucher;
    @TableField(value = "status")
    private String status;
    @TableField(value = "start_date")
    private String startDate;
    @TableField(value = "finish_date")
    private String  finishDate;













    //    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")

}
