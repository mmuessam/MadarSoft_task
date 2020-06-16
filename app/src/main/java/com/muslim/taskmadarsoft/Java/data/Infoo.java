package com.muslim.taskmadarsoft.Java.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Infoo")
public class Infoo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private  String name;

    private String age;
    private String jobtitle;

    private String gender;

    public
    Infoo(String name, String age, String jobtitle, String gender) {
        this.name = name;
        this.age = age;
        this.jobtitle = jobtitle;
        this.gender = gender;
    }

    public
    String getName() {
        return name;
    }

    public
    void setName(String name) {
        this.name = name;
    }

    public
    String getAge() {
        return age;
    }

    public
    void setAge(String age) {
        this.age = age;
    }

    public
    String getJobtitle() {
        return jobtitle;
    }

    public
    void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public
    String getGender() {
        return gender;
    }

    public
    void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}