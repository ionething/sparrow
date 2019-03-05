package com.github.ionething.sparrow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.ionething.sparrow.dao.UserDao;
import com.github.ionething.sparrow.entity.model.User;
import com.github.ionething.sparrow.framework.bean.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "User", description = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserDao userDao;

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResultEntity<User> select(@PathVariable("id") Long id) {
        return new ResultEntity<>(userDao.findById(id));
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
