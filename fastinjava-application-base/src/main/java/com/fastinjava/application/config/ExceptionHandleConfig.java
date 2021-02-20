package com.fastinjava.application.config;

import com.fastinjava.framework.common.res.CommonResponseCode;
import com.fastinjava.framework.common.res.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice("com.fastinjava")
public class ExceptionHandleConfig {

    @ExceptionHandler(Exception.class)
    public JsonResult<String> exception(Exception e) {
        log.info("捕捉到异常：[{}]", e.getMessage(), e);
        return JsonResult.<String>builder().failure(e.getMessage()).build();
    }
    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return JsonResult.<String>builder().failure(e.getMessage()).build();
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public JsonResult<String> handleValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return JsonResult.<String>builder().failure(e.getMessage()).build();
    }


    /**
     * 处理validation框架中的{@link MethodArgumentNotValidException}异常
     * <p>该异常一般出在用{@code @RequestBody}注解标记的参数校验，在对象中的参数有问题是抛出</p>
     *
     * @param e {@link MethodArgumentNotValidException}
     * @return {@link JsonResult}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<String> doNoValidExceptionHandler(MethodArgumentNotValidException e) {
        //默认捕获第一个不符合校验规则的错误信息
        return validationExceptionHandler(e.getBindingResult(), e.getMessage());
    }



    /**
     * 处理validation框架中的{@link ConstraintDeclarationException}异常
     * <p>该异常一般出在用{@code @RequestParam}注解标记的参数校验</p>
     *
     * @param e {@link ConstraintDeclarationException}
     * @return {@link JsonResult}
     */
    @ExceptionHandler(ConstraintDeclarationException.class)
    public JsonResult<String> doNoValidExceptionHandler(ConstraintDeclarationException e) {
        String errorMsg = e.getMessage();
        log.info("捕捉到参数校验异常： [{}]", errorMsg, e);
        return JsonResult.<String>builder()
                .failure(CommonResponseCode.PARAMS_VALID_ERROR)
                .build();
    }

    /**
     * 处理validation框架中的{@link BindException}异常
     * <p>该异常一般出在用{@code @RequestBody}注解标记的参数校验，在对象嵌套类型参数有问题时抛出</p>
     *
     * @param e {@link BindException}
     * @return {@link JsonResult}
     */
    @ExceptionHandler(BindException.class)
    public JsonResult<String> doNoValidExceptionHandler(BindException e) {
        return validationExceptionHandler(e.getBindingResult(), e.getMessage());
    }

    /**
     * 处理validation框架的异常
     *
     * @param bindingResult {@link BindingResult} 异常绑定的对象信息
     * @param message       异常信息
     * @return {@link JsonResult}
     */
    private JsonResult<String> validationExceptionHandler(BindingResult bindingResult, String message) {
        //默认捕获第一个不符合校验规则的错误信息
        //错误字段对象
        FieldError fieldError = bindingResult.getFieldError();
        //错误字段名
        String fieldName = fieldError == null ? "" : fieldError.getField();
        //具体错误信息
        String defaultMessage = fieldError == null ? "" : fieldError.getDefaultMessage();
        String errorMsg = "[" + fieldName + "]" + defaultMessage;
        log.info("捕捉到参数校验异常： [{}]", message);
        return JsonResult.<String>builder().failure(CommonResponseCode.PARAMS_VALID_ERROR.getCode(), defaultMessage).build();
    }


}
