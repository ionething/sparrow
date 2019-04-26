package com.github.ionething.sparrow.framework.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final static String HEADER = "Authorization";

    private final static String HEADER_PREFIX = "Bearer ";

    public final static String KEY_PREFIX = "login:user:";

    @Autowired
    private StringRedisTemplate template;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HEADER);
        if(!StringUtils.isEmpty(token) && token.startsWith(HEADER_PREFIX)) {
            ValueOperations<String, String> ops = template.opsForValue();
            String key = KEY_PREFIX + token.substring(7);
            String value = ops.get(key);
            if (!StringUtils.isEmpty(value)) {
                UserInfo userInfo = JSONObject.parseObject(value, UserInfo.class);
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(userInfo.getUserId(), "N/A", AuthorityUtils.NO_AUTHORITIES)
                );
                ops.set(key, value, 1, TimeUnit.DAYS);
            }
        }
        filterChain.doFilter(request, response);
    }
}
