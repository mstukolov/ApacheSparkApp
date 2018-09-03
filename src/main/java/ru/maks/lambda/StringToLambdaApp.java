package ru.maks.lambda;


import pl.joegreen.lambdaFromString.LambdaCreationException;
import pl.joegreen.lambdaFromString.LambdaFactory;
import pl.joegreen.lambdaFromString.TypeReference;


import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * Created by mstukolov on 31.08.2018.
 */
public class StringToLambdaApp {
    public static void main(String[] args) throws LambdaCreationException {

        LambdaFactory lambdaFactory = LambdaFactory.get();


            Function<Integer, Integer> increase =
                    lambdaFactory.createLambda("i -> i+ \"ssss\"", new TypeReference<Function<Integer, Integer>>(){});

            int result = increase.apply(0);

        System.out.printf("result: %d", result);





        //assertTrue(1 == increase.apply(0));

        /*
        IntBinaryOperator multiply = lambdaFactory.createLambda(
                "(a,b) -> a*b", new TypeReference<IntBinaryOperator>(){});

        */
        ///assertEquals(1*2*3*4, IntStream.range(1,5).reduce(multiply).getAsInt());

        /*
        Function<Integer, String> decorate = lambdaFactory.createLambda(
                "i -> \"ABC\"+i+\"DEF\"", new TypeReference<Function<Integer, String>>(){});
        ///assertEquals("ABC101DEF", decorate.apply(101));
        */
    }
}
