package com.groundhog.utils.exception;

import java.text.MessageFormat;

/**
 * @Author: guotianyu
 * @Date: 2021/6/24
 * @Description:
 */
public class DggUtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public DggUtilException() {
        super();
    }

    public DggUtilException(String message) {
        super(message);
    }

    public DggUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public DggUtilException(Throwable cause) {
        super(cause);
    }

    protected DggUtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DggUtilException(Throwable cause, String pattern, Object... params) {
        super(MessageFormat.format(pattern, params), cause);
    }
}
