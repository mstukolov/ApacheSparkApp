package ru.maks.capitalize;

import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import pl.joegreen.lambdaFromString.LambdaCreationException;
import pl.joegreen.lambdaFromString.LambdaFactory;
import pl.joegreen.lambdaFromString.TypeReference;
import ru.maks.model.Person;
import ru.maks.model.PersonsData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by mstukolov on 22.08.2018.
 */
public class CapitalizeDQ {
    public static void main(String[] args) throws LambdaCreationException {
        List<Person> raw = new PersonsData().getPersons();

        raw.stream()
                .map((Person p) -> StringUtils.capitalize(p.getName()))
                .forEach(System.out::println);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(3);
        numbers.add(8);
        numbers.add(12);
        numbers.add(39);

        // configure sparkling
        SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD")
                .setMaster("local[2]").set("sparkling.executor.memory","2g");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        JavaRDD<Person> myRDD = sc.parallelize(raw);
        JavaRDD<Integer> myRDD2 = sc.parallelize(numbers);


        LambdaFactory lambdaFactory = LambdaFactory.get();
       /* Function<Integer, Integer> increase =
                lambdaFactory.createLambda("i -> i+ \"ssss\"", new TypeReference<Function<Integer, Integer>>(){});
*/

       Function<Integer, Integer> multiply =
               lambdaFactory.createLambda("p -> 2*p",
                       new TypeReference<Function<Integer, Integer>>(){});

        System.out.println(multiply.apply(3));

       myRDD2.foreach((VoidFunction<Integer>) multiply);


       //myRDD.foreach(msg -> System.out.print(msg.getName() + "-ok,"));



      /*  myRDD.map((Person p) -> StringUtils.capitalize(p.getName())).
              foreach( (String o) -> System.out.print(o + ","));*/


        System.out.println("finished");
    }
}
