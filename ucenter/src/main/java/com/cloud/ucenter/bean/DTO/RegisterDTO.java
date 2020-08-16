package com.cloud.ucenter.bean.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fjj
 * @date 2020/6/4 13:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    //用户账号
    private String userName;
    //用户密码
    private String passWord;
    //用户手机号码
    private String userPhone;
    //用户邮箱
    private String userEmail;

}
