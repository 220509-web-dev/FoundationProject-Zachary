package dev.cooremans.entities;

import java.util.Objects;

public class Users {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String password;
    private int role_id;
    private int department_id;

    public Users() {
        super();
    }

    public Users(int id, String first_name, String last_name, String email, String username, String password, int role_id, int department_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.department_id = department_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && role_id == users.role_id && department_id == users.department_id && Objects.equals(first_name, users.first_name) && Objects.equals(last_name, users.last_name) && Objects.equals(email, users.email) && Objects.equals(username, users.username) && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,first_name, last_name, email, username, password, role_id, department_id);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_id=" + role_id +
                ", department_id=" + department_id +
                '}';
    }
}
