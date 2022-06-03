package dev.cooremans.daos;

import dev.cooremans.entities.Users;

import java.util.List;

public interface UsersDAO {

    // CREATE
    Users createUser(Users user);

    // READ
    Users getUserById(int id);
    Users getUserByUsername(String username);
    List<Users> getAllUsers();

    // UPDATE
    Users updateUser(Users user);

    // DELETE
    Users deleteUserById(int id);
}
