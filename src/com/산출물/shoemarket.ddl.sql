-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema shoemarket
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema shoemarket
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shoemarket` DEFAULT CHARACTER SET utf8mb3 ;
USE `shoemarket` ;

-- -----------------------------------------------------
-- Table `shoemarket`.`brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoemarket`.`brand` (
  `brandNo` INT NOT NULL AUTO_INCREMENT,
  `brandName` VARCHAR(20) NULL,
  PRIMARY KEY (`brandNo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shoemarket`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoemarket`.`product` (
  `productCode` INT NOT NULL AUTO_INCREMENT,
  `brandNo` INT NOT NULL,
  `productName` VARCHAR(45) NULL DEFAULT NULL,
  `productPrice` INT NULL DEFAULT NULL,
  `productInsertdate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `productUpdatedate` DATETIME NULL DEFAULT NULL,
  `productDeletedate` DATETIME NULL DEFAULT NULL,
  `productImageName` VARCHAR(45) NULL,
  `productImage` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`productCode`, `brandNo`),
  UNIQUE INDEX `productCode_UNIQUE` (`productCode` ASC) VISIBLE,
  INDEX `fk_product_brand1_idx` (`brandNo` ASC) VISIBLE,
  CONSTRAINT `fk_product_brand1`
    FOREIGN KEY (`brandNo`)
    REFERENCES `shoemarket`.`brand` (`brandNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoemarket`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoemarket`.`user` (
  `userid` VARCHAR(20) NOT NULL COMMENT '유저 아이디',
  `userPassword` VARCHAR(20) NULL DEFAULT NULL,
  `userName` VARCHAR(10) NULL DEFAULT NULL,
  `userPhone` VARCHAR(20) NULL DEFAULT NULL,
  `userAddress` VARCHAR(45) NULL DEFAULT NULL,
  `userEmail` VARCHAR(45) NULL DEFAULT NULL,
  `userInsertdate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `userUpdatedate` DATETIME NULL DEFAULT NULL,
  `userDeletedate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE INDEX `userid_UNIQUE` (`userid` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoemarket`.`productOption`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoemarket`.`productOption` (
  `size` INT NOT NULL,
  `productCode` INT NOT NULL,
  `productStock` INT NULL,
  PRIMARY KEY (`size`, `productCode`),
  INDEX `fk_productSize_product1_idx` (`productCode` ASC) VISIBLE,
  CONSTRAINT `fk_productSize_product1`
    FOREIGN KEY (`productCode`)
    REFERENCES `shoemarket`.`product` (`productCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shoemarket`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoemarket`.`purchase` (
  `purchaseNo` INT NOT NULL,
  `userid` VARCHAR(20) NOT NULL,
  `productCode` INT NOT NULL,
  `size` INT NOT NULL,
  `purchaseInsertdate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `purchaseDeletedate` DATETIME NULL,
  `purchaseQty` INT NULL,
  PRIMARY KEY (`purchaseNo`, `userid`, `productCode`, `size`),
  INDEX `fk_user_has_product_product1_idx` (`productCode` ASC) VISIBLE,
  INDEX `fk_user_has_product_user1_idx` (`userid` ASC) VISIBLE,
  UNIQUE INDEX `purchaseNo_UNIQUE` (`purchaseNo` ASC) VISIBLE,
  INDEX `fk_purchase_productOption1_idx` (`size` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_product_user1`
    FOREIGN KEY (`userid`)
    REFERENCES `shoemarket`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_product_product1`
    FOREIGN KEY (`productCode`)
    REFERENCES `shoemarket`.`product` (`productCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_productOption1`
    FOREIGN KEY (`size`)
    REFERENCES `shoemarket`.`productOption` (`size`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoemarket`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoemarket`.`cart` (
  `cartNo` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(20) NOT NULL,
  `productCode` INT NOT NULL,
  `size` INT NOT NULL,
  `cartQty` INT NULL,
  PRIMARY KEY (`cartNo`, `userid`, `productCode`, `size`),
  INDEX `fk_user_has_product_product2_idx` (`productCode` ASC) VISIBLE,
  INDEX `fk_user_has_product_user2_idx` (`userid` ASC) VISIBLE,
  UNIQUE INDEX `cartNo_UNIQUE` (`cartNo` ASC) VISIBLE,
  INDEX `fk_cart_productOption1_idx` (`size` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_product_user2`
    FOREIGN KEY (`userid`)
    REFERENCES `shoemarket`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_product_product2`
    FOREIGN KEY (`productCode`)
    REFERENCES `shoemarket`.`product` (`productCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_productOption1`
    FOREIGN KEY (`size`)
    REFERENCES `shoemarket`.`productOption` (`size`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
