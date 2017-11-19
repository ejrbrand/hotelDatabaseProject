CREATE DEFINER=`team10`@`%` PROCEDURE `getAvailableRooms`(X DATE, Y DATE, Z INT)
BEGIN

(SELECT reservedrooms.roomID
FROM reservedrooms NATURAL JOIN room
WHERE 
((X < dateIn AND
 X < dateOut AND
 Y < dateIn AND
 Y < dateOut )
 OR
 (X >= dateIn AND
 X >= dateOut AND
 Y >= dateIn AND
 Y>= dateOut))
 AND
 (roomTypeID = Z
 ))
 
 UNION
 
(SELECT roomID
FROM room
WHERE NOT EXISTS (SELECT roomID FROM reservedrooms));



END