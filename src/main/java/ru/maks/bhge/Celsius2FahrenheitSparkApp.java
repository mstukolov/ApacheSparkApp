package ru.maks.bhge;

import net.openhft.compiler.CompilerUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import pl.joegreen.lambdaFromString.LambdaCreationException;
import pl.joegreen.lambdaFromString.LambdaFactory;
import pl.joegreen.lambdaFromString.LambdaFactoryConfiguration;
import pl.joegreen.lambdaFromString.TypeReference;
import ru.maks.functions.BaseMathFunction2;
import ru.maks.functions.OneParamFunction;
import ru.maks.model.Person;
import ru.maks.utils.MaksLambdaFromStringHelper;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by mstukolov on 31.08.2018.
 */
public class Celsius2FahrenheitSparkApp {
    public static void main(String[] args) throws Exception {

        LambdaFactory lambdaFactory =
                LambdaFactory.get(
                        LambdaFactoryConfiguration.get().
                                withImports(Person.class, OneParamFunction.class)
                );

        String celsius2fahrenheit = " a -> a*(9/5) + 32";

       /* OneParamFunction<Integer, Integer> one2lambda = lambdaFactory.createLambda(
                celsius2fahrenheit, new TypeReference<OneParamFunction<Integer, Integer>>(){});

        System.out.printf("celsius2fahrenheit=%d\n", one2lambda.call(30));*/

        // configure sparkling
        SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD")
                .setMaster("local[2]").set("sparkling.executor.memory","2g");

        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<Integer> celsiusRDD = sc.parallelize(Arrays.asList(23,12,32,28,18,10,22,24));

        //celsiusRDD.map(a -> a*(9/5) + 32).foreach(a -> System.out.println(a));
        //celsiusRDD.map(one2lambda).foreach(a -> System.out.println(a));



       /* String javaCode = "package mypackage;\n" +
                "public class LambdaMaksHelper implements IBuildLambda {\n" +
                    "public static org.apache.spark.api.java.function.Function<java.lang.Integer, java.lang.Integer> getLambda() {return ( a -> a*(9/5) + 32);" +
                    "\n}" +
                "}\n";*/

        String className = "mypackage.MyClass";
        String javaCode = "package mypackage;\n" +
                "import ru.maks.bhge.IBuildLambda;\n" +
                "import org.apache.spark.api.java.function.Function;\n" +
                "public class MyClass implements IBuildLambda {\n" +
                "    public Function<java.lang.Integer, java.lang.Integer> getLambda() {\n" +
                "        return ( a -> a*(9/5) + 100);\n" +
                "    }\n" +
                "}\n";
        Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, javaCode);
        IBuildLambda lambda = (IBuildLambda) aClass.newInstance();


        //IBuildLambda lambda = (IBuildLambda) aClass.newInstance();

        //System.out.println("fine");
        celsiusRDD.map(lambda.getLambda()).foreach(a -> System.out.println(a));
        //celsiusRDD.map(MaksLambdaFromStringHelper.getLambda()).foreach(a -> System.out.println(a));

    }
}
