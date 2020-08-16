package com.cloud.ucenter.service.impl;

import com.cloud.base.BaseResult;
import com.cloud.constant.BaseConstants;
import com.cloud.db.bean.UserBean;
import com.cloud.db.mapper.UserMapper;
import com.cloud.db.service.impl.BaseServiceImpl;
import com.cloud.ucenter.bean.DTO.RegisterDTO;
import com.cloud.ucenter.constant.UserConstants;
import com.cloud.ucenter.service.IUserService;
import com.cloud.util.ToolDate;
import com.cloud.util.ToolMessage;
import com.cloud.util.ToolSnowFlake;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author fjj
 * @date 2020/6/25 21:55
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserBean> implements IUserService {

    @Override
    public BaseResult register(RegisterDTO register) {
        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(register, userBean);

        // 通过系统变量获取机房ID和机器ID
        int datacenterId = System.getenv("datacenterId") == null ? 1 : Integer.valueOf(System.getenv("datacenterId"));
        int machineId = System.getenv("machineId") == null ? 1 : Integer.valueOf(System.getenv("machineId"));
        ToolSnowFlake snowFlake = new ToolSnowFlake(datacenterId, machineId);
        userBean.setId(snowFlake.getNextId());

        // 密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userBean.setPassWord(encoder.encode(userBean.getPassWord()));

        userBean.setDataState(BaseConstants.DEL_FLAG_NORMAL);
        userBean.setUserStatus(UserConstants.USER_STATUS_OK);
        userBean.setUpdateTime(ToolDate.getSysDate());
        userBean.insert();
        return BaseResult.success(ToolMessage.message("i18n.operation.success"), userBean);
    }
}
