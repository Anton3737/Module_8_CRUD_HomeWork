package com.example.module_8_crud_homework;

import com.example.module_8_crud_homework.crud.WorkerService;
import com.example.module_8_crud_homework.databaseFlyWay.DBinitFlyWay;
import com.example.module_8_crud_homework.dto.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.example.module_8_crud_homework.crud.ClientService.*;

//@SpringBootApplication
public class Module8CrudHomeWorkApplication {
    private static final String SELECT_WORKERS = "SELECT * FROM worker";

    public static void main(String[] args) {
//        SpringApplication.run(Module8CrudHomeWorkApplication.class, args);


        // Робота CRUD з Клієнтами

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


        // Робота CRUD з Працівниками

        List<Worker> workerCrudListRES = WorkerService.getAllWorkersData(SELECT_WORKERS);
        System.out.println(workerCrudListRES);

        List<Worker> worker = WorkerService.safeWorkersDATA(new Worker("Antonio Banderas", "1955-01-17", "Spain actor", 55000));
        System.out.println(worker);

        List<Worker> updateWorkers = WorkerService.updateWorker(11, "Best actor");
        System.out.println(updateWorkers);

        WorkerService.deleteWorker(11);


    }
}
