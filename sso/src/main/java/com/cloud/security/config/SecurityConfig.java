package com.cloud.security.config;

import com.cloud.security.filter.JWTAuthenticationFilter;
import com.cloud.security.handler.JWTLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author fjj
 * @date 2020/6/26 9:22
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 无需权限校验直接放行的路径
    @Value("${spring.auth.path}")
    private String[] PATH_PASS;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                // 允许跨域访问
                .cors()
                //关闭打开的csrf保护
                .and().csrf().disable()
                // 开始请求权限配置
                .authorizeRequests()
                // 无需权限校验直接放行的路径
                .antMatchers(PATH_PASS).permitAll()
                // 尚未匹配的任务URL需要进行用户身份认证
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/auth/logout")
                .logoutSuccessHandler(new JWTLogoutSuccessHandler())
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }


}
