package com.cloud.security.filter;

import com.cloud.db.bean.UserBean;
import com.cloud.tool.ToolAES;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fjj
 * @date 2020/6/26 16:10
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;

    /**
     * 配置登录路径
     * @param authenticationManager
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            //1、从输入流中获取到登录的信息
            UserBean userBean = new ObjectMapper().readValue(request.getInputStream(), UserBean.class);
            //2、校验验证码 TODO

            //3、解密用户信息
            userBean = ToolAES.decryptUserBean(userBean);
            // 4、校验用户密码错误次数 TODO

            //5、设置用户是否记住我，默认不记住
            rememberMe.set(userBean.getRememberMe());
            //6、封装到token中提交
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userBean.getUserName(),userBean.getPassWord()));
        }catch (IOException e){
            e.printStackTrace();
        }

//        authenticationManager.authenticate()

        return super.attemptAuthentication(request, response);
    }
}
