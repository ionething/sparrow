package com.github.ionething.sparrow.framework.bean;

import com.github.ionething.sparrow.framework.common.ResultConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ResultEntity<T> {

    private int code;

    private String message;

    private T data;

    public ResultEntity() {
        this.code = ResultConstants.CODE_SUCCESS;
        this.message = ResultConstants.MESSAGE_SUCCESS;
    }

    public ResultEntity(T data) {
        this.code = ResultConstants.CODE_SUCCESS;
        this.message = ResultConstants.MESSAGE_SUCCESS;
        this.data = data;
    }

    public ResultEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
