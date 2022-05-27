package dev.cooremans.entities;

public class Roles {
    private int role_id;
    private String role;

    public Roles(int role_id, String role) {
        this.role_id = role_id;
        this.role = role;
    }

    public Roles() {
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role_id=" + role_id +
                ", role='" + role + '\'' +
                '}';
    }
}
