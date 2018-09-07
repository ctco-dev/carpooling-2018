INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, NAME, SURNAME, PHONENUMBER) VALUES (-1, 'admin', 'PBKDF2WithHmacSHA256:2048:McY8GmR3k1PjMwJQE97wYxbdCuJ2f7bBfwiNlOMT2Bc=:qnB+F68kaLGTITEiYfg8s+7+L1/5AKbiWgkKC4g2VKE=', 'ADMIN', 'Hans', 'Landa', '1111111');
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, NAME, SURNAME, PHONENUMBER) VALUES (-2, 'user1', 'PBKDF2WithHmacSHA256:2048:McY8GmR3k1PjMwJQE97wYxbdCuJ2f7bBfwiNlOMT2Bc=:qnB+F68kaLGTITEiYfg8s+7+L1/5AKbiWgkKC4g2VKE=', 'USER', 'Danila', 'Bagrov', '2222222');
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE, NAME, SURNAME, PHONENUMBER) VALUES (-3, 'user2', 'PBKDF2WithHmacSHA256:2048:McY8GmR3k1PjMwJQE97wYxbdCuJ2f7bBfwiNlOMT2Bc=:qnB+F68kaLGTITEiYfg8s+7+L1/5AKbiWgkKC4g2VKE=', 'USER', 'Anakin', 'Skywalker', '3333333');
INSERT INTO TRIPS (ID, DRIVER_ID, DEPARTURE, DESTINATION, PLACES, DEPARTURETIME, ISEVENT, EVENT_ID, TRIPSTATUS) VALUES (-1, -2, 'PURVCIEMS', 'CTCO', 3, '10:00', FALSE,NULL , 'ACTIVE');
INSERT INTO TRIPS (ID, DRIVER_ID, DEPARTURE, DESTINATION, PLACES, DEPARTURETIME, ISEVENT, EVENT_ID, TRIPSTATUS) VALUES (-2, -1, 'CTCO', 'CENTRS', 3, '18:00', FALSE,NULL , 'ACTIVE');
INSERT INTO TRIPS (ID, DRIVER_ID, DEPARTURE, DESTINATION, PLACES, DEPARTURETIME, ISEVENT, EVENT_ID, TRIPSTATUS) VALUES (-3, -2, 'CTCO', 'MEZCIEMS', 4, '19:00', FALSE ,NULL , 'FINISHED');
INSERT INTO TRIPS (ID, DRIVER_ID, DEPARTURE, DESTINATION, PLACES, DEPARTURETIME, ISEVENT, EVENT_ID, TRIPSTATUS) VALUES (-4, -3, 'JUGLA', 'CTCO', 2, '09:00', FALSE ,NULL , 'ACTIVE');
INSERT INTO TRIPS (ID, DRIVER_ID, DEPARTURE, DESTINATION, PLACES, DEPARTURETIME, ISEVENT, EVENT_ID, TRIPSTATUS) VALUES (-5, -2, 'CTCO', 'VECMILGRAVIS', 3, '19:30', FALSE, NULL , 'ACTIVE');
INSERT INTO CARS (ID, CARMODEL, CARCOLOR, CARNUMBER) VALUES (-1, 'Shevrolet Camaro', 'yellow', 'BUMBLEBEE');
INSERT INTO CARS (ID, CARMODEL, CARCOLOR, CARNUMBER) VALUES (-2, 'Skoda Oktavia RS', 'black', 'R248NN');
INSERT INTO CARS (ID, CARMODEL, CARCOLOR, CARNUMBER) VALUES (-3, 'Audi A6', 'red', 'LV-1013');
INSERT INTO CARS (ID, CARMODEL, CARCOLOR, CARNUMBER) VALUES (-4, 'Volkswagen Tiguan', 'black', 'LV-1122');
UPDATE USERS SET CAR_ID = -1 WHERE ID = -1;
UPDATE USERS SET CAR_ID = -2 WHERE ID = -2;
UPDATE USERS SET CAR_ID = -4 WHERE ID = -3;
INSERT INTO USER_TRIP (TRIP_ID, USER_ID) VALUES (-1, -3);
INSERT INTO USER_TRIP (TRIP_ID, USER_ID) VALUES (-2, -2);
INSERT INTO USER_TRIP (TRIP_ID, USER_ID) VALUES (-2, -3);