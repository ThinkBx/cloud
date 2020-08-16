package com.cloud.util;

import com.cloud.constant.BaseConstants;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author fjj
 * @date 2020/6/3 22:34
 */
public class ToolMessage {

    /**
     * 根据消息键和参数 获取消息
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        MessageSource messageSource = ToolSpring.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, locale);
    }

    /**
     * 设置语言环境
     * @param request
     */
    public static void setLang(HttpServletRequest request){
        String lang = request.getParameter(BaseConstants.I18N_LANG);
        if (lang != null) {
            Locale locale = StringUtils.parseLocaleString(lang);
            WebUtils.setSessionAttribute(request,
                    SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            LocaleContextHolder.setLocale(locale, true);
        }
    }

}
