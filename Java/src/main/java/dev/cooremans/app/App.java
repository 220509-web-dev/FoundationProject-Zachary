package dev.cooremans.app;

import dev.cooremans.daos.UsersDAO;
import dev.cooremans.daos.UsersDaoPostgres;
import dev.cooremans.entities.Users;
import dev.cooremans.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) throws SQLException {

        UsersDAO usersDAO = new UsersDaoPostgres();
        //System.out.println(usersDAO.getAllUsers());

        // HOW TO CREATE A NEW USER
//        Users newUser = new Users(0, "Jake", "Statefarm", "JakeState@gmail.com", "Kakies", "ILOVEPANTS", 3, 1);
//        usersDAO.createUser(newUser);
//        System.out.println(newUser);

//        Users user = usersDAO.getUserById(2);
//        user.setUsername("Salads");
//        usersDAO.updateUser(user);
        usersDAO.deleteUserById(8);

    }

}
