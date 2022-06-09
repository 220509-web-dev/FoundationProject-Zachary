package dev.cooremans.services;

import dev.cooremans.daos.UsersDaoPostgres;
import dev.cooremans.dto.ResourceCreationResponse;
import dev.cooremans.entities.Users;
import dev.cooremans.utils.exceptions.InvalidRequestException;

import java.util.List;

import static java.lang.Integer.parseInt;

public class AuthService {

    private final UsersDaoPostgres usersDAO;
    private List<Users> users;

    public AuthService(UsersDaoPostgres usersDAO) {
        this.usersDAO = usersDAO;
    }
    public ResourceCreationResponse createNewUser(Users newUser) {
        if (newUser == null ||
                newUser.getUsername() == null || newUser.getUsername().equals("") ||
                newUser.getPassword() == null || newUser.getPassword().equals(""))
        {
            String msg = "Provided card was invalid. Username and password must not be null or empty!";
            throw new InvalidRequestException(msg);
        }

        return new ResourceCreationResponse(usersDAO.save(newUser).getId());
    }



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

    public Users getUserBeId(String id) {
        Users user = usersDAO.getUserById(parseInt(id));
        if (user == null) {
            throw new InvalidRequestException("Cannot find user");
        }
        return user;
    }

}
