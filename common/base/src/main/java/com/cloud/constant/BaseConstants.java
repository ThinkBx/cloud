package com.cloud.constant;

/**
 * 共有常量
 * @author fjj
 * @date 2020/5/26 22:30
 */
public interface BaseConstants {

    /**
     * 用户认证信息
     */
    String TOKEN_HEADER = "X-Auth-Token";
    /**
     * token前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 国际化参数
     */
    String I18N_LANG = "lang";

    /**
     * AES加密key
     */
    String AES_KEY = "FTCLOUD_20190320";

    /**
     * 删除标记（0：正常；1：删除）
     */
    Integer DEL_FLAG_NORMAL = 0;
    Integer DEL_FLAG_DELETE = 1;

}
