package com.example.module_8_crud_homework.crud;

import com.example.module_8_crud_homework.connections.DBConnections;
import com.example.module_8_crud_homework.dto.Clients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private static final String SELECT_CLIENT = "SELECT id , name FROM client WHERE id = ?";
    public static final String INSERT_CLIENT = "INSERT INTO client (NAME) VALUES (?)";
    private static final String UPDATE_CLIENT = "UPDATE client SET NAME = ? WHERE id = ?";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM client";


    // CREATE
    public static long create(String name) {
        long newId = -1;

        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = DriverManager.getConnection(DBConnections.getConnectionDB(), DBConnections.getUserDB(), DBConnections.getPasswordDB());
            preparedStatement = connection.prepareStatement(INSERT_CLIENT, Statement.RETURN_GENERATED_KEYS);

            if (name.length() > 2 && name.length() < 20) {
                preparedStatement.setString(1, name);
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            newId = generatedKeys.getLong(1);
                        }
                    }
                }
            } else {
                throw new Exception("Name isn't valid");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newId;
    }


    // READ
    public static String getById(long id) {
        Clients client = null;

        try (Connection connection = DriverManager.getConnection(DBConnections.getConnectionDB(), DBConnections.getUserDB(), DBConnections.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long clientId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                client = new Clients(clientId, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (client != null) {
            return client.toString();
        } else {
            throw new RuntimeException("Client ID not found ,please revise valid ID");
        }
    }

    // UPDATE
    public static String setName(long id, String name) {
        Clients client = null;
        try (Connection connection = DriverManager.getConnection(DBConnections.getConnectionDB(), DBConnections.getUserDB(), DBConnections.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT)) {

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            client = new Clients(id, name);
            preparedStatement.executeUpdate();

            System.out.println("Data are updated");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Client ID not found for update data, please revise valid ID");
        }
        return client.toString();
    }

    // DELETE
    public static void deleteById(long id) {
        try (Connection connection = DriverManager.getConnection(DBConnections.getConnectionDB(), DBConnections.getUserDB(), DBConnections.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Client deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Client ID not found for delete data, please revise valid ID");
        }
    }


    // READ ALL
    public static List<Clients> getAllClients() {
        List<Clients> clientsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DBConnections.getConnectionDB(), DBConnections.getUserDB(), DBConnections.getPasswordDB());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                clientsList.add(new Clients(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientsList;
    }
}
