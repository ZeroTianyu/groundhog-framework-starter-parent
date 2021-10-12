package com.groundhog.base.excption;

import com.groundhog.base.model.vo.ResultCode;
import com.groundhog.base.utils.JacksonUtil;
import com.groundhog.base.constant.ResultPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

public final class Asserts {

    private static final Logger logger = LoggerFactory.getLogger(Asserts.class);

    /**
     * 构造异常信息
     *
     * @param errorMsg
     * @param <T>
     */
    public final static <T extends RuntimeException> boolean build(String errorMsg) {
        return build(GroundhogBizException.class, errorMsg);
    }

    /**
     * 构造异常信息
     *
     * @param errorMsg
     * @param <T>
     */
    public final static <T extends RuntimeException> boolean build(Class<T> exceptionClass, String errorMsg) {
        return getResult(false, exceptionClass, errorMsg == null ? "操作失败" : errorMsg);
    }

    public final static Object isOk(Map<String, Object> params) throws GroundhogBizException {
        Object code = params.get("code"), message = params.get("message");
        if (!isOk(code)) {
            if (logger.isErrorEnabled()) {
                logger.error("\n\n\n<== [ mark：{} ][ params：{} ] \n\n\n", "返回信息异常", JacksonUtil.toJSONString(params));
            }
            throw new GroundhogBizException(null == message ? String.valueOf(params.get("msg")) : String.valueOf(message));
        }
        return params.get("data");
    }

    public final static void isOk(Integer status, ResultCode resultCode) throws GroundhogBizException {
        isOk(status, resultCode.getCode(), resultCode.getMessage());
    }

    public final static void isOk(Integer code, String message) throws GroundhogBizException {
        if (null == code) {
            throw new GroundhogBizException(ResultPool.UNKNOW_ERROR_CODE, message);
        }
        if (!isOk(code)) {
            throw new GroundhogBizException(ResultPool.UNKNOW_ERROR_CODE_1 == code ? ResultPool.UNKNOW_ERROR_CODE : code, message);
        }
    }

    public final static void isOk(Integer status, Integer code, String message) throws GroundhogBizException {
        if (null == status) {
            throw new GroundhogBizException(ResultPool.UNKNOW_ERROR_CODE, message);
        }
        if (!isOk(code)) {
            throw new GroundhogBizException(ResultPool.UNKNOW_ERROR_CODE_1 == status ? code : status, message);
        }
    }

    public final static boolean isOk(Object code) {
        switch (String.valueOf(code)) {
            case "0":
                return true;
            case "200":
                return true;
            case "10000":
                return true;
            case "CODE_00":
                return true;
            default:
                return false;
        }
    }

    public final static void equals(int status, int code, String message) throws GroundhogBizException {
        if (status != code) {
            throw new GroundhogBizException(code, message);
        }
    }

    public final static void notEquals(Object param1, Object param2, int code, String message) throws GroundhogBizException {
        if (param1.equals(param2)) {
            throw new GroundhogBizException(code, message);
        }
    }

    public final static void notEquals(Object param1, Object param2, ResultCode resultCode) throws GroundhogBizException {
        if (param1.equals(param2)) {
            throw new GroundhogBizException(resultCode);
        }
    }

    public final static void equals(Object param1, Object param2, ResultCode resultCode) throws GroundhogBizException {
        if (!param1.equals(param2)) {
            throw new GroundhogBizException(resultCode);
        }
    }

    public final static void isTrue(boolean expression, int code, String message) throws GroundhogBizException {
        if (false == expression) {
            throw new GroundhogBizException(code, message);
        }
    }

    public final static void isTrue(boolean expression, ResultCode resultCode) throws GroundhogBizException {
        if (false == expression) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
    }

    public final static void isTrue(boolean expression) throws GroundhogBizException {
        if (false == expression) {
            throw new GroundhogBizException();
        }
    }

    public final static void isFalse(boolean expression, Integer code, String message) throws GroundhogBizException {
        if (true == expression) {
            throw new GroundhogBizException(code, message);
        }
    }

    public final static void isFalse(boolean expression, ResultCode resultCode) throws GroundhogBizException {
        if (true == expression) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
    }

    public final static void isFalse(boolean expression, ResultCode resultCode, String message) throws GroundhogBizException {
        if (true == expression) {
            throw new GroundhogBizException(resultCode.getCode(), message);
        }
    }

    public final static void isFalse(boolean expression) throws GroundhogBizException {
        if (true == expression) {
            throw new GroundhogBizException();
        }
    }

    public final static void isNull(Object object, int code, String message) throws GroundhogBizException {
        if (object != null) {
            throw new GroundhogBizException(code, message);
        }
    }

    public final static void isNull(Object object, ResultCode resultCode) throws GroundhogBizException {
        if (object != null) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
    }

    public final static void isNull(Object object) throws GroundhogBizException {
        if (object != null) {
            throw new GroundhogBizException();
        }
    }

    public final static <T> T isNotNull(T object, String message) throws GroundhogBizException {
        if (object == null) {
            throw new GroundhogBizException(message);
        }
        return object;
    }

    public final static <T> T isNotNull(T object, int code, String message) throws GroundhogBizException {
        if (object == null) {
            throw new GroundhogBizException(code, message);
        }
        return object;
    }

