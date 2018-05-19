package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IAdminDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Admin;
import com.kpi.salon.model.Master;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements IAdminDao {
    private static final Logger LOGGER = Logger.getLogger(Admin.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String ALL_ADMINS_QUERY = "SELECT * FROM beauty_admins";
    private static final String BY_USERNAME_QUERY = "SELECT * FROM beauty_admins WHERE login='%s'";

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(ALL_ADMINS_QUERY);
            while (resultSet.next()){
                admins.add(new Admin(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("phone")
                ));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return admins;
    }

    @Override
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Admin findById(Long id) {
        return null;
    }

    @Override
    public boolean insert(Admin item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Admin findByUsername(String username) {
        Admin admin = null;
        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){

            ResultSet resultSet = statement.executeQuery(String.format(
                    BY_USERNAME_QUERY,
                    username
            ));

            if (resultSet.next()){
                admin = new Admin(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("phone")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return admin;
    }
}
