import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import ru.maks.functions.MaksFunction;
import ru.maks.lambda.IntegerComparator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

/**
 * Created by mstukolov on 16.08.2018.
 */
public class App {
    public static void main(String[] args) throws ScriptException {


        // configure sparkling
        SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD")
                .setMaster("local[2]").set("sparkling.executor.memory","2g");

       JavaSparkContext sc = new JavaSparkContext(sparkConf);
       JavaRDD<Integer> myRDD = sc.parallelize(Arrays.asList(1,2,3,4,5,6,7,8));

        //
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        String jsfunction = "function(x) 3*x +1";
        String function = String.format("new java.util.function.Function(%s)", jsfunction);

        @SuppressWarnings("unchecked")
        Function<Object,Object> f = (Function<Object,Object>)engine.eval(function);

        /*Function<Object,Object> lambdaf
                        = (Function<Object,Object>)engine.eval("function(x) 3*x +1");*/


        //myRDD.reduce((Function2<Integer, Integer, Integer>) f.apply(2));


        //System.out.println("Max: " + myRDD.reduce(max));
        //System.out.println("Min: " + myRDD.reduce(min));

        MaksFunction<Integer, Integer> maksFunction = (a,b) -> a + b ;

        String jfunction = String.format("new ru.maks.functions.MaksFunction(%s)", "(a,b) -> a + b");
        MaksFunction<Integer, Integer> jsFunction =
                    (MaksFunction<Integer, Integer>)engine.eval(jfunction);

        int sum = myRDD.reduce(maksFunction);
        //int sum = myRDD.reduce((a,b)->a+b);
        //int sum = myRDD.reduce((Function2<Integer, Integer, Integer>) lambdaf);
        System.out.println("Sum: " + sum);


        //System.out.println("Finished!!!");
        sc.close();

    }

}
