package com.kpi.salon.services;

public interface ISecurityService {
    String encryptPassword(String password);
    boolean checkPassword(String originalPassword, String hashed);
}
