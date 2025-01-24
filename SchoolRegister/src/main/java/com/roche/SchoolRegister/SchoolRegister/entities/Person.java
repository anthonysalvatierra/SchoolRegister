package com.roche.SchoolRegister.SchoolRegister.entities;

public class Person {

    private String name;
    private String dna;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dna='" + dna + '\'' +
                '}';
    }
}
