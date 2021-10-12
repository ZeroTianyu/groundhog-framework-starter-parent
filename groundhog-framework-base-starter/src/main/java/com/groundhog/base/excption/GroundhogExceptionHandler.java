package com.groundhog.base.excption;


import com.groundhog.base.model.vo.GroundhogResult;
import com.groundhog.base.model.vo.ResultCode;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 全局异常信息处理类
 *
 * @author hl-king
 **/
@Slf4j
@ControllerAdvice
public class GroundhogExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = HystrixRuntimeException.class)
    public GroundhogResult<Object> hystrixRuntimeException(HystrixRuntimeException e) throws AbstractGroundhogException {
        if (e.getCause() instanceof AbstractGroundhogException) {
            return customExceptionHandler((AbstractGroundhogException) e.getCause());
        }
        GroundhogResult<Object> failed = new GroundhogResult();
        log.error("HystrixRuntimeException =====>> ", e);
        failed.setResult(ResultCode.FEIGN_FAILURE);
        return failed;
    }

    @ResponseBody
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public GroundhogResult<Object> nonTransientDataAccessException(DataIntegrityViolationException e) {
        GroundhogResult<Object> failed = new GroundhogResult<>();
        log.error("DataIntegrityViolationException =====>> ", e);
        failed.setResult(ResultCode.VALIDATE_ERROR);
        return failed;
    }

    /**
     * 处理参数验证异常
     * <p>
     *
     * @Size @RequestParam("name") String name
     * </p>
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public GroundhogResult<Object> resolveConstraintViolationException(ConstraintViolationException ex) {
        log.error("参数验证异常 =====>> ", ex);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            List<String> messages = new LinkedList<>();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                messages.add(constraintViolation.getMessage());
            }
            return GroundhogResult.failed(ResultCode.PARAMETER_ERROR.getCode(), String.join(",", messages));
        }
        return GroundhogResult.failed(ResultCode.UNKNOW_ERROR);
    }

    /**
     * 处理po实体类验证异常
     * <p>
     *
     * @Valid @RequestBody VO params
     * </p>
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GroundhogResult<Object> resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException =====>> ", ex);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringJoiner joiner = new StringJoiner(",");
            for (ObjectError objectError : objectErrors) {
                joiner.add(objectError.getDefaultMessage());
            }

            return GroundhogResult.failed(ResultCode.UNKNOW_ERROR.getCode(), String.join("", joiner.toString()));
        }
        return GroundhogResult.failed(ResultCode.UNKNOW_ERROR);
    }


    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public GroundhogResult<Object> defaultBindExceptionHandler(BindException ex) {
        log.error("BindException =====>> ", ex);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringJoiner joiner = new StringJoiner(",");
            for (ObjectError objectError : objectErrors) {
                joiner.add(objectError.getDefaultMessage());
            }
            return GroundhogResult.validateFailed(String.join(",", joiner.toString()));
        }
        return GroundhogResult.validateFailed();
    }

    /**
     * API统一异常处理
     **/

    @ResponseBody
    @ExceptionHandler(value = AbstractGroundhogException.class)
    public GroundhogResult<Object> customExceptionHandler(AbstractGroundhogException e) {
        log.error("AbstractCrispsException =====>> ", e);
        try {
            Throwable innerEx = e.getCause();
            while (innerEx != null) {
                if (innerEx.getCause() == null) {
                    break;
                }
                innerEx = innerEx.getCause();
            }
            int code = e.getCode() == null ? ResultCode.UNKNOW_ERROR.getCode() : e.getCode();
            String message = e.getMessage() == null ? ResultCode.UNKNOW_ERROR.getMessage() : e.getMessage();
            return GroundhogResult.failed(code, message, false);
        } catch (Exception ex) {
            log.error("", ex);
            return GroundhogResult.failed(ResultCode.UNKNOW_ERROR);
        }
    }

    /**
     * API统一异常处理
     **/
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public GroundhogResult<Object> defaultErrorHandler(Exception e) {
        GroundhogResult<Object> failed = new GroundhogResult<>();
        log.error("统一异常处理 =====>> ", e);

        // 请求方法“HEAD”不支持
        if (e instanceof HttpMediaTypeException) {
            failed.setResult(ResultCode.NOT_FOUND);
            failed.setMessage("请求方法“HEAD”不支持");
            return failed;
        }

        // 参数类型不匹配得
        if (e instanceof MethodArgumentTypeMismatchException) {
            failed.setResult(ResultCode.PARAMETER_ERROR);
            failed.setMessage("参数类型不正确");
            return failed;
        }

        // 路径不存在异常处理
        if (e instanceof NoHandlerFoundException) {
            failed.setResult(ResultCode.NOT_FOUND);
            return failed;
        }

        // 请求方式错误异常处理
        if (e instanceof HttpRequestMethodNotSupportedException) {
            failed.setResult(ResultCode.NOT_FOUND);
            return failed;
        }

        // 请求参数有误异常处理
        if (e instanceof MissingServletRequestParameterException) {
            failed.setResult(ResultCode.PARAMETER_ERROR);
            return failed;
        }

        // 请求参数转换异常处理
        if (e instanceof HttpMessageNotReadableException) {
            failed.setResult(ResultCode.PARAMETER_ERROR);
            return failed;
        }

        //参数绑定异常处理
        if (e instanceof BindException) {
            failed.setResult(ResultCode.PARAMETER_ERROR);
            return failed;
        }

        //无权访问
        if (e instanceof AccessDeniedException) {
            failed.setResult(ResultCode.ACCESS_DENIED_ERROR);
            return failed;
        }

        //无权访问
        if (e instanceof GroundhogException) {
            failed.setCode(((GroundhogException) e).getCode());
            failed.setMessage(e.getMessage());
            return failed;
        }
        failed.setResult(ResultCode.UNKNOW_ERROR);
        return failed;
    }
}
