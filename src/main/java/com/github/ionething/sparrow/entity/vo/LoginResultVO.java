package com.github.ionething.sparrow.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResultVO {

    private String username;

    private String token;

}
