package br.ufc.crateus.coop.schoolapp.model;

import com.google.gson.Gson;

/**
 * Created by andremeireles on 29/06/16.
 */
public class Student {

    public static final String STUDENT_API_URL = "https://192.168.1.6:8443";

    private String name;
    private String email;
    private String course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public static Student fromJSON(String jsonString) {
        Gson converter = new Gson();
        return converter.fromJson(jsonString, Student.class);
    }

    public String toJSON() {
        Gson converter = new Gson();
        return converter.toJson(this);
    }
}
