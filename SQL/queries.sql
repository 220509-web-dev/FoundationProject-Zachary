set search_path to foundation_project

-- All users from the users table
select * from users;

-- Update the user from users table with id 7
update users
set first_name = 'Ethan'
where id = 7;

-- join users with roles
select * 
from users u 
right join roles r 
on u.role_id = r.id;
