package com.cloud.ucenter.service;

import com.cloud.base.BaseResult;
import com.cloud.db.bean.UserBean;
import com.cloud.db.service.IBaseService;
import com.cloud.ucenter.bean.DTO.RegisterDTO;

/**
 * 用户中心
 *
 * @author fjj
 * @date 2020/5/26 23:20
 */
public interface IUserService extends IBaseService<UserBean> {

    /**
     * 用户注册
     * @param register
     * @return
     */
    BaseResult register(RegisterDTO register);

}
