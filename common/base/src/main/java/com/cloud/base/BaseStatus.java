package com.cloud.base;

/**
 * 操作状态
 * @author fjj
 * @date 2020/6/3 22:11
 */
public interface BaseStatus {

    Integer STATUS_SUCCESS = 200;   // 消息成功
    Integer STATUS_OTHER = 401;     // 一般错误
    Integer STATUS_FAIL = 500;      // 服务器错误
    Integer STATUS_NOT_FIND = 404;  // 访问地址不存在


}
