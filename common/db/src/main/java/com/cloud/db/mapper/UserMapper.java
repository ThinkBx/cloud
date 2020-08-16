package com.cloud.db.mapper;

import com.cloud.db.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fjj
 * @date 2020/6/25 21:58
 */
@Mapper
public interface UserMapper extends SuperMapper<UserBean> {

}
