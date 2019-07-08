package com.cx.edu.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3915121934673642632L;

    private String code;
    private String message;

    public BusinessException(ResultEnum resultEnum) {
        this.code = resultEnum.code();
        this.message = resultEnum.message();
    }

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
