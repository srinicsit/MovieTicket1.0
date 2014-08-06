INSERT INTO `movie_ticket`.`user` (`PWD`, `USER_ID`,`USER_TYPE`) VALUES ('Admin', 'Admin',0);
INSERT INTO `movie_ticket`.`user` (`PWD`, `USER_ID`,`USER_TYPE`) VALUES ('tadmin', 'tadmin',2);
INSERT INTO `movie_ticket`.`user` (`PWD`, `USER_ID`,`USER_TYPE`) VALUES ('user', 'user',1);

/**
 * seat types start
 */
INSERT INTO `movie_ticket`.`seat_types` (`is_active`,`name`) VALUES ('y','Balcony');
INSERT INTO `movie_ticket`.`seat_types` (`is_active`,`name`) VALUES ('y','Gold Class');
INSERT INTO `movie_ticket`.`seat_types` (`is_active`,`name`) VALUES ('y','First Class');
INSERT INTO `movie_ticket`.`seat_types` (`is_active`,`name`) VALUES ('y','Silver Class');
INSERT INTO `movie_ticket`.`seat_types` (`is_active`,`name`) VALUES ('y','Bronze Class');

/**
 * seat types end
 */

/**
 * Certificate start
 */
INSERT INTO `movie_ticket`.`certificate` (`is_active`,`name`,`code`) VALUES ('y','Universal','U');
INSERT INTO `movie_ticket`.`certificate` (`is_active`,`name`,`code`) VALUES ('y','Parental Guidance','UA');
INSERT INTO `movie_ticket`.`certificate` (`is_active`,`name`,`code`) VALUES ('y','Adults Only','A');
INSERT INTO `movie_ticket`.`certificate` (`is_active`,`name`,`code`) VALUES ('y','Restricted to any special class of persons','S');
/**
 * Certificate end
 */


/**
 * languages start
 */
INSERT INTO `movie_ticket`.`language` (`is_active`,`name`) VALUES ('y','Telugu');
INSERT INTO `movie_ticket`.`language` (`is_active`,`name`) VALUES ('y','Tamil');
INSERT INTO `movie_ticket`.`language` (`is_active`,`name`) VALUES ('y','Hindi');
INSERT INTO `movie_ticket`.`language` (`is_active`,`name`) VALUES ('y','English');
/**
 * languages end
 */

INSERT INTO `movie_ticket`.`location` (`ID`,`name`) VALUES (1,'Guntur');

INSERT INTO `movie_ticket`.`theater` (IS_ACTIVE, LOCATION_ID, NAME, user_pk_id)values('Y', 1, 'harihar', 1);

INSERT INTO `movie` VALUES (1,'Y',NULL,NULL,NULL,NULL,2,'Telugu',30,'manam','2014-06-27 00:00:00');