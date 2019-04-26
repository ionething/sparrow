package com.github.ionething.sparrow.framework.handler;

import com.alibaba.fastjson.JSON;
import com.github.ionething.sparrow.framework.exception.BussinessException;
import com.github.ionething.sparrow.framework.bean.ResultEntity;
import com.github.ionething.sparrow.framework.common.ResultConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultEntity handle(HttpServletRequest request, Exception e) {
        ResultEntity result;
        if (e instanceof BussinessException) {
            result = new ResultEntity(ResultConstants.CODE_FAIL, e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            // 404
            result = new ResultEntity(ResultConstants.CODE_NOT_FIND, ResultConstants.MESSAGE_NOT_FIND);
        } else if (e instanceof UsernameNotFoundException) {
            result = new ResultEntity(ResultConstants.CODE_FAIL, ResultConstants.MESSAGE_LOGIN_ERROR);
        } else if (e instanceof ServletException) {
            result = new ResultEntity(ResultConstants.CODE_BAD_REQUEST, ResultConstants.MESSAGE_BAD_REQUEST);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            String message = ResultConstants.MESSAGE_UNKNOWN;
            List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
            if (objectErrors.size() > 0) {
                ObjectError objectError = objectErrors.get(0);

                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    message = "参数[" + fieldError.getField() + "]" + fieldError.getDefaultMessage();
                }

            }
            result = new ResultEntity(ResultConstants.CODE_BAD_REQUEST, message);
        } else {
            // 500
            log.error("接口 [" + request.getRequestURI() + "] 内部错误：{}", e.getMessage());
            result = new ResultEntity(ResultConstants.CODE_INTERNAL_SERVER_ERROR, ResultConstants.MESSAGE_INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
