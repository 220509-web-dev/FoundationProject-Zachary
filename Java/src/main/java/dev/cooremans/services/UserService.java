package dev.cooremans.services;

import dev.cooremans.daos.UsersDaoPostgres;
import dev.cooremans.entities.Users;
import dev.cooremans.utils.exceptions.InvalidRequestException;

import java.util.List;

public class UserService {

    private UsersDaoPostgres usersDAO;
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public Users getByUsername(String username) {
        Users user = usersDAO.getUserByUsername(username);
        if (user == null) {
            throw new InvalidRequestException("Cannot find user");
        }
        return user;
    }

    public Users getUserBeId(int id) {
        Users user = usersDAO.getUserById(id);
        if (user == null) {
            throw new InvalidRequestException("Cannot find user");
        }
        return user;
    }

}
