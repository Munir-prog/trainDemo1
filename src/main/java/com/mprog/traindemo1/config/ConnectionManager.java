package com.mprog.traindemo1.config;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
@Getter
@Setter
public class ConnectionManager {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    static {
        loadDriver();
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("org.postgresql.Driver");
    }

    @SneakyThrows
    public Connection get() {
        return DriverManager
                .getConnection(dbUrl, dbUser, dbPassword);
    }

}
