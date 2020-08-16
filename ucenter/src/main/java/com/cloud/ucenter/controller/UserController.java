package com.cloud.ucenter.controller;

import com.cloud.base.BaseController;
import com.cloud.base.BaseResult;
import com.cloud.ucenter.bean.DTO.RegisterDTO;
import com.cloud.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户中心
 * @author fjj
 * @date 2020/5/26 22:22
 */
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping("/reg")
    public BaseResult reg(@RequestBody RegisterDTO register){
        return userService.register(register);
    }
}
