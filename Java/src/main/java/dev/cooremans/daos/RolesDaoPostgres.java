package dev.cooremans.daos;

import dev.cooremans.entities.Roles;
import dev.cooremans.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolesDaoPostgres implements RolesDAO{

    @Override
    public Roles createRole(Roles role) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "insert into Roles values (default, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, role.getRole());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");

            role.setRole_id(generatedId);
            return role;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Roles getRoleById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from Roles where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            rs.next();

            Roles roles = new Roles();
            roles.setRole_id(rs.getInt("id"));
            roles.setRole(rs.getString("role"));
            return roles;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Roles> getAllRoles() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from Roles";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Roles> roles = new ArrayList<Roles>();

            while(rs.next()){
                Roles role = new Roles();
                role.setRole_id(rs.getInt("id"));
                role.setRole(rs.getString("role"));
                roles.add(role);
            }

            return roles;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Roles updateRole(Roles role) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "update Roles set role = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, role.getRole());
            ps.setInt(2, role.getRole_id());

            ps.execute();

            return role;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Roles deleteById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "delete from Roles where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
