package com.cx.edu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandleAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData> bindExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldError().getDefaultMessage();
        log.warn(message);
        return ResponseEntity.badRequest().body(new ResponseData("70000", message));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseData> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.warn("参数错误", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData(ResultEnum.METHOD_NOT_SUPPORTED));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseData> handler(Exception e) {
        log.error("服务器错误", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData(ResultEnum.ERROR));
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ResponseData> businessExceptionhandler(BusinessException exception) {
        log.warn(exception.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseData> httpMessageConvertExceptionHanlder(HttpMessageNotReadableException e) {
        log.warn("参数错误", e);
        return ResponseEntity.badRequest().body(new ResponseData(ResultEnum.MESSAGE_NOT_READABLE));
    }

    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity<ResponseData> missingPathVariableExceptionHanlder(MissingPathVariableException e) {
        log.warn("参数错误", e);
        return ResponseEntity.badRequest().body(new ResponseData(ResultEnum.MISSING_PATH_VARIABLE));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseData> requestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.warn("参数错误", e);
        return ResponseEntity.badRequest().body(new ResponseData(ResultEnum.MISSING_REQUEST_PARAM));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseData> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        log.warn("参数错误", e);
        return ResponseEntity.badRequest().body(new ResponseData(ResultEnum.ARGUMENT_TYPE_MISMATCH));
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseData> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        log.warn("参数错误", e);
        return ResponseEntity.badRequest().body(new ResponseData(ResultEnum.MEDIA_TYPE_NOT_SUPPORTED));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ResponseData> noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        log.warn("no handler found: {}", e.getRequestURL());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData(ResultEnum.NO_HANDLER_NOT_FOUND));
    }

//    @ExceptionHandler(value = DataValidationException.class)
//    public ResponseEntity<ResponseData> dataValidationExceptionHandler(DataValidationException e) {
//        log.warn("invalid request parameters");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData("400", e.getMessage()));
//    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<ResponseData> bindExceptionHandler(BindException e) {
        log.warn("invalid request parameters");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData("400", e.getBindingResult().getAllErrors().iterator().next().getDefaultMessage()));
    }

}
