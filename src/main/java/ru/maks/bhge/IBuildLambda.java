package ru.maks.bhge;

/**
 * Created by mstukolov on 31.08.2018.
 */
@FunctionalInterface
public interface IBuildLambda {
    public org.apache.spark.api.java.function.Function<java.lang.Integer, java.lang.Integer> getLambda();
}
