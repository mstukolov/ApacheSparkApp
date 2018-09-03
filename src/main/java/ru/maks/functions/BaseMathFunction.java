package ru.maks.functions;

/**
 * Created by mstukolov on 31.08.2018.
 */

@FunctionalInterface
public interface BaseMathFunction {

    Integer call(Integer integer) throws Exception;
}
