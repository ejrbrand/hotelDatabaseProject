USE hotelReservation;

CREATE TABLE reservedRooms	(

roomID int NOT NULL REFERENCES room(roomID),
dateIn DATE NOT NULL,
dateOut DATE NOT NULL,
uID int NOT NULL REFERENCES guest(uID),
bookingID int NOT NULL REFERENCES reservation(bookingID)

);