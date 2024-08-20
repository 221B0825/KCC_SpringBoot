INSERT INTO users(id, name, join_date, password, ssn) values (9001, 'kim', now(), 'pass9001', '001010-1111111');
INSERT INTO users(id, name, join_date, password, ssn) values (9002, 'park', now(), 'pass9002', '001010-1111112');
INSERT INTO users(id, name, join_date, password, ssn) values (9003, 'lee', now(), 'pass9003', '001010-1111113');

INSERT INTO post(description, user_id) values ('my first post', 9001);
INSERT INTO post(description, user_id) values ('my second post', 9002);