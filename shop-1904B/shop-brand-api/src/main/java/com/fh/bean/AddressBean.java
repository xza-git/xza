package com.fh.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_address")
public class AddressBean implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String name;
    private String phone;
    @TableField(value = "email")
    private String email;
    private String address;
    private int type;
    @TableField("addTime")
    private Date addTime;
}
