package com.szw.commonweal.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "attendance")
public class Attendance {
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "serial_num")
    private String serialNum;
    @TableField(value = "voucher")
    private String voucher;
    @TableField(value = "status")
    private Integer status;
    @TableField(value = "start_date")
    private Long startDate;
    @TableField(value = "finish_date")
    private Long finishDate;

}