package com.dchalbyshevv.models;

public class Person {

    private  String name;
    private  int id;

    public Person() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }



}
