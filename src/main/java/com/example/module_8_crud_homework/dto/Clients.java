package com.example.module_8_crud_homework.dto;

public class Clients {

    private long id;

    private String name;

    public Clients(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
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

    @Override
    public String toString() {
        return "id=" + id + " name= " + name + "\n";
    }
}
