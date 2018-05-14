package com.kpi.salon.services.impl;

import com.kpi.salon.services.IValidationService;
import org.apache.log4j.Logger;

public class ValidationService implements IValidationService {
    private static final Logger LOGGER = Logger.getLogger(ValidationService.class);
    /****
     *  Change to common apache email validation
     * */

    @Override
    public boolean isUsernameValid(String username) {
        if (username == null) return false;
        return username.matches("^([\\w-\\\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");
    }

    @Override
    public boolean isPasswordValid(String password) {
        if (password == null) return false;
        return password.matches("\\w{6,255}");
    }

    @Override
    public boolean isConfirmationPassMatched(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword) ;
    }
}
