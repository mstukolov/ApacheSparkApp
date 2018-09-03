package ru.maks.capitalize;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import ru.maks.model.Person;

/**
 * Created by mstukolov on 23.08.2018.
 */
public class SparkDQSCV {
    public static void main(String[] args) {
        // configure sparkling

        String file = "C:\\\\Users\\\\mstukolov\\\\IdeaProjects\\\\ApacheSparkApp\\\\src\\\\main\\\\resources\\\\people.csv";
        SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD")
                .setMaster("local[2]").set("sparkling.executor.memory","2g");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);


        JavaRDD<Person> data = sc.textFile(file).map(
                new Function<String, Person>() {
                    public Person call(String line) throws Exception {
                        // Here you can use JSON
                        // Gson gson = new Gson();
                        // gson.fromJson(line, Record.class);
                        String[] fields = line.split(",");
                        Person sd = new Person(fields[0], Integer.valueOf(fields[1]), fields[2]);
                        return sd;
                    }
                }
        );


        /*SparkSession sparkling = SparkSession.builder().appName("Java Spark SQL Example").getOrCreate();

        StructType schema = new StructType()
                .add("name", "string")
                .add("age", "long")
                .add("sex", "string");

        Dataset<Row> df = sparkling.read()
                .option("mode", "DROPMALFORMED")
                .schema(schema)
                .csv(file);

        df.foreach(p -> System.out.println(p));
        df.createOrReplaceTempView("people");

        Dataset<Row> sqlResult = sparkling.sql(
                "SELECT name, age, sex"
                        + " FROM people ORDER BY sex DESC");

        sqlResult.show(); //for testing*/

        /*JavaRDD<String> myRDD = sc.textFile("C:\\Users\\mstukolov\\IdeaProjects\\ApacheSparkApp\\src\\main\\resources\\people.csv");
        myRDD.map(line -> line.split(","));

        myRDD.foreach(p -> System.out.print(p));*/
        //myRDD.foreach(p -> System.out.print(p.getName() + ","));
    }
}
