/*
	Procedure to get comment tree for 
    crosscampus API by xcampus database
	by Kazuma Sato 100948212 kazuma.sato@georgebrown.ca
    Date created: Feb 27, 2017
    Date last modified Feb 27, 2017
*/


-- TODO: This is just something I found ONLINE! STILL NEEDS TO BE TWEEKED
CREATE PROCEDURE get_tree(IN id int)
 BEGIN
 DECLARE child_id int;
 DECLARE prev_id int;
 SET prev_id = id;
 SET child_id=0;
 SELECT col3 into child_id 
 FROM table1 WHERE col1=id ;
 create TEMPORARY  table IF NOT EXISTS temp_table as (select * from table1 where 1=0);
 truncate table temp_table;
 WHILE child_id <> 0 DO
   insert into temp_table select * from table1 WHERE col1=prev_id;
   SET prev_id = child_id;
   SET child_id=0;
   SELECT col3 into child_id
   FROM TABLE1 WHERE col1=prev_id;
 END WHILE;
 select * from temp_table;
 END //