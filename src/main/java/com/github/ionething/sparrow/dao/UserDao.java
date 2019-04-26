package com.github.ionething.sparrow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.ionething.sparrow.entity.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "users")
public interface UserDao extends BaseMapper<User> {

    @Cacheable()
    User findById(@Param("id") Long id);

}
