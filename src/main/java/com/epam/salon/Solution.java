package com.epam.salon;

import com.epam.salon.dao.DaoFactory;

import static com.epam.salon.dao.DaoFactory.Entity.*;

public class Solution {
    public static void main(String[] args) {
        DaoFactory.create(ADMIN);
    }
}
