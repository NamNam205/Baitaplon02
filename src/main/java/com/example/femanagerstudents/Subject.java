package com.example.femanagerstudents;

public class Subject {
    private int Subject_ID;
    private String Subject_Name;
    private String Course;

    public Subject(int Subject_ID, String Subject_Name, String Course){
        this.Subject_ID = Subject_ID;
        this.Subject_Name = Subject_Name;
        this.Course = Course;
    }

    public int getSubject_ID() {
        return Subject_ID;
    }

    public void setSubject_ID(int Subject_ID) {
        this.Subject_ID = Subject_ID;
    }

    // Getter and Setter for Subject_Name
    public String getSubject_Name() {
        return Subject_Name;
    }

    public void setSubject_Name(String Subject_Name) {
        this.Subject_Name = Subject_Name;
    }

    // Getter and Setter for Course
    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

}
