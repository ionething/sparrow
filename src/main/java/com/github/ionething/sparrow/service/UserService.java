package com.github.ionething.sparrow.service;

import com.github.ionething.sparrow.entity.model.User;

public interface UserService {

    User getUserByUsername(String username);

}
