package com.epam.salon.dao.master;

import com.epam.salon.datasource.ConnectionManager;
import com.epam.salon.model.Master;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MasterDao implements IMasterDao {
    private static final Logger LOGGER = Logger.getLogger(MasterDao.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String ALL_MASTERS_QUERY = "SELECT * FROM beauty_masters";
    private static final String BY_USERNAME_QUERY = "SELECT * FROM beauty_masters WHERE login='%s'";

    @Override
    public List<Master> findAll() {
        List<Master> masters = new ArrayList<>();

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(ALL_MASTERS_QUERY);
            while (resultSet.next()){
                masters.add(new Master(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return masters;
    }


    @Override
    public void insert(Master item) {

    }

    @Override
    public Master findByUsername(String username) {
        Master master = null;
        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){

            ResultSet resultSet = statement.executeQuery(String.format(
                    BY_USERNAME_QUERY,
                    username
            ));

            if (resultSet.next()){
                master = new Master(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return master;
    }
}
