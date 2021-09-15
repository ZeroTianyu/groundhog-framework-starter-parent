package com.groundhog.base.excption;

import com.groundhog.base.model.vo.ResultCode;

import java.text.MessageFormat;

/**
 * @author guotianyu
 */
public abstract class AbstractCrispsException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    private int code = 5000;
    private String message = "服务异常,请稍后再试!";

    public AbstractCrispsException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public AbstractCrispsException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public AbstractCrispsException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public AbstractCrispsException(Throwable cause) {
        super(cause);
    }

    public AbstractCrispsException(String message) {
        super(message);
        this.message = message;
    }

    public AbstractCrispsException() {
        super("crisps framework base异常");
    }

    public AbstractCrispsException(Throwable cause, String pattern, Object... params) {
        super(MessageFormat.format(pattern, params), cause);
        this.message = MessageFormat.format(pattern, params);
    }

    public AbstractCrispsException(String pattern, Object... params) {
        super(MessageFormat.format(pattern, params));
        this.message = MessageFormat.format(pattern, params);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}