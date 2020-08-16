package com.cloud.ucenter.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 设置请求头(header参数)
 * feign 调用微服务转发不了request请求头和参数问题
 * @author fjj
 * @date 2020/5/25 17:59
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void apply(RequestTemplate template) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> requestHeaderNames = request.getHeaderNames();
            if (requestHeaderNames != null) {
                while (requestHeaderNames.hasMoreElements()) {
                    String name = requestHeaderNames.nextElement();
                    String values = request.getHeader(name);
                    template.header(name, values);
                }
            }
            Enumeration<String> bodyNames = request.getParameterNames();
            StringBuilder body = new StringBuilder();
            if (bodyNames != null) {
                while (bodyNames.hasMoreElements()) {
                    String name = bodyNames.nextElement();
                    String values = request.getParameter(name);
                    body.append(name).append("=").append(values).append("&");
                }
            }
            if (body.length() != 0) {
                body.deleteCharAt(body.length() - 1);
                template.body(body.toString());
                logger.info("feign interceptor body:{}", body.toString());
            }
        } catch (Exception e) {
            logger.error("feign interceptor Exception：-----", e);
        }

    }
}