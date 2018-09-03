package ru.maks.functions;

import org.apache.spark.api.java.function.Function2;

import java.util.function.Function;

/**
 * Created by mstukolov on 21.08.2018.
 */
@FunctionalInterface
public interface MaksFunction<T, R> extends Function2<Integer, Integer, Integer> {
//    R apply(T x);

    @Override
    Integer call(Integer integer, Integer integer2) throws Exception;
}
