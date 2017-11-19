DROP DATABASE IF EXISTS hotelReservation;
CREATE DATABASE hotelReservation;
USE hotelReservation;

DROP TABLE IF EXISTS guest;
CREATE TABLE guest (
uID INT NOT NULL auto_increment,
username VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(50) NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
age INT,
email VARCHAR(50) NOT NULL,
PRIMARY KEY(uID)
);
ALTER TABLE guest auto_increment=1000;

DROP TABLE IF EXISTS room;
CREATE TABLE room (
roomID INT NOT NULL auto_increment,
roomTypeID INT NOT NULL REFERENCES roomType(roomTypeID) ,
roomNo INT NOT NULL,
primary key(roomID)
);
ALTER TABLE room auto_increment=1000;

DROP TABLE IF EXISTS roomType;
CREATE TABLE roomType (
roomTypeID INT NOT NULL,
description VARCHAR(50),
price INT NOT NULL,
smoke BOOLEAN,
max_capacity INT NOT NULL,
PRIMARY KEY(roomTypeID)
);

DROP TABLE IF EXISTS reservation;
CREATE TABLE reservation	(
bookingID INT NOT NULL PRIMARY KEY,
roomID INT NOT NULL REFERENCES room(roomID),
uID INT NOT NULL references guest(uID),
dateCheckIn DATE NOT NULL,
dateCheckOut DATE NOT NULL,
status BOOLEAN NOT NULL,
noOfPeople INT,
amountDue DOUBLE NOT NULL,
paid BOOLEAN NOT NULL DEFAULT FALSE,
comments VARCHAR(500)
);

DROP TABLE IF EXISTS PointsOfInterests;
CREATE TABLE PointsOfInterests	(
poiID INT NOT NULL PRIMARY KEY,
description VARCHAR(50),
name VARCHAR(50)
);

DROP TABLE IF EXISTS ratings;
CREATE TABLE ratings (
uID INT NOT NULL REFERENCES guest(uID),
bookingID INT NOT NULL REFERENCES reservation(bookingID),
stars INT NOT NULL,
feedback VARCHAR(50)
);