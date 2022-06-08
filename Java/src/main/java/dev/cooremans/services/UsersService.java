package dev.cooremans.services;

import dev.cooremans.daos.UsersDaoPostgres;
import dev.cooremans.dto.ResourceCreationResponse;
import dev.cooremans.entities.Users;
import dev.cooremans.utils.exceptions.InvalidRequestException;

public class UsersService {

    private final UsersDaoPostgres usersDAO;

    public UsersService(UsersDaoPostgres usersDAO) {
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


}
