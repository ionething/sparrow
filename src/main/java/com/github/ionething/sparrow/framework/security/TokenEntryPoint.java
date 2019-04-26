package com.github.ionething.sparrow.framework.security;

import com.alibaba.fastjson.JSON;
import com.github.ionething.sparrow.framework.bean.ResultEntity;
import com.github.ionething.sparrow.framework.common.ResultConstants;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class TokenEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        if (authException instanceof BadCredentialsException) {
            // 身份认证未通过
        }
        ResultEntity result = new ResultEntity(ResultConstants.CODE_UNAUTHORIZED, ResultConstants.MESSAGE_UNAUTHORIZED);
        response.getWriter().append(JSON.toJSONString(result));
    }
}

