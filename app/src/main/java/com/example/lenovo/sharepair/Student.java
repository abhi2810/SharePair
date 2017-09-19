package com.example.lenovo.sharepair;

/**
 * Created by abhi on 19/9/17.
 */

public class Student {
    String name,roll,year,username,pass,pl;
    Student(String name,String roll,String year,String username,String pass,String pl){
        this.name=name;
        this.roll=roll;
        this.year=year;
        this.username=username;
        this.pass=pass;
        this.pl=pl;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getUsername() {
        return username;
    }

    public String getYear() {
        return year;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }
}
