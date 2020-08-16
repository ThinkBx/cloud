package com.cloud.base;

import com.cloud.constant.BaseConstants;
import com.cloud.util.ToolDate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 控制器基类
 * @author fjj
 * @date 2020/5/26 22:24
 */
@Slf4j
public class BaseController {

    @Autowired
    public HttpServletRequest request;

    /**
     * 获取认证信息TOKEN
     * @return
     */
    public String getAuthorization(){
        String authorization = request.getHeader(BaseConstants.TOKEN_HEADER);
        if (StringUtils.isNotBlank(authorization)){
            return null;
        }
        authorization = authorization.replace(BaseConstants.TOKEN_PREFIX, "");
        return authorization;
    }

    /**
     * 防止XSS攻击
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(ToolDate.parseDate(text));
            }
        });
        // Timestamp 类型转换
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Date date = ToolDate.parseDate(text);
                setValue(date==null?null:new Timestamp(date.getTime()));
            }
        });
    }

}
