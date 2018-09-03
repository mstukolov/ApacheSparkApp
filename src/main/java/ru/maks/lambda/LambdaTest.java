package ru.maks.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mstukolov on 16.08.2018.
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 1, 1, 1, 1, 1);
        final Integer[] result = {0};

        numbers.forEach((value) -> result[0] += value);
        System.out.println(result[0]);

        IntegerComparator comparator = new IntegerComparator();
        System.out.println(comparator.compare(3, 5));


    }
}
