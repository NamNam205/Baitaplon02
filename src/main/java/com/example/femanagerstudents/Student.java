package com.example.femanagerstudents;

import java.time.LocalDate;
import java.util.Date;

public class Student {
    private int ID;
    private String Name;
    private String Gender;
    private LocalDate date;
    private Float GPA;

    public Student(int ID, String Name, String Gender, LocalDate date, Float GPA) {
        this.ID = ID;
        this.Name = Name;
        this.Gender = Gender;
        this.date = date;
        this.GPA = GPA;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // Getter and Setter for Name
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    // Getter and Setter cho Gender
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    // Getter and Setter cho date
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter and Setter cho GPA
    public Float getGPA() {
        return GPA;
    }

    public void GPA(Float GPA) {
        this.GPA = GPA;
    }

}
