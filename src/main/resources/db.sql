CREATE SCHEMA `BEAUTY_SALON` DEFAULT CHARACTER SET utf8 ;


CREATE TABLE `BEAUTY_SALON`.`beauty_masters` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `avatar` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `BEAUTY_SALON`.`beauty_admins` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `BEAUTY_SALON`.`beauty_clients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `BEAUTY_SALON`.`beauty_requests` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `appointment_time` DATETIME NOT NULL,
  `client_id` INT(11) NOT NULL,
  `master_id` INT(11) NOT NULL,
  `status_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `BEAUTY_SALON`.`beauty_reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(255) NULL,
  `rating` INT NULL,
  `request_id` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `BEAUTY_SALON`.`beauty_status` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

ADD INDEX `fk_beauty_requests_3_idx` (`status_id` ASC);
ALTER TABLE `BEAUTY_SALON`.`beauty_requests`
ADD CONSTRAINT `fk_beauty_requests_1`
  FOREIGN KEY (`client_id`)
  REFERENCES `BEAUTY_SALON`.`beauty_clients` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_beauty_requests_2`
  FOREIGN KEY (`master_id`)
  REFERENCES `BEAUTY_SALON`.`beauty_masters` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_beauty_requests_3`
  FOREIGN KEY (`status_id`)
  REFERENCES `BEAUTY_SALON`.`beauty_status` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `BEAUTY_SALON`.`beauty_reviews`
ADD INDEX `fk_beauty_reviews_1_idx` (`request_id` ASC);
ALTER TABLE `BEAUTY_SALON`.`beauty_reviews`
ADD CONSTRAINT `fk_beauty_reviews_1`
  FOREIGN KEY (`request_id`)
  REFERENCES `BEAUTY_SALON`.`beauty_requests` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;