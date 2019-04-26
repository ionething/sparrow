package com.github.ionething.sparrow.framework.common;

import org.springframework.http.HttpStatus;

public class ResultConstants {

    public static final int CODE_SUCCESS = HttpStatus.OK.value();

    public static final int CODE_FAIL = HttpStatus.BAD_REQUEST.value();

    public static final int CODE_BAD_REQUEST = HttpStatus.BAD_REQUEST.value();

    public static final int CODE_UNAUTHORIZED = HttpStatus.UNAUTHORIZED.value();

    public static final int CODE_NOT_FIND = HttpStatus.NOT_FOUND.value();

    public static final int CODE_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public static final String MESSAGE_SUCCESS = "处理成功";

    public static final String MESSAGE_BAD_REQUEST = "抱歉，系统无法理解你的请求";

    public static final String MESSAGE_UNAUTHORIZED = "抱歉，身份认证失效";

    public static final String MESSAGE_NOT_FIND = "抱歉，你请求的资源不存在";

    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "抱歉，系统暂时无法处理你的请求，请联系客服";

    public static final String MESSAGE_UNKNOWN = "未知错误";

    public static final String MESSAGE_LOGIN_ERROR = "用户名或者密码错误";
}
