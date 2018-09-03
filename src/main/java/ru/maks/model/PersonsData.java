package ru.maks.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mstukolov on 22.08.2018.
 */
public class PersonsData implements Serializable {

    List<Person> persons;
    public PersonsData() {
        persons = Arrays.asList(
                new Person("maks", 45, "man"),
                new Person("dmitry", 12, "man"),
                new Person("stas", 32, "man"),
                new Person("elena", 5, "woman"),
                new Person("vasya", 65, "man"),
                new Person("sveta", 24, "woman")
        );
    }

    public List<Person> getPersons() {
        return persons;
    }
}
