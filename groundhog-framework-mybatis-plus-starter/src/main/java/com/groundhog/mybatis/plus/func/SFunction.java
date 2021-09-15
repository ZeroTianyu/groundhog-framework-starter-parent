package com.groundhog.mybatis.plus.func;

import java.util.List;


@FunctionalInterface
public interface SFunction<T, V> {
    List<V> apply(List<T> source);
}