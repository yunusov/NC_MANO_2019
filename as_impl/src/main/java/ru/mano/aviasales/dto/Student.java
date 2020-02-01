package ru.mano.aviasales.dto;

public class Student {
    private String name;
    private Curator curator;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Curator getCurator() {
        return curator;
    }

    public void setCurator(Curator curator) {
        this.curator = curator;
    }

    public String sayINeedHelp() {
        return "I need help!1";
    }
}
