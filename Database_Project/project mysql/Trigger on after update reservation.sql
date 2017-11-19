USE hotelreservation;

DELIMITER $$
CREATE DEFINER=`team10`@`%` TRIGGER after_update_reservation
	AFTER UPDATE ON reservation
	FOR EACH ROW 
	BEGIN
		insert into reservedroom(roomID, dateIn, dateOut, uID, bookingID) VALUES
		(new.roomID, new.dateIN, 
        new.dateOut, new.uID, new.bookingID);
	End$$
DELIMITER ;
