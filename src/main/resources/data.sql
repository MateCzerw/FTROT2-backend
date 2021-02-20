INSERT INTO USER_INFO(NAME,SURNAME,EMAIL,JOINED_AT, PICTURE_URL)
VALUES ('AGNIESZKA', 'LESZCZUK','TEST1@GMAIL.COM','2020-11-01','www.test.com'),
       ('MAREK', 'REPELA','TEST2@GMAIL.COM', '2020-11-01','www.test.com'),
       ('MACIEJ', 'PSZCZOLA','TEST3@GMAIL.COM', '2020-11-01','www.test.com'),
       ('MATEUSZ', 'CZERWIŃSKI','TEST4@GMAIL.COM', '2020-11-01','www.test.com'),
       ('WOJCIECH', 'ZABIEGŁO', 'TEST5@GMAIL.COM','2020-11-01','www.test.com');


INSERT INTO TEAM(NAME)
VALUES ( 'DLSC1');




INSERT INTO APPLICATION_USER (APPLICATION_USE_ROLE, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED,
                              IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED, PASSWORD, USERNAME, USER_INFO_ID, TEAM_ID)
VALUES ('ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user1',1,1),
       ('ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user2',2,1),
       ('TECHNICAL_PROJECT_MANAGER', true, true, true, true,
        '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'tpjm',3,1),
       ('LEAD_ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user3',4,1),
       ('TEAM_LEADER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC',
        'teamleader',5,1);

UPDATE TEAM
SET TEAM_LEADER_ID = 5
WHERE ID = 1;


INSERT INTO WORK_PACKAGE(DESCRIPTION, NAME, ASSIGNED_TECHNICAL_PROJECT_MANAGER_ID, ASSIGNED_LEAD_ENGINEER_ID, TEAM_ID, START_DATE,DEADLINE)
VALUES ('LOREM IPSUM', 'DTNA',3,4,1,'2020-11-01','2020-12-31'),
       ('LOREM IPSUM', 'HMC',3,4,1,'2020-11-01','2020-12-31'),
       ('LOREM IPSUM', 'DAF',3,4,1,'2020-11-01','2020-12-31'),
       ('LOREM IPSUM', 'Volvo',3,4,1,'2020-11-01','2020-12-31');

INSERT INTO WEEK(USER_ID, WEEK_NUMBER, YEAR_NUMBER)
VALUES (1,1,2021),
       (2,1,2021);

INSERT INTO DAY(DATE, DAY_NAME, WEEK_ID)
VALUES ('2020-11-01','MONDAY', 1),
       ('2020-11-01','TUESDAY', 1),
       ('2020-11-01','WEDNESDAY', 1),
       ('2020-11-01','THURSDAY', 1),
       ('2020-11-01','FRIDAY', 1),
       ('2020-11-01','MONDAY', 2),
       ('2020-11-01','TUESDAY', 2),
       ('2020-11-01','WEDNESDAY', 2),
       ('2020-11-01','THURSDAY', 2),
       ('2020-11-01','FRIDAY', 2);

INSERT INTO TASK(DESCRIPTION,DURATION, NAME, IS_IN_BACKLOG, WORK_PACKAGE_ID,STATUS,ASSIGNED_ENGINEER_ID, DAY_ID )
VALUES ('LOREM IPSUM',1, 'CAD',false, 1,0.3,1,1),
       ('LOREM IPSUM',1, 'FEM',false, 1,0.2,1,2),
       ('LOREM IPSUM',1, 'DRAWING',false, 1,0.1,1,3),
       ('LOREM IPSUM',1, 'ASSEMBLY',false, 1,0.7,1,4),
       ('LOREM IPSUM',1, 'DFMEA',false, 1,0.9,1,5),
       ('LOREM IPSUM',1, 'CAD',false, 2,0.3,1,1),
       ('LOREM IPSUM',1, 'FEM',false, 2,0.6,1,2),
       ('LOREM IPSUM',1, 'DRAWING',false, 2,0.2,1,3),
       ('LOREM IPSUM',1, 'ASSEMBLY',false, 2,0.5,1,4),
       ('LOREM IPSUM',1, 'DFMEA',false, 2,0.5,2,5),
       ('LOREM IPSUM',1, 'CAD',false, 3,0.5,2,6),
       ('LOREM IPSUM',1, 'FEM',false, 3,0.5,2,6),
       ('LOREM IPSUM',1, 'DRAWING',false,3, 0.5,2,7),
       ('LOREM IPSUM',1, 'ASSEMBLY',false,3, 0.5,2,7),
       ('LOREM IPSUM',1, 'DFMEA',false, 3,0.5,2,8),
       ('LOREM IPSUM',1, 'CAD',false, 4,0.5,2,8),
       ('LOREM IPSUM',1, 'FEM',false, 4,0.5,2,9),
       ('LOREM IPSUM',1, 'DRAWING',false, 4,0.5,2,9),
       ('LOREM IPSUM',1, 'ASSEMBLY',false, 4,0.5,2,10),
       ('LOREM IPSUM',1, 'DFMEA',false, 4,0.5,2,10);
