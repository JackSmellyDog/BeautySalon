package com.kpi.salon.services;

import java.time.LocalDateTime;

public interface IValidationService {
    boolean isUsernameValid(String username);
    boolean isPasswordValid(String password);
    boolean isConfirmationPassMatched(String password, String confirmPassword);
    boolean isRequestDateValid(LocalDateTime dateTime);

    String validationCode();
}
