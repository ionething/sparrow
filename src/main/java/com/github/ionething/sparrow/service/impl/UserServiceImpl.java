package com.github.ionething.sparrow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.ionething.sparrow.dao.UserDao;
import com.github.ionething.sparrow.entity.model.User;
import com.github.ionething.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name", username);
        User user = userDao.selectOne(wrapper);
        return user;
    }
}
