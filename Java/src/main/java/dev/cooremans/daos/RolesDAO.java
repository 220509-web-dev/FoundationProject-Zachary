package dev.cooremans.daos;

import dev.cooremans.entities.Roles;

import java.util.List;

public interface RolesDAO {

    // CREATE
    Roles createRole(Roles role);

    // READ
    Roles getRoleById(int id);
    List<Roles> getAllRoles();

    // UPDATE
    Roles updateRole(Roles role);

    // DELETE
    Roles deleteById(int id);
}