    public final static <T> T isNotNull(T object, AbstractGroundhogException exception) throws GroundhogBizException {
        if (object == null) {
            throw exception;
        }
        return object;
    }

    public final static <T> T isNotNull(T object, ResultCode resultCode) throws GroundhogBizException {
        if (object == null) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
        return object;
    }

    public final static <T> T isNotNull(T object) throws GroundhogBizException {
        if (object == null) {
            throw new GroundhogBizException();
        }
        return object;
    }

    public final static String isEmpty(String text, ResultCode resultCode) throws GroundhogBizException {
        if (notBlank(text)) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
        return text;
    }

    public final static String isEmpty(String text) throws GroundhogBizException {
        if (notBlank(text)) {
            throw new GroundhogBizException();
        }
        return text;
    }

    public final static String isBlank(String text, ResultCode resultCode) throws GroundhogBizException {
        if (notBlank(text)) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
        return text;
    }

    public final static String isBlank(String text) throws GroundhogBizException {
        if (notBlank(text)) {
            throw new GroundhogBizException();
        }
        return text;
    }

    public final static <T> Collection<T> isEmpty(Collection<T> collection, ResultCode resultCode) throws GroundhogBizException {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
        return collection;
    }

    public final static <T> Collection<T> isEmpty(Collection<T> collection) throws GroundhogBizException {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new GroundhogBizException();
        }
        return collection;
    }

    public final static <K, V> Map<K, V> isEmpty(Map<K, V> map, ResultCode resultCode) throws GroundhogBizException {
        if (!CollectionUtils.isEmpty(map)) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
        return map;
    }

    public final static <K, V> Map<K, V> isEmpty(Map<K, V> map) throws GroundhogBizException {
        if (!CollectionUtils.isEmpty(map)) {
            throw new GroundhogBizException();
        }
        return map;
    }

    public final static String isNotBlank(String text, String message) throws GroundhogBizException {
        if (blank(text)) {
            throw new GroundhogBizException(message);
        }
        return text;
    }

    public final static String isNotBlank(String text, int code, String message) throws GroundhogBizException {
        if (blank(text)) {
            throw new GroundhogBizException(code, message);
        }
        return text;
    }

    public final static String isNotBlank(String text, ResultCode resultCode) throws GroundhogBizException {
        if (blank(text)) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
        return text;
    }

    public final static String isNotBlank(String text, AbstractGroundhogException exception) throws GroundhogBizException {
        if (blank(text)) {
            throw exception;
        }
        return text;
    }

    public final static String isNotBlank(String text) throws GroundhogBizException {
        if (blank(text)) {
            throw new GroundhogBizException();
        }
        return text;
    }

    public final static <T> Collection<T> isNotEmpty(Collection<T> collection, int code, String message) throws GroundhogBizException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new GroundhogBizException(code, message);
        }
        return collection;
    }

    public final static <T> Collection<T> isNotEmpty(Collection<T> collection, ResultCode resultCode) throws GroundhogBizException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
        return collection;
    }

    public final static <T> Collection<T> isNotEmpty(Collection<T> collection) throws GroundhogBizException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new GroundhogBizException();
        }
        return collection;
    }


    public final static <K, V> Map<K, V> isNotEmpty(Map<K, V> map, int code, String message) throws GroundhogBizException {
        if (CollectionUtils.isEmpty(map)) {
            throw new GroundhogBizException(code, message);
        }
        return map;
    }

    public final static <K, V> Map<K, V> isNotEmpty(Map<K, V> map, ResultCode resultCode) throws GroundhogBizException {
        if (CollectionUtils.isEmpty(map)) {
            throw new GroundhogBizException(resultCode.getCode(), resultCode.getMessage());
        }
        return map;
    }

    public final static <K, V> Map<K, V> isNotEmpty(Map<K, V> map) throws GroundhogBizException {
        if (CollectionUtils.isEmpty(map)) {
            throw new GroundhogBizException();
        }
        return map;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------- Private method start
    public static boolean notBlank(final CharSequence cs) {
        return !blank(cs);
    }

    public static boolean blank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 处理验证结果的私有方法，减少重复代码量
     *
     * @param result         结果本身
     * @param exceptionClass 需要抛出的异常类实例
     * @param errorMsg       需要返回的消息
     * @param <T>            需要抛出的异常类类型
     * @return 结果
     */
    private final static <T extends RuntimeException> boolean getResult(boolean result, Class<T> exceptionClass, String errorMsg) {
        // 如果结果为false，并且需要抛出异常
        if (!result && exceptionClass != null) {
            throw buildException(exceptionClass, errorMsg);
        }
        return result;
    }

    /**
     * 构造异常
     *
     * @param exceptionClass 需要抛出的异常类实例
     * @param errorMsg       需要返回的消息
     * @param <T>            需要抛出的异常类类型
     * @return
     */
    private final static <T extends RuntimeException> T buildException(Class<T> exceptionClass, String errorMsg) {
        T exception = null;
        Constructor constructor = null;
        try {
            constructor = exceptionClass.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            throw new GroundhogBizException("缺少String为参数的构造函数", e);
        }
        try {
            exception = (T) constructor.newInstance(errorMsg);
        } catch (InstantiationException e) {
            throw new GroundhogBizException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new GroundhogBizException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new GroundhogBizException(e.getMessage(), e);
        }
        return exception;
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------- Private method end
}
