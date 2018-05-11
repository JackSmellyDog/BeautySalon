package com.epam.salon.dao.admin;

import com.epam.salon.datasource.ConnectionManager;
import com.epam.salon.model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements IAdminDao {
    private ConnectionManager connectionManager = new ConnectionManager("jdbc/db");
    private static final String ALL_ADMINS_QUERY = "SELECT * FROM beauty_admins";
    private static final String BY_USERNAME_QUERY = "SELECT * FROM beauty_admins WHERE login='%s'";

    @Override
    public List<Admin> getAll() {

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            List<Admin> admins = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(ALL_ADMINS_QUERY);
            while (resultSet.next()){
                admins.add(new Admin(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("phone")
                ));
            }

            // delete
            admins.forEach(System.out::println);

            return admins;
        } catch (SQLException e) {
            e.printStackTrace(); // TODO log4j
            return null;
        }
    }


    @Override
    public Admin getById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void insert(Admin item) {

    }

    @Override
    public void update(long id, Admin item) {

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

            while (resultSet.next()){
                admin = new Admin(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("phone")
                );
            }

            return admin;
        } catch (SQLException e) {
            e.printStackTrace(); // TODO log4j
            return null;
        }
    }
}
