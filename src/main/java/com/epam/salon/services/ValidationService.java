package com.epam.salon.services;

public class ValidationService implements IValidationService {
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
