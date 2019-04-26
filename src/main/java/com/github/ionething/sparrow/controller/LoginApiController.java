package com.github.ionething.sparrow.controller;

import com.alibaba.fastjson.JSON;
import com.github.ionething.sparrow.entity.model.User;
import com.github.ionething.sparrow.entity.vo.LoginResultVO;
import com.github.ionething.sparrow.framework.bean.ResultEntity;
import com.github.ionething.sparrow.framework.common.ResultConstants;
import com.github.ionething.sparrow.framework.security.TokenFilter;
import com.github.ionething.sparrow.framework.security.UserInfo;
import com.github.ionething.sparrow.service.UserService;
import com.google.common.collect.Maps;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate template;

    @PostMapping("/login")
    public ResultEntity<LoginResultVO> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            // 简单处理
            if(password.equals(user.getPassword())) {
                String token = UUID.randomUUID().toString();
                ValueOperations<String, String> ops = template.opsForValue();

                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(user.getId());

                ops.set(TokenFilter.KEY_PREFIX + token, JSON.toJSONString(userInfo), 1, TimeUnit.DAYS);
                return new ResultEntity<>(new LoginResultVO(user.getUserName(), token));
            }
        }
        throw new UsernameNotFoundException(ResultConstants.MESSAGE_LOGIN_ERROR);
    }

    @PostMapping("/hello")
    public ResultEntity hello() {
        Map map = Maps.newHashMap();
        map.put("foo", "bar");
        return new ResultEntity(map);
    }

}
