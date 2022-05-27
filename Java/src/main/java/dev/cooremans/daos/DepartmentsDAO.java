package dev.cooremans.daos;

import dev.cooremans.entities.Departments;

import java.util.List;

public interface DepartmentsDAO {

    // CREATE
    Departments createDepartment(Departments department);

    // READ
    Departments getDepartmentById(int department_id);
    List<Departments> getAllDepartments();

    // UPDATE
    Departments updateDepartment(Departments department);

    // DELETE
    Departments deleteById(int department_id);
}
