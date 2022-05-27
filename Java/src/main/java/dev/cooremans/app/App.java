package dev.cooremans.app;

import dev.cooremans.daos.*;
import dev.cooremans.entities.Roles;
import dev.cooremans.entities.Users;
import dev.cooremans.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) throws SQLException {

        UsersDAO usersDAO = new UsersDaoPostgres();
        RolesDAO rolesDAO = new RolesDaoPostgres();
        DepartmentsDAO departmentsDAO = new DepartmentsDaoPostgres();
        rolesDAO.deleteById(5);

        // HOW TO CREATE A NEW ROLE
//        Roles newRole = new Roles(0, "Something");
//        rolesDAO.createRole(newRole);
//        System.out.println(newRole);

        // HOW TO CREATE A NEW USER
//        Users newUser = new Users(0, "Jake", "Statefarm", "JakeState@gmail.com", "Kakies", "ILOVEPANTS", 3, 1);
//        usersDAO.createUser(newUser);
//        System.out.println(newUser);

//        Users user = usersDAO.getUserById(2);
//        user.setUsername("Salads");
//        usersDAO.updateUser(user);
        // usersDAO.deleteUserById(8);

    }

}
