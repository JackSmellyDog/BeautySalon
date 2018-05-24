package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.DaoFactory;
import com.kpi.salon.dao.IReviewsDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.Review;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kpi.salon.dao.DaoFactory.Entity.REQUEST;

public class ReviewDao implements IReviewsDao {
    private static final Logger LOGGER = Logger.getLogger(ReviewDao.class);

    private static final String INSERT_REVIEW_QUERY = "INSERT INTO beauty_reviews (text, rating, request_id) VALUES (?, ?, ?)";
    private static final String ALL_REVIEWS_QUERY = "SELECT * FROM beauty_reviews";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM beauty_reviews WHERE id=?";
    private static final String UPDATE_REVIEW_QUERY = "UPDATE beauty_reviews SET text=?, rating=? WHERE id=?";
    private static final String FIND_BY_REQUEST_ID_QUERY = "SELECT * FROM beauty_reviews WHERE request_id=?";

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private RequestDao requestDao = (RequestDao) DaoFactory.create(REQUEST);


    @Override
    public List<Review> findAll() {
        List<Request> requests = requestDao.findAll();
        Map<Long, Request> map = new HashMap<>();

        for (Request request : requests) {
            map.put(request.getId(), request);
        }

        List<Review> reviews = new ArrayList<>();

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(ALL_REVIEWS_QUERY);
            while (resultSet.next()){
                reviews.add(new Review(
                        resultSet.getLong("id"),
                        resultSet.getString("text"),
                        resultSet.getInt("rating"),
                        map.get(resultSet.getLong("request_id"))
                ));
            }

        } catch (SQLException e) {
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

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
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

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return review;
    }

    @Override
    public boolean insert(Review item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVIEW_QUERY)
        ){
            preparedStatement.setString(1, item.getText());
            preparedStatement.setInt(2, item.getRating());
            preparedStatement.setLong(3, item.getRequest().getId());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(Review item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REVIEW_QUERY)
        ){
            preparedStatement.setString(1, item.getText());
            preparedStatement.setInt(2, item.getRating());

            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Review findByRequestId(Request request) {
        Review review = null;

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_REQUEST_ID_QUERY)
        ){
            preparedStatement.setLong(1, request.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Long id = resultSet.getLong("id");
                String text = resultSet.getString("text");
                Integer rating = resultSet.getInt("rating");

                review = new Review(id, text, rating, request);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return review;
    }
}
