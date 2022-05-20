SET search_path to foundation_project;

-- Create some base user roles
insert into roles(role)
VALUES ('Enginner 1'), ('Engineer 2'), ('Senior Developer'), ('Intern');

-- Create departments
insert into departments(department_name)
values ('Quality Assurance'), ('Front End'), ('Back End');

-- create new users
insert into users (first_name, last_name, email, username, password, role_id, department_id)
VALUES ('Zachary', 'Cooremans', 'zcooremans@gmail.com', 'ZacharyC', 'Password', 1, 2),
       ('Nadia', 'Salazar', 'Nadarada@gmail.com', 'Salad', '1234567', 2, 2),
       ('Joe', 'Mamma', 'joemam@yahoo.com', 'JoeM', 'aCoolPassword', 3, 3),
       ('Bob', 'Bailey', 'BB@gmail,com', 'BigBob', 'aVeryUniquePassowrd', 4, 2),
       ('Charley', 'Chaston', 'CharleyChas@yahoo.com', 'CharleyChase', 'CharleyIsAwesome', 2, 1),
       ('Dan', 'Dantion', 'danDant@revature.com', 'DannyDan', 'Dan01Dan10', 4, 3),
       ('East', 'Echo', 'Eastern@gmail.com', 'EasternEck', 'IJUSTNOWMADETHISUP', 4, 1);