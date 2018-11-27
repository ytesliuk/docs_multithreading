package com.javacourse;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public class Document {
    private int id;
    private String name;
    private ScientistSpeciality speciality;

    public Document(String name, ScientistSpeciality speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ScientistSpeciality getSpeciality() {
        return speciality;
    }
    public void setSpeciality(ScientistSpeciality speciality) {
        this.speciality = speciality;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}


