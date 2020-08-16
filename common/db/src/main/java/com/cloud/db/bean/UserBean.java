package com.cloud.db.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户
 * @author fjj
 * @date 2020/5/26 23:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ucenter_user")
public class UserBean extends BaseBean<UserBean> {

    //用户名
    private String userName;
    //用户密码
    private String passWord;
    //用户手机号码
    private String userPhone;
    //用户邮箱
    private String userEmail;
    // 用户状态
    private Integer userStatus;
    // 修改时间
    private Date updateTime;
    // 数据状态
    private Integer dataState;

    @TableField(exist = false)
    private Integer rememberMe = 2;



}
