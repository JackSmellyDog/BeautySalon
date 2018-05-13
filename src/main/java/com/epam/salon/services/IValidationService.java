package com.epam.salon.services;

public interface IValidationService {
    boolean isUsernameValid(String username);
    boolean isPasswordValid(String password);
    boolean isConfirmationPassMatched(String password, String confirmPassword);
}
