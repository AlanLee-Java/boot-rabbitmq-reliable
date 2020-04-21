package com.alanlee.exception;

import com.alanlee.common.ResponseCode;
import com.alanlee.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MyControllerAdvice {

    @ExceptionHandler(ServiceException.class)
    public ServerResponse serviceExceptionHandler(ServiceException se) {
        return ServerResponse.error(se.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ServerResponse exceptionHandler(Exception e) {
        log.error("Exception: ", e);
        if (e instanceof BindException || e instanceof MethodArgumentNotValidException) {
            //参数校验异常处理
            BindingResult bindingResult = null;
            if (e instanceof BindException) {
                BindException bindException = (BindException) e;
                bindingResult = bindException.getBindingResult();
            }else {
                MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
                bindingResult = methodArgumentNotValidException.getBindingResult();
            }

            //表单提交参数校验不通过
            return ServerResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ServerResponse.error(ResponseCode.SERVER_ERROR.getMsg());
    }

}
