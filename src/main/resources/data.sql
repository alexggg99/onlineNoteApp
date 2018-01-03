insert into users(username, password, fullname, state, role) VALUES ('user', '123456', 'user', 0, 2);
insert into users(username, password, fullname, state, role) VALUES ('admin', '123456', 'admin', 0, 0);


insert into notes(user_id, note, created_at) VALUES (1, 'test note 1', {ts '2016-09-17 18:47:52.69'});
insert into notes(user_id, note, created_at) VALUES (1, 'test note 2', {ts '2015-11-17 14:17:00.23'});
insert into notes(user_id, note, created_at) VALUES (2, 'test note 1', {ts '2017-03-22 13:00:13.33'});