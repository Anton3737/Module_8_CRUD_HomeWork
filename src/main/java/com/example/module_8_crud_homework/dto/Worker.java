package com.example.module_8_crud_homework.dto;

import java.util.Date;

public class Worker {

    private int id;
    private String name;
    private String birthday;
    private String level;
    private int salary;

    public Worker(String name, String birthday, String level, int salary) {
        this.name = name;
        this.birthday = String.valueOf(birthday);
        this.level = level;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return
                "id= " + id +
                        ", name= " + name +
                        ", birthday= " + birthday +
                        ", level= " + level +
                        ", salary=" + salary + "\n";
    }
}
