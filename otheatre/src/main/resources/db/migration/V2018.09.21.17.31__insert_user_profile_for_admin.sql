ALTER TABLE `user_profile`
  MODIFY `user_profile_id` int(11) NOT NULL AUTO_INCREMENT

INSERT INTO `user_profile` (`bio`, `birthday`, `email`, `first_name`, `last_name`, `link_to_profile_picture`, `user_user_id`) VALUES
("Sunt un boss bre", '1994-05-21', 'admin@otheatre.com', 'admin','adminescu', NULL, 1);