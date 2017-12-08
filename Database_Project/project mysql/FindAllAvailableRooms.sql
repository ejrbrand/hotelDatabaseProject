SELECT roomID FROM ROOM as  R
WHERE RoomTypeID = 4 AND NOT EXISTS 
(SELECT * FROM reservedrooms 
 WHERE RoomID = R.RoomID
    AND dateOut >= '2017-12-20'
    AND dateIn <= '2017-12-27'
)