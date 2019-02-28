package com.github.ionething.sparrow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.ionething.sparrow.dao.UserDao;
import com.github.ionething.sparrow.entity.model.User;
import com.github.ionething.sparrow.framework.bean.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/{id}")
    public ResultEntity<User> select(@PathVariable("id") Long id) {
        return new ResultEntity<>(userDao.selectById(id));
    }

    @PostMapping("/list")
    public ResultEntity<IPage<User>> list() {
        return new ResultEntity<>(userDao.selectPage(new Page<>(2, 3), null));
    }

    @PostMapping("/add")
    public ResultEntity add(@RequestBody User user) {
        int result = userDao.insert(user);
        return new ResultEntity();
    }

    @PostMapping("/delete")
    public ResultEntity delete(@RequestBody @Valid User user) {
        int result = userDao.deleteById(user.getId());
        return new ResultEntity();
    }

    @PostMapping("/update")
    public ResultEntity update(@RequestBody @Valid User user) {
        int result = userDao.updateById(user);
        return new ResultEntity();
    }

}
