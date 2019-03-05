package com.github.ionething.sparrow.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @NotNull
    private Long id;

    private String userName;

    private String password;

    private String passwordSalt;

    @TableLogic
    private Byte deleted;

    private Byte valid;

    private Date createAt;

    private Date updateAt;

    @Version
    private Integer version;
}
