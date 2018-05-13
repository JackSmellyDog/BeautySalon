package com.epam.salon.services;

public class ValidationService implements IValidationService {
    @Override
    public boolean isUsernameValid(String username) {
        return false;
    }

    @Override
    public boolean isPasswordValid(String password) {
        return false;
    }

    @Override
    public boolean isConfirmationPassMatched(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword) ;
    }
}
