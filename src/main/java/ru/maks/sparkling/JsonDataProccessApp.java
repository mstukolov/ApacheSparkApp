package ru.maks.sparkling;

import com.google.gson.Gson;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import ru.maks.model.Person;
import ru.maks.model.PersonJ;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mstukolov on 03.09.2018.
 */
public class JsonDataProccessApp {
    public static void main(String[] args) {

        String path = "C://Hadoop/data/data.json";
        System.setProperty("hadoop.home.dir", "C:\\Hadoop");
        System.out.println("Starting\n=======================");

        SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD")
                .setMaster("local[2]").set("sparkling.executor.memory","2g");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);


        JavaRDD<String> peopleRDD = sc.textFile(path);


        String schemaString = "id fname lname email gender";

        // Generate the schema based on the string of schema
        List<StructField> fields = new ArrayList<>();
        for (String fieldName : schemaString.split(" ")) {
            StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            fields.add(field);
        }
        StructType schema = DataTypes.createStructType(fields);

        // Convert records of the RDD (people) to Rows
        /*JavaRDD<Row> rowRDD = peopleRDD.map((Function<String, Row>) record -> {

            String[] attributes = record.split(",");
            return RowFactory.create(attributes[0], attributes[1].trim());
        });*/

       /* String[] fields = line.split(",");
        PersonJ sd = new PersonJ(fields[0], Integer.valueOf(fields[1]), fields[2]);
        return sd;*/


        JavaRDD<PersonJ> rowRDD = peopleRDD.map((Function<String, PersonJ>) record -> {

            /*Gson gson = new Gson();
            PersonJ sd = gson.fromJson(record, PersonJ.class);*/

            String[] attributes = record.split(",");
            PersonJ sd =
                    new PersonJ(Long.valueOf(attributes[0]),
                                 attributes[1], attributes[2], attributes[3], attributes[4]);
            return sd;
        });


        rowRDD.foreach(row -> System.out.println(row));

        // Apply the schema to the RDD
        //Dataset<Row> peopleDataFrame = sc.createDataFrame(rowRDD, schema);




        /*SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL data source JSON example")
                .master("local[2]")
                .getOrCreate();*/



        /*
        Dataset<Row> persons = spark.read().json(path);
        System.out.println("Schema\n=======================");
        persons.printSchema();*/

      /*  JavaSparkContext sc = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new org.apache.spark.sql.SQLContext(spark);
        Dataset people = sqlContext.read().json(path);*/





        //raw.foreach(row -> System.out.println(row));







        System.out.println("finished");
    }
}
