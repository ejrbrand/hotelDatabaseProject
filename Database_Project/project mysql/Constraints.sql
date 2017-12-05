USE hotelReservation;

ALTER TABLE ratings
ADD CONSTRAINT fk_bookingID_ratings
FOREIGN KEY (bookingID) REFERENCES reservation(bookingID);

ALTER TABLE room
ADD CONSTRAINT fk_roomTypeID_room
FOREIGN KEY (roomTypeID) REFERENCES roomtype(roomTypeID);

ALTER TABLE reservation
ADD CONSTRAINT fk_roomID_reservation
FOREIGN KEY (roomID) REFERENCES room(roomID);

ALTER TABLE reservation
ADD CONSTRAINT fk_uID_reservation
FOREIGN KEY (uID) REFERENCES guest(uID);