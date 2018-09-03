package ru.maks.model;

import java.io.Serializable;

/**
 * Created by mstukolov on 22.08.2018.
 */
public class Person implements Comparable, Serializable {
    String name;
    Integer age;
    String sex;

    public Person(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public int compareTo(Object o) {
        if ( ((Person)o).getAge() > this.getAge()) {
            return 0;
        }
        return ((Person)o).getAge();
    }
}
