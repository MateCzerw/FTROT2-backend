insert into application_user(APPLICATION_USE_ROLE, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED,
                             IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED, PASSWORD, USERNAME)
values ('ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user1'),
       ('ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user2'),
       ('ENGINEER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user3'),
       ('TEAM_LEADER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'teamleader');