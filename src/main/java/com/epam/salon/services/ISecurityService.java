package com.epam.salon.services;

public interface ISecurityService {
    String encryptPassword(String password);
    boolean checkPassword(String originalPassword, String hashed);
}
