package ru.maks.utils;

import ru.maks.bhge.IBuildLambda;
import org.apache.spark.api.java.function.Function;

/**
 * Created by mstukolov on 31.08.2018.
 */
public class MaksLambdaFromStringHelper implements IBuildLambda{
    public Function<java.lang.Integer, java.lang.Integer> getLambda() {return ( a -> a*(9/5) + 32);}
}
