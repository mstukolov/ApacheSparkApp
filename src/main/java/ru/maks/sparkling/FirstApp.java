package ru.maks.sparkling;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import pl.joegreen.lambdaFromString.LambdaFactory;
import pl.joegreen.lambdaFromString.LambdaFactoryConfiguration;
import pl.joegreen.lambdaFromString.TypeReference;
import ru.maks.functions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * Created by mstukolov on 31.08.2018.
 */
public class FirstApp {
    public static void main(String[] args) throws Exception {


        BaseMathFunction2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        BaseMathFunction2<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        LambdaFactory lambdaFactory =
                LambdaFactory.get( LambdaFactoryConfiguration.get().withImports(BaseMathFunction2.class));

        String fp1 = "(a,b) -> a*b";
        String fp2 = " a -> a + 2";
        String fp3 = " (a,b,c) -> a + b - c";

        String celsius2fahrenheit = " a -> a*(9/5) + 32";

        OneParamFunction<Integer, Integer> one2lambda = lambdaFactory.createLambda(
                celsius2fahrenheit, new TypeReference<OneParamFunction<Integer, Integer>>(){});


        TwoParamFunction<Integer, Integer, Integer> two2lambda = lambdaFactory.createLambda(
                fp1, new TypeReference<TwoParamFunction<Integer, Integer, Integer>>(){});

        ThreeParamFunction<Integer, Integer, Integer, Integer> three2lambda = lambdaFactory.createLambda(
                fp3, new TypeReference<ThreeParamFunction<Integer, Integer, Integer, Integer>>(){});

       // System.out.printf("celsius2fahrenheit=%d\n", one2lambda.apply(30));
        System.out.printf("two2lambda=%d\n", two2lambda.apply(3, 9));
        System.out.printf("three2lambda=%d\n", three2lambda.apply(3, 9, 4));


        Function2<Integer, Integer, Integer> function2 = (a,b) -> a*b;

       /* BaseMathFunction2<Integer, Integer, Integer> str2lambda =
                lambdaFactory.createLambda("(a,b) -> a + b",
                        new TypeReference<BaseMathFunction2<Integer, Integer, Integer>>(){});*/



        // configure sparkling
        SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD")
                .setMaster("local[2]").set("sparkling.executor.memory","2g");

        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<Integer> myRDD = sc.parallelize(Arrays.asList(1,2,3,4,5,6,7,8));

        //int result = myRDD.reduce((a,b) -> a+b);
        int total = myRDD.reduce(sum);
        int mult = myRDD.reduce(multiply);

        /*JavaRDD<Integer> counts = myRDD.flatMap(new FlatMapFunction<Integer, Integer>() {
            @Override
            public Iterator<Integer> call(Integer a) throws Exception {
                return a*5;
            }
        });*/




        int lambda = myRDD.reduce(function2);

        //int lambda = IntStream.range(1,3).reduce(multiplyString).getAsInt();

        System.out.printf("sum=%d, multiply=%d, lambda=%d,\n", total, mult, lambda);
    }
}
