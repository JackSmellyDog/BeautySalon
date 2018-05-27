package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.DaoFactory;
import com.kpi.salon.dao.IReviewsDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.Review;
import com.kpi.salon.utils.ResourcesManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.kpi.salon.dao.DaoFactory.Entity.REQUEST;

public class ReviewDao implements IReviewsDao {
    private static final Logger LOGGER = Logger.getLogger(ReviewDao.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private RequestDao requestDao = (RequestDao) DaoFactory.create(REQUEST);
    private Properties queries;

    public ReviewDao() {
        init();
    }

    @Override
    public List<Review> findAll() {
        List<Request> requests = requestDao.findAll();
        Map<Long, Request> map = new HashMap<>();
        String query = queries.getProperty("sql.review.find.all");

        for (Request request : requests) {
            map.put(request.getId(), request);
        }

        List<Review> reviews = new ArrayList<>();

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                reviews.add(new Review(
                        resultSet.getLong("id"),
                        resultSet.getString("text"),
                        resultSet.getInt("rating"),
                        map.get(resultSet.getLong("request_id"))
                ));
            }

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return reviews;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Review findById(Long id) {
        Review review = null;
        String query = queries.getProperty("sql.review.find.by.id");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String text = resultSet.getString("text");
                Integer rating = resultSet.getInt("rating");
                Long request_id = resultSet.getLong("request_id");

                Request request = requestDao.findById(request_id);

                if (request != null) {
                    review = new Review(id, text, rating, request);
                }
            }

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return review;
    }

    @Override
    public boolean insert(Review item) {
        String query = queries.getProperty("sql.review.insert");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, item.getText());
            preparedStatement.setInt(2, item.getRating());
            preparedStatement.setLong(3, item.getRequest().getId());
            preparedStatement.executeUpdate();

            return true;

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(Review item) {
        String query = queries.getProperty("sql.review.update");
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, item.getText());
            preparedStatement.setInt(2, item.getRating());

            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();

            return true;

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Review findByRequestId(Request request) {
        Review review = null;
        String query = queries.getProperty("sql.review.find.by.request");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setLong(1, request.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Long id = resultSet.getLong("id");
                String text = resultSet.getString("text");
                Integer rating = resultSet.getInt("rating");

                review = new Review(id, text, rating, request);
            }

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return review;
    }

    private void init() {
        try {
            queries = new ResourcesManager().getProperties("queries");
        } catch (IOException e) {
            LOGGER.error(String.format("Unable to download property file. %s", e.getMessage()), e);
        }
    }
}
