DROP PROCEDURE IF EXISTS fillRoutes;;
CREATE PROCEDURE fillRoutes()
  BEGIN
    DECLARE counter INT DEFAULT 0;
    WHILE counter < 1000 DO
      SET counter = counter + 1;
      INSERT INTO city_route (first, second, distance) VALUES (RAND() * 356, RAND() * 356, RAND() * 1000 + 1);
    END WHILE;
  END;;

CALL fillRoutes();;
DROP PROCEDURE fillRoutes;;

-- remove invalid values
DELETE FROM city_route WHERE first = second;;
-- remove connections to 3rd city in the list
DELETE FROM city_route WHERE first = 3 OR second = 3;;
