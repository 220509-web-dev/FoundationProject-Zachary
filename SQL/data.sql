CREATE schema foundation_project;

SET search_path TO foundation_project;

CREATE TABLE roles (
    id  int GENERATED ALWAYS AS identity primary key,
    role varchar UNIQUE NOT NULL
);

CREATE TABLE departments (
    department_id int GENERATED ALWAYS AS identity PRIMARY KEY,
    department_name varchar NOT NULL
);


CREATE TABLE users (
    id         int GENERATED ALWAYS AS identity,
    first_name  varchar NOT NULL,
    last_name  varchar NOT NULL,
    email      varchar UNIQUE NOT NULL,
    username   varchar UNIQUE NOT NULL,
    password   varchar NOT NULL check (length(password) >= 6),
    role_id    int,
    department_id int,

    FOREIGN key (role_id)
    REFERENCES roles(id), 

    FOREIGN KEY (department_id)
    REFERENCES departments(department_id)
);
