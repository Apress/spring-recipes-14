insert into USERS (username, password,enabled) values ('admin','secret', 1);
insert into USERS (username, password,enabled) values ('user1','1111', 1);
insert into USERS (username, password,enabled) values ('user2','2222', 0);

insert into AUTHORITIES (username, authority) values ('admin', 'ROLE_ADMIN');
insert into AUTHORITIES (username, authority) values ('admin', 'ROLE_USER');
insert into AUTHORITIES (username, authority) values ('user1', 'ROLE_USER');
insert into AUTHORITIES (username, authority) values ('user2', 'ROLE_USER');


