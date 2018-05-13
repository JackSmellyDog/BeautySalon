package com.epam.salon.dao;

import com.epam.salon.dao.admin.AdminDao;
import com.epam.salon.dao.client.ClientDao;
import com.epam.salon.dao.master.MasterDao;
import com.epam.salon.dao.request.RequestDao;
import com.epam.salon.dao.review.ReviewDao;
import com.epam.salon.exceptions.ThereIsNoSuchDaoEntityException;
import org.apache.log4j.Logger;

public class DaoFactory {
    public static IDao create(Entity entity) {
        switch (entity) {
            case ADMIN:
                return new AdminDao();
            case CLIENT:
                return new ClientDao();
            case MASTER:
                return new MasterDao();
            case REVIEW:
                return new ReviewDao();
            case REQUEST:
                return new RequestDao();
            default:
                throw new ThereIsNoSuchDaoEntityException();
        }
    }

    public enum Entity {
        ADMIN,
        CLIENT,
        MASTER,
        REQUEST,
        REVIEW,
    }
}
