package com.github.ionething.sparrow.framework.security;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1861414467704843340L;

    private Long userId;

    private List<String> roles;
}
