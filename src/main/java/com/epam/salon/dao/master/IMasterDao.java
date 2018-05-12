package com.epam.salon.dao.master;

import com.epam.salon.dao.IDao;
import com.epam.salon.model.Master;
import com.epam.salon.model.User;

public interface IMasterDao extends IDao<Master> {
    Master findByUsername(String username);
}
