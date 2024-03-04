package com.example.module_8_crud_homework.crud;

import com.example.module_8_crud_homework.connections.DBConnections;
import com.example.module_8_crud_homework.dto.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerService {
    private static final String SELECT_WORKERS = "SELECT * FROM worker";
    public static final String INSERT_WORKER = "INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";

    public static final String UPDATE_WORKER = "UPDATE worker SET LEVEL = ? WHERE id = ?";

    public static final String DELETE_WORKER = "DELETE FROM worker WHERE id = ?";


    // CREATE
    public static List<Worker> safeWorkersDATA(Worker workerCrud) {
        List<Worker> workers = new ArrayList<>();

        try (Connection connection = DBConnections.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORKER);

            preparedStatement.setString(1, workerCrud.getName());
            preparedStatement.setString(2, workerCrud.getBirthday());
            preparedStatement.setString(3, workerCrud.getLevel());
            preparedStatement.setInt(4, workerCrud.getSalary());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }


    // READ
    public static List<Worker> getAllWorkersData(String query) {
        List<Worker> workerCrudList = new ArrayList<>();


        try (Connection connection = DBConnections.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String birthday = resultSet.getString("birthday");
                String level = resultSet.getString("level");
                int salary = resultSet.getInt("salary");

                workerCrudList.add(new Worker(name, birthday, level, salary));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return workerCrudList;
    }

    // UPDATE
    public static List<Worker> updateWorker(int workerID, String level) {
        List<Worker> workersAfterUpdate = new ArrayList<>();

        try (Connection connection = DBConnections.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WORKER);

            preparedStatement.setString(1, level);
            preparedStatement.setInt(2, workerID);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workersAfterUpdate;
    }


    // DELETE
    public static void deleteWorker(int workerID) {

        try (Connection connection = DBConnections.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WORKER);

            preparedStatement.setInt(1, workerID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}

