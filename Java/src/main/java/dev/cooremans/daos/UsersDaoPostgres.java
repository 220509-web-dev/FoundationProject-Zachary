package dev.cooremans.daos;

import dev.cooremans.entities.Users;
import dev.cooremans.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoPostgres implements UsersDAO{

    @Override
    public Users createUser(Users user) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "insert into Users values (default,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirst_name());
            ps.setString(2,user.getLast_name());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setInt(6,user.getRole_id());
            ps.setInt(7,user.getDepartment_id());

            // EXECUTE THE PROGRAM
            ps.execute();

            // GET THE GENERATED PRIMARY KEY
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");

            user.setId(generatedId);
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users getUserById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from Users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            // GET FIRST RECORD
            rs.next();

            // CREATE USER AND SET THE VALUES
            Users users = new Users();
            users.setId(rs.getInt("id"));
            users.setFirst_name(rs.getString("first_name"));
            users.setLast_name(rs.getString("last_name"));
            users.setEmail(rs.getString("email"));
            users.setUsername(rs.getString("username"));
            users.setPassword(rs.getString("password"));
            users.setRole_id(rs.getInt("role_id"));
            users.setDepartment_id(rs.getInt("department_id"));
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Users> getAllUsers() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from Users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Users> users = new ArrayList<Users>();

            while(rs.next()){
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole_id(rs.getInt("role_id"));
                user.setDepartment_id(rs.getInt("department_id"));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users updateUser(Users user) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "update Users set first_name = ?, last_name = ?, email = ?, username = ?, password = ?, role_id = ?, department_id = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getFirst_name());
            ps.setString(2, user.getLast_name());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole_id());
            ps.setInt(7, user.getDepartment_id());
            ps.setInt(8, user.getId());

            ps.execute();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users deleteUserById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "delete from Users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
