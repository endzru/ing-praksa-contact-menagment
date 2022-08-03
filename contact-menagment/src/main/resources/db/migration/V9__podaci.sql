insert into  roles (role_name) VALUES ('admin');
insert into  roles (role_name) VALUES ('user');

insert into contact_type (contact_type_name) values ('friend');
insert into contact_type (contact_type_name) values ('family');
insert into contact_type (contact_type_name) values ('work');

insert into users (email, first_name,last_name,password,id_role,username) values ('astojanovic321@gmail.com', 'Andrija', 'Stojaonvic', 'asdffdsa', 1, 'endzru');
insert into users (email, first_name,last_name,password,id_role,username) values ('milanmilanovic@yahoo.com', 'Milan', 'Milanovic', 'weqwert', 2, 'milan');
insert into users (email, first_name,last_name,password,id_role,username) values ('bogdanbogdanovic@hotmail.com', 'Bogdan', 'Bogdanovic', 'ajdhflau', 2, 'bogi');

insert into contacts (id_contact_type, id_user, contact_first_name, contact_last_name, contact_email, contact_phonenumber) values (1,1,'jovan', 'spasic', 'jovan@gmail.com', '+381 60 1445944' );

