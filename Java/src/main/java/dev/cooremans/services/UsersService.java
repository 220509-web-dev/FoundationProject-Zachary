package dev.cooremans.services;

import dev.cooremans.daos.UsersDAO;
import dev.cooremans.entities.Users;

public class UsersService {

    private final UsersDAO userDAO;

    public UsersService(UsersDAO userDAO) {
        this.userDAO = userDAO;
    }


}
