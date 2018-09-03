package ru.maks.lambda;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by mstukolov on 16.08.2018.
 */
public class IntegerComparator implements Comparator<Integer>, Serializable {

    public int compare(Integer a, Integer b)
    {
        return a < b ? -1 : a > b ? +1 : 0;
    }
}
