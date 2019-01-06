--mysql -h 127.0.0.1 -u root -p **********
CREATE DATABASE palchiki_studio;

USE palchiki_studio;

--DROP TABLE tbl_master_events;

CREATE TABLE tbl_master_events
(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    master_name VARCHAR(200) NOT NULL,
    teacher_name VARCHAR(150),
    description VARCHAR(1000) NOT NULL,
    coast INTEGER NOT NULL,
    event_date DATETIME NOT NULL,
    img_name VARCHAR(1000) DEFAULT 'default.jpg',
    is_deleted ENUM('0', '1') DEFAULT '0',
    creation_date DATETIME DEFAULT now(),
    modification_date DATETIME ON UPDATE now()
);

INSERT INTO tbl_master_events
    (master_name, teacher_name, description, event_date, coast, img_name)
VALUES
    ('first_master', 'first_teacher', 'first_descrition', '2020-01-10 10:00:00', 400, '2.jpg');

INSERT INTO tbl_master_events
    (master_name, teacher_name, description, event_date, coast, img_name)
VALUES
    ('second_master', 'second_teacher', 'second_descrition', '2019-10-11 10:00:00', 900, '4.jpg');

INSERT INTO tbl_master_events
    (master_name, teacher_name, description, event_date, coast, img_name)
VALUES
    ('third_master', 'third_teacher', 'third_descrition', '2017-08-27 12:00:00', 300, '6.jpg');

INSERT INTO tbl_master_events
    (master_name, teacher_name, description, event_date, coast)
VALUES
    ('forth_master', 'forth_teacher', 'forth_descrition', '2020-09-23 11:45:00', 300);

SELECT * FROM tbl_master_events;