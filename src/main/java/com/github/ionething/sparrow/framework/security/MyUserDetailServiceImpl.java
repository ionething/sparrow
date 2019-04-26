package com.github.ionething.sparrow.framework.security;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.ionething.sparrow.dao.UserDao;
import com.github.ionething.sparrow.entity.model.User;
import com.github.ionething.sparrow.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        return new MyUserDetail(user.getUserName(), user.getPassword(), Collections.emptyList());
    }

}
