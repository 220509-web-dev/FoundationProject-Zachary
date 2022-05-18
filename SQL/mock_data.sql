SET search_path to foundation_project;

-- Create some base user roles
insert into roles(role)
VALUES ('Enginner 1'), ('Engineer 2'), ('Janiter');

-- create a new user
insert into users (first_name, last_name, email, username, password, role_id)
VALUES ('Zachary', 'Cooremans', 'zcooremans@gmail.com', 'ZacharyC', 'Password', 1);