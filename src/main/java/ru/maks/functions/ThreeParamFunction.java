package ru.maks.functions;

/**
 * Created by mstukolov on 31.08.2018.
 */
public interface ThreeParamFunction<P1,P2,P3,R>  {
    R apply(P1 p1, P2 p2, P3 p3);
}
