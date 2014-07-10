INSERT INTO `movie_ticket`.`user` (`PWD`, `USER_ID`,`USER_TYPE`) VALUES ('Admin', 'Admin',0);
INSERT INTO `movie_ticket`.`user` (`PWD`, `USER_ID`,`USER_TYPE`) VALUES ('tadmin', 'tadmin',2);
INSERT INTO `movie_ticket`.`user` (`PWD`, `USER_ID`,`USER_TYPE`) VALUES ('user', 'user',1);

INSERT INTO `movie_ticket`.`location` (`ID`,`name`) VALUES (1,'Guntur');

INSERT INTO `movie_ticket`.`theater` (IS_ACTIVE, LOCATION_ID, NAME, user_pk_id)values('Y', 1, 'harihar', 1);

INSERT INTO `movie` VALUES (1,'Y',NULL,NULL,NULL,NULL,2,'Telugu',30,'manam','2014-06-27 00:00:00');