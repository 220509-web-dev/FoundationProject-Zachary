package dev.cooremans.daos;

import dev.cooremans.entities.Departments;
import dev.cooremans.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDaoPostgres implements DepartmentsDAO{

    @Override
    public Departments createDepartment(Departments department) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "insert into departments values (default, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, department.getDepartment_name());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("department_id");

            department.setDepartment_id(generatedId);
            return department;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Departments getDepartmentById(int department_id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from Departments where department_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, department_id);
            ResultSet rs = ps.executeQuery();

            rs.next();

            Departments departments = new Departments();
            departments.setDepartment_id(rs.getInt("department_id"));
            departments.setDepartment_name(rs.getString("department_name"));
            return departments;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Departments> getAllDepartments() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from Departments";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Departments> departments = new ArrayList<Departments>();

            while(rs.next()){
                Departments department = new Departments();
                department.setDepartment_id(rs.getInt("department_id"));
                department.setDepartment_name(rs.getString("department_name"));
                departments.add(department);
            }
            return departments;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Departments updateDepartment(Departments department) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "update Departments set department_name = ? where department_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, department.getDepartment_name());
            ps.setInt(2, department.getDepartment_id());

            ps.execute();

            return department;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Departments deleteById(int department_id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "delete from Departments where department_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, department_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
