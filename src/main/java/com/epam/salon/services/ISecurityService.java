package com.epam.salon.services;

public interface ISecurityService {
    String encryptPassword(String password);
    boolean checkPasswords(String one, String two);

}
