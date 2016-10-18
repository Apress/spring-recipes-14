insert into USERS (id,username, password) values (1,'admin','secret');
insert into USERS (id,username, password) values (2,'user1','1111');

insert into AUTHORITIES (member_id,role) values (1, 'ROLE_ADMIN');
insert into AUTHORITIES (member_id,role) values (1, 'ROLE_USER');
insert into AUTHORITIES (member_id,role) values (2, 'ROLE_USER');


