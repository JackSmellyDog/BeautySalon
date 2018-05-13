package com.epam.salon.services;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class SecurityService implements ISecurityService {
    private static final Logger LOGGER = Logger.getLogger(SecurityService.class);

    private static final String SALT = "$2a$10$JY8cZc3h2m5yX0626rW1bO";

    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, SALT);
    }

    @Override
    public boolean checkPassword(String originalPassword, String hashed) {
        return BCrypt.checkpw(originalPassword, hashed);
    }
}
