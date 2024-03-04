package com.example.module_8_crud_homework.databaseFlyWay;

import com.example.module_8_crud_homework.connections.DBConnections;
import org.flywaydb.core.Flyway;

public class DBinitFlyWay {

    public static void initDBFlyWay() {
        String url = DBConnections.getConnectionDB();
        String user = DBConnections.getUserDB();
        String pass = DBConnections.getPasswordDB();

        Flyway flyway = Flyway.configure().dataSource(url, user, pass).load();
        flyway.migrate();
    }
}
