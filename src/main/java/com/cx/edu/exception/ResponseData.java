package com.cx.edu.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(content = JsonInclude.Include.NON_NULL, value = JsonInclude.Include.NON_EMPTY)
@Data
public class ResponseData implements Serializable {


    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private Object data;

    public ResponseData() {
        this.code = "0";
        this.message = "OK";
    }


    public ResponseData(ResultEnum resultEnum) {
        this.code = resultEnum.code();
        this.message = resultEnum.message();
    }

    public ResponseData(Object data) {
        this.code = "0";
        this.message = "OK";
        this.data = data;
    }

    public ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
