package ru.maks.functions;

import org.apache.spark.api.java.function.Function2;

/**
 * Created by mstukolov on 31.08.2018.
 */
@FunctionalInterface
public interface BaseMathFunction2<P1, P2, R> extends Function2<Integer, Integer, Integer> {
    //R call(P1 p1, P2 p2) throws Exception;

    @Override
    Integer call(Integer integer, Integer integer2) throws Exception;
}
