package com.example.module_8_crud_homework;

import com.example.module_8_crud_homework.databaseFlyWay.DBinitFlyWay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.module_8_crud_homework.crud.ClientService.*;

//@SpringBootApplication
public class Module8CrudHomeWorkApplication {

    public static void main(String[] args) {
//        SpringApplication.run(Module8CrudHomeWorkApplication.class, args);



//        Початок міграції БД
        DBinitFlyWay.initDBFlyWay();

//        Create new Client
        System.out.println(create("Toyota"));

//        // Read client on id
        System.out.println(getById(7));
//
//        // Update client on id
        setName(7, "Nissan");

//        // Delete client
        deleteById(7);

        // Show all clients
        System.out.println(getAllClients());

    }
}
