INSERT INTO `BEAUTY_SALON`.`beauty_status` (`id`, `name`) VALUES ('1', 'Active');
INSERT INTO `BEAUTY_SALON`.`beauty_status` (`id`, `name`) VALUES ('2', 'Done');
INSERT INTO `BEAUTY_SALON`.`beauty_status` (`id`, `name`) VALUES ('3', 'Canceled');

INSERT INTO `BEAUTY_SALON`.`beauty_masters` (`id`, `login`, `password`, `name`, `description`, `avatar`) VALUES ('1', 'bessonov@beauty.com', '$2a$10$JY8cZc3h2m5yX0626rW1bOWJE1Oh4lqyaCO1X.BNhC7U5.eNydi8a', 'Бессонов Богдан', 'Креативний опис', 'avatar1.jpg');

INSERT INTO `BEAUTY_SALON`.`beauty_masters` (`id`, `login`, `password`, `name`, `description`, `avatar`) VALUES ('2', 'yegorov@beauty.com', '$2a$10$JY8cZc3h2m5yX0626rW1bOWJE1Oh4lqyaCO1X.BNhC7U5.eNydi8a', 'Єгоров Ілля', 'Креативний опис', 'avatar2.jpg');

INSERT INTO `BEAUTY_SALON`.`beauty_masters` (`id`, `login`, `password`, `name`, `description`, `avatar`) VALUES ('3', 'shaposhnik@beauty.com', '$2a$10$JY8cZc3h2m5yX0626rW1bOWJE1Oh4lqyaCO1X.BNhC7U5.eNydi8a', 'Шапошнік Андрій', 'Креативний опис', 'avatar3.jpg');


INSERT INTO `BEAUTY_SALON`.`beauty_admins` (`id`, `login`, `password`, `phone`) VALUES ('1', 'admin@admin.com', '$2a$10$JY8cZc3h2m5yX0626rW1bOWJE1Oh4lqyaCO1X.BNhC7U5.eNydi8a', '380631066143');
