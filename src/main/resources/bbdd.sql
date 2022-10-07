CREATE SCHEMA IF NOT EXISTS `BOOKING_RESTAURANT` DEFAULT CHARACTER SET utf8 ;
USE `BOOKING_RESTAURANT` ;

-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`RESTAURANT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKING_PLAZA`.`RESTAURANTS` (
  `ID_RESTAURANT` INT(19) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL,
  `DESCRIPTION` VARCHAR(100) NULL,
  `ADDRESS` VARCHAR(100) NULL,
  `IMAGE` VARCHAR(500) NULL,
  PRIMARY KEY (`ID_RESTAURANT`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`RESERVATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKING_PLAZA`.`RESERVATION` (
  `ID_RESERVATION` INT(19) NOT NULL AUTO_INCREMENT,
  `LOCATOR` VARCHAR(45) NULL,
  `PERSONS` INT(19) NULL,
  `DATE` DATE NULL,
  `TURN` VARCHAR(45) NULL,
  `RESTAURANT_ID` INT(19) NOT NULL,
  PRIMARY KEY (`ID_RESERVATION`),
  INDEX `fk_RESERVATION_RESTAURANTE_idx` (`RESTAURANT_ID` ASC),
  CONSTRAINT `fk_RESERVATION_RESTAURANTE`
    FOREIGN KEY (`RESTAURANTE_ID`)
    REFERENCES `BOOKING_RESTAURANT`.`RESTAURANT` (`ID_RESTAURANT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`TURN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKING_PLAZA`.`TURN` (
  `ID_TURN` int(19)  AUTO_INCREMENT NOT NULL ,
  `NAME` VARCHAR(45) NULL,
  `RESTAURANT_ID` INT(19) NOT NULL,
  PRIMARY KEY (`ID_TURN`),
  INDEX `fk_TURN_RESTAURANT1_idx` (`RESTAURANT_ID` ASC) ,
  CONSTRAINT `fk_TURN_RESTAURANT1`
    FOREIGN KEY (`RESTAURANT_ID`)
    REFERENCES `BOOKING_RESTAURANT`.`RESTAURANT` (`ID_RESTAURANT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`BOARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKING_PLAZA`.`BOARD` (
  `ID_BOARD` INT(19) NOT NULL AUTO_INCREMENT,
  `CAPACITY` INT(19) NULL,
  `NUMBER` INT(19) NULL,
  `RESTAURANT_ID` INT(19) NOT NULL,
  PRIMARY KEY (`ID_BOARD`),
  INDEX `fk_BOARD_RESTAURANT1_idx` (`RESTAURANT_ID` ASC) ,
  CONSTRAINT `fk_BOARD_RESTAURANT1`
    FOREIGN KEY (`RESTAURANT_ID`)
    REFERENCES `BOOKING_RESTAURANT`.`RESTAURANT` (`ID_RESTAURANT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- UPDATE Table `BOOKING_RESTAURANT`.`RESTAURANT`
-- -----------------------------------------------------
ALTER TABLE `booking_restaurant`.`restaurants`
ADD COLUMN `PRICE` INT(19) NULL AFTER `IMAGE`;

-- -----------------------------------------------------
-- UPDATE Table `BOOKING_RESTAURANT`.`RESERVATION`
-- -----------------------------------------------------
ALTER TABLE `booking_restaurant`.`reservation`
ADD COLUMN `NAME` VARCHAR(45) NULL AFTER `RESTAURANT_ID`,
ADD COLUMN `EMAIL` VARCHAR(45) NULL AFTER `NAME`;

-- -----------------------------------------------------
-- Table `BOOKING_RESTAURANT`.`NOTIFICATION`
-- -----------------------------------------------------
CREATE TABLE `booking_restaurant`.`notification` (
  `ID` INT(19) NOT NULL AUTO_INCREMENT,
  `TEMPLATE` VARCHAR(800) NULL,
  `TEMPLATE_TYPE` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
  ENGINE = InnoDB;

ALTER TABLE `booking_restaurant`.`notification`
CHANGE COLUMN `TEMPLATE_TYPE` `TEMPLATE_CODE` VARCHAR(45) NULL DEFAULT NULL ;

Reinicio de Contador Auto Increment:
ALTER TABLE tblname AUTO_INCREMENT = 0;

ALTER TABLE `booking_restaurant`.`reservation`
ADD COLUMN `PAYMENT` BLOB NULL AFTER `EMAIL`;