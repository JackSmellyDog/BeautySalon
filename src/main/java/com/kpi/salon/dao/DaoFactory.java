package com.kpi.salon.dao;

import com.kpi.salon.dao.impl.AdminDao;
import com.kpi.salon.dao.impl.ClientDao;
import com.kpi.salon.dao.impl.MasterDao;
import com.kpi.salon.dao.impl.RequestDao;
import com.kpi.salon.dao.impl.ReviewDao;
import com.kpi.salon.exceptions.ThereIsNoSuchDaoEntityException;

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
