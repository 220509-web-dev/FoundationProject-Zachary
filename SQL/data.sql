CREATE schema foundation_project;

SET search_path TO foundation_project;

CREATE TABLE users (
    id         int GENERATED ALWAYS AS identity,
    first_name  varchar NOT NULL,
    last_name  varchar NOT NULL,
    email      varchar UNIQUE NOT NULL,
    username   varchar UNIQUE NOT NULL,
    password   varchar NOT NULL check (length(password) >= 6),
    role_id    int,

    CONSTRAINT users_pk
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id  int GENERATED ALWAYS AS identity,
    role varchar UNIQUE NOT NULL
);


