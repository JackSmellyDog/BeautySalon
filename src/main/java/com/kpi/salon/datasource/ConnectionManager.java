package com.kpi.salon.datasource;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

    private static ConnectionManager instance = new ConnectionManager();
    private DataSource dataSource;


    private ConnectionManager() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/db");
        } catch (NamingException e) {
            // Handle error that it's not configured in JNDI.
            throw new IllegalStateException("jdbc/db is missing in JNDI!", e);
        }
    }

    public static ConnectionManager getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
