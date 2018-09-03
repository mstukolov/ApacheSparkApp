package ru.maks.functions;


/**
 * Created by mstukolov on 31.08.2018.
 */

public interface OneParamFunction<T,R>  {
    R call(T t) throws Exception;
}
