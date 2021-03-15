INSERT INTO USER_INFO(NAME, SURNAME, EMAIL, JOINED_AT, PICTURE_URL)
VALUES ('Agnieszka', 'Leszczuk', 'TEST1@GMAIL.COM', '2020-11-01', 'www.test.com'),
       ('Marek', 'Repeła', 'TEST2@GMAIL.COM', '2020-11-01', 'www.test.com'),
       ('Maciej', 'Pszczoła', 'TEST3@GMAIL.COM', '2020-11-01', 'www.test.com'),
       ('Mateusz', 'Czerwiński', 'TEST4@GMAIL.COM', '2020-11-01', 'www.test.com'),
       ('Wojciech', 'Zabiegło', 'TEST5@GMAIL.COM', '2020-11-01', 'www.test.com'),
        ('Bartosz', 'Kozłowski', 'TEST6@GMAIL.COM', '2020-11-01', 'www.test.com');


INSERT INTO TEAM(NAME)
VALUES ('DLSC1');



INSERT INTO APPLICATION_USER (APPLICATION_USE_ROLE, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED,
                              IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED, PASSWORD, USERNAME, USER_INFO_ID, TEAM_ID)
VALUES ('ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user1', 1,
        1),
       ('ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user2', 2,
        1),
       ('TECHNICAL_PROJECT_MANAGER', true, true, true, true,
        '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'tpjm', 3, 1),
       ('LEAD_ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC',
        'user3', 4, 1),
       ('LEAD_ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC',
        'user4', 6, 1),
       ('TEAM_LEADER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC',
        'teamleader', 5, 1);

UPDATE TEAM
SET TEAM_LEADER_ID = 5
WHERE ID = 1;


INSERT INTO WORK_PACKAGE(DESCRIPTION, NAME, ASSIGNED_TECHNICAL_PROJECT_MANAGER_ID, ASSIGNED_LEAD_ENGINEER_ID, TEAM_ID,
                         START_DATE, DEADLINE, IS_FINISHED, STATUS)
VALUES ('LOREM IPSUM', 'DTNA', 3, 4, 1, '2020-11-01', '2021-01-01', TRUE, 1),
       ('LOREM IPSUM', 'HMC', 3, 4, 1, '2020-11-01', '2021-02-01', FALSE, 0.46),
       ('LOREM IPSUM', 'DAF', 3, 4, 1, '2020-11-01', '2021-03-01', FALSE, 0.5),
       ('LOREM IPSUM', 'Volvo', 3, 4, 1, '2020-11-01', '2021-04-01', TRUE, 1),
       ('LOREM IPSUM', 'BMW', 3, 4, 1, '2020-11-01', '2021-05-01', FALSE, 0),
       ('LOREM IPSUM', 'AUDI', 3, 4, 1, '2020-11-01', '2021-06-01', FALSE, 0),
       ('LOREM IPSUM', 'Volvo', 3, 4, 1, '2020-11-01', '2021-07-01', FALSE, 0);

INSERT INTO WEEK(USER_ID, WEEK_NUMBER, YEAR_NUMBER)
VALUES (1, 10, 2021),
       (2, 10, 2021);

INSERT INTO DAY(DATE, DAY_NAME, WEEK_ID)
VALUES ('2021-03-08', 'MONDAY', 1),
       ('2021-03-09', 'TUESDAY', 1),
       ('2021-03-10', 'WEDNESDAY', 1),
       ('2021-03-11', 'THURSDAY', 1),
       ('2021-03-12', 'FRIDAY', 1),
       ('2021-03-08', 'MONDAY', 2),
       ('2021-03-09', 'TUESDAY', 2),
       ('2021-03-10', 'WEDNESDAY', 2),
       ('2021-03-11', 'THURSDAY', 2),
       ('2021-03-12', 'FRIDAY', 2);

INSERT INTO TASK(DESCRIPTION, DURATION, NAME, WORK_PACKAGE_ID, STATUS, ASSIGNED_ENGINEER_ID, DAY_ID, SORTING)
VALUES ('LOREM IPSUM', 1, 'CAD', 1, 1, 1, 1,1),
       ('LOREM IPSUM', 1, 'FEM', 1, 1, 1, 1,1),
       ('LOREM IPSUM', 1, 'MEETING', 1, 1, 1, 1,1),
       ('LOREM IPSUM', 1, 'DRAWING', 1, 1, 1, 1,1),
       ('LOREM IPSUM', 1, 'PLANISWARE', 1, 1, 1, 1,1),
       ('LOREM IPSUM', 1, 'FTROT', 1, 1, 1, 1,1),
       ('LOREM IPSUM', 1, 'FEM', 1, 1, 1, 2,2),
       ('LOREM IPSUM', 1, 'DRAWING', 1, 1, 1, 3,3),
       ('LOREM IPSUM', 1, 'ASSEMBLY', 1, 1, 1, 4,4),
       ('LOREM IPSUM', 1, 'DFMEA', 1, 1, 1, 5,5),
       ('LOREM IPSUM', 1, 'CAD', 2, 0.3, 1, 1,6),
       ('LOREM IPSUM', 1, 'FEM', 2, 0.6, 1, 2,7),
       ('LOREM IPSUM', 1, 'DRAWING', 2, 0.2, 1, 3,8),
       ('LOREM IPSUM', 1, 'ASSEMBLY', 2, 0.5, 1, 4,9),
       ('LOREM IPSUM', 1, 'DFMEA', 2, 0.5, 1, 5,10),
       ('LOREM IPSUM', 1, 'DFMEA', 2, 0.5, 1, 5,10),
       ('LOREM IPSUM', 1, 'DFMEA', 2, 0.5, 1, 5,10),
       ('LOREM IPSUM', 1, 'DFMEA', 2, 0.5, 1, 5,10),
       ('LOREM IPSUM', 1, 'DFMEA', 2, 0.5, 1, 5,10),
       ('LOREM IPSUM', 1, 'DFMEA', 2, 0.5, 1, 5,10),
       ('LOREM IPSUM', 1, 'DFMEA', 2, 0.5, 1, 5,10),
       ('LOREM IPSUM', 1, 'CAD', 3, 0.5, 2, 6,11),
       ('LOREM IPSUM', 1, 'FEM', 3, 0.5, NULL , 6,12),
       ('LOREM IPSUM', 1, 'DRAWING', 3, 0.5, 2, 7,13),
       ('LOREM IPSUM', 1, 'ASSEMBLY', 3, 0.5, 2, NULL,14),
       ('LOREM IPSUM', 1, 'DFMEA', 3, 0.5, 2, 8,15),
       ('LOREM IPSUM', 1, 'CAD', 4, 1, 2, 8,16),
       ('LOREM IPSUM', 1, 'FEM', 4, 1, 2, 9,17),
       ('LOREM IPSUM', 1, 'DRAWING', 4, 1, 2, 9,18),
       ('LOREM IPSUM', 1, 'ASSEMBLY', 4, 1, 2, 10,19),
       ('LOREM IPSUM', 1, 'DFMEA', 4, 1, 2, 10,20);
