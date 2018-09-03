package ru.maks.functions;

/**
 * Created by mstukolov on 31.08.2018.
 */
public interface TwoParamFunction<P1,P2,R> {
    R apply(P1 p1, P2 p2);
}
