DROP DATABASE IF EXISTS ShoesShop;
CREATE DATABASE IF NOT EXISTS ShoesShop;
USE ShoesShop;

CREATE TABLE `Account`(
		id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    	username				VARCHAR(50) NOT NULL UNIQUE KEY,
		`password` 				VARCHAR(255) NOT NULL,
    	first_name				NVARCHAR(50) NOT NULL,
    	last_name				NVARCHAR(50) NOT NULL,
    	email					VARCHAR(50) NOT NULL,
        address					NVARCHAR(200) NOT NULL,
        phone					VARCHAR(20) NOT NULL,
        gender					ENUM('MALE','FEMALE','UNKNOWN'),
        birthday				DATE,
    	`role` 					ENUM('ADMIN','EMPLOYEE','CUSTOMER') NOT NULL,
        create_date				DATETIME DEFAULT NOW()
);


CREATE TABLE `ProductType`(
	type_id				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	type_name			NVARCHAR(50) NOT NULL UNIQUE KEY
    
);

DROP TABLE IF EXISTS `Sale`;
CREATE TABLE `Sale`(
	saleId				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    saleInfo			NVARCHAR(100) NOT NULL UNIQUE,
	percentSale			FLOAT NOT NULL,
	startSale			DATE,
	endSale				DATE
);

CREATE TABLE `Product`(
	id					INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`name`				NVARCHAR(50) NOT NULL,
	`description`		NVARCHAR(200) NOT NULL,
	image_url			VARCHAR(255) NOT NULL,
	type_id				INT UNSIGNED,
	price				FLOAT NOT NULL,
    gender_type 		ENUM('MALE','FEMALE','UNISEX'),
    saleID				INT UNSIGNED,
    FOREIGN KEY(saleID) REFERENCES `Sale`(saleID) ON DELETE SET NULL,
	FOREIGN KEY(type_id) REFERENCES `ProductType`(type_id) ON DELETE SET NULL
);

CREATE TABLE `ProductDetail`(
	id_detail		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	quantity		INT NOT NULL,
	img_url			VARCHAR(255) NOT NULL,
	color			NVARCHAR(30) NOT NULL,
	size			NVARCHAR(20) NOT NULL,
	product_id		INT UNSIGNED,
    FOREIGN KEY(product_id) REFERENCES `Product`(id) ON DELETE SET NULL,
	UNIQUE KEY unique_name_color_size (`color`, `size`, `product_id`)
);



DROP TABLE IF EXISTS `PaymentMethod`;
CREATE TABLE `PaymentMethod`(	
	paymentMethodId			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	paymentName				NVARCHAR(100) NOT NULL UNIQUE,
	descriptionPayment		NVARCHAR(200) NOT NULL
);

DROP TABLE IF EXISTS `Order`;
CREATE TABLE `Order`(
	orderId				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	customerId			INT UNSIGNED,
	orderDate			DATETIME DEFAULT NOW(),
	totalAmount			FLOAT NOT NULL,
	paymentMethodId		INT UNSIGNED,
    shipping_address	NVARCHAR(255) DEFAULT NULL,
    recipient_phone		VARCHAR(20)  DEFAULT NULL,
	oderStatus			ENUM('ADDED_TO_CARD', 'TO_PAY', 'TO_RECEIVE', 'COMPLETED', 'CANCELED', 'FEEDBACK_COMPLETED') NOT NULL,
	employeeId			INT UNSIGNED,
    FOREIGN KEY(employeeId) REFERENCES `Account`(id) ON DELETE SET NULL,
    FOREIGN KEY(customerId) REFERENCES `Account`(id) ON DELETE SET NULL,
    FOREIGN KEY(paymentMethodId) REFERENCES `PaymentMethod`(paymentMethodId) ON DELETE SET NULL
);



DROP TABLE IF EXISTS `OrderItem`;
CREATE TABLE `OrderItem`(	
	orderItemId			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	orderId				INT UNSIGNED,
	product_detailId	INT UNSIGNED,
	quantity			INT NOT NULL,
	sellPrice			FLOAT NOT NULL,
    subtotal			FLOAT NOT NULL,
	FOREIGN KEY(orderId) REFERENCES `Order`(orderId) ON DELETE SET NULL,
    FOREIGN KEY(product_detailId) REFERENCES `ProductDetail`(id_detail) ON DELETE SET NULL
);

DROP TABLE IF EXISTS `Feedback`;
CREATE TABLE `Feedback`(
	feedbackID			INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	customerId			INT UNSIGNED,
	productId			INT UNSIGNED,
	rating				ENUM('VERY_BAD','BAD','AVERAGE', 'GOOD','EXCELLENT') NOT NULL,
	`comment` 			NVARCHAR(255),
	feedbackDate		DATETIME DEFAULT NOW(),
    FOREIGN KEY(customerId) REFERENCES `Account`(id) ON DELETE SET NULL,
    FOREIGN KEY(productId) REFERENCES `Product`(id) ON DELETE SET NULL
);



USE ShoesShop;
-- Thêm dữ liệu vào bảng ProductType
INSERT INTO ProductType (type_name) VALUES
('Sneakers'),
('Sandals'),
('Boots'),
('Business Casual'),
('High heels');

-- Thêm dữ liệu vào bảng Sale
INSERT INTO Sale (saleInfo, percentSale, startSale, endSale) VALUES
('Summer Sale', 15.0, '2023-06-01', '2023-06-30'),
('Back to School Sale', 10.0, '2023-08-01', '2023-08-15');

-- Thêm dữ liệu vào bảng Product
INSERT INTO Product (`name`, `description`,  image_url, type_id, price, gender_type) VALUES
('Giày thể thao thời trang', 'Giày thể thao nam nữ, lót êm, thoáng gió, form chuẩn.','img/sp1.png', 1, 315000, 'UNISEX'),
('Giày cổ cao custom nữ', 'Giày cổ cao thêu hoa, phối màu xinh xắn, form 0.5.', 'img/sp3.png', 1, 250000, 'FEMALE'),
('Giày da nữ cổ thấp', 'Giày da mềm, có lót chống đau gót chân form 0.5', 'img/sp5.png', 3, 270000, 'FEMALE'),
('Giày thể thao nam cổ thấp', 'Giày thể thao nam, cổ thấp, lót êm, thoáng gió, form chuẩn.','img/sp6.png', 1, 320000, 'MALE'),
('Giày thể thao thời trang nam', 'Giày thể thao nam, cổ thấp, vải nhung, form 1.5.', 'img/sp8_be.png', 1, 320000, 'MALE'),
('Giày búp bê quai ngang', 'Giày da mềm, có lót chống đau gót chân, form 0.5', 'img/sp9nu_black.png', 2, 270000, 'FEMALE'),
('Giày sandal nữ dây chéo', 'Giày quai đan chéo, da mềm, thoáng, form chuẩn', 'img/sp10_white.png', 2, 300000, 'FEMALE'),
('Giày sandal nữ dây phối caro', 'Giày phối giả nơ thời trang, phù hợp đi học, đi làm, form 0.5','img/sp11nu_black.png',2, 175000,'FEMALE'),
('Giày thể thao nam phối viền', 'Giày thể thao nam, cổ thấp, vải nhung, form 1.5.','img/sp12_brown.png',1, 315000,'MALE'),
('Giày name phối caro', 'Giày nam, cổ thấp, vải thường, form chuẩn.', 'img/sp13black.png', 4, 350000,'MALE'),
('Giày da cổ cao nữ', 'Giày da mềm, có lót chống đau gót chân, form 0.5', 'img/sp14nu.png', 3, 415000, 'FEMALE'),
('Giày cao gót trơn', 'Giày cao gót trơn, dáng thông dụng, gót 7cm, form 0.5','img/sp15nu_white.png',5, 215000,'FEMALE'),
('Giày cao gót hở ngón dây mảnh', 'Giày cao gót dây mảng, gót 7cm, form 0.5','img/sp16nu_.png',5, 220000,'FEMALE'),
('Giày cao gót hở ngón quai ngang', 'Giày cao gót quai ngang phối nhúng, gót 5cm, form 0.5','img/sp17nu_black.png',5, 220000,'FEMALE'),
('Giày búp bê phối nơ', 'Giày búp bê gót bệt, form 0.5','img/sp18nu_black.png',2, 215000,'FEMALE'),
('Giày búp bê da phối quai ngang', 'Giày búp bê gót bệt,chất liệu da bóng, mềm, form 0.5','img/sp19nu_black.png',2, 220000,'FEMALE'),
('Giày búp bê trơn', 'Giày búp bê trơn gót bệt, form 0.5','img/sp20nu_white.png',2, 220000,'FEMALE'),
('Giày thể thao nữ phối logo', 'Giày thể thao nữ, mũi nhựa, cổ thấp, form chuẩn','img/sp21nu_.png',1, 220000,'FEMALE')
;

-- Thêm dữ liệu vào bảng ProductDetail
INSERT INTO ProductDetail (quantity, img_url, color, size, product_id) VALUES
(10, 'img/sp1_4.png', 'White', '38', 1),
(10, 'img/sp1_4.png', 'White', '39', 1),
(10, 'img/sp1_4.png', 'White', '40', 1),
(10, 'img/sp1_4.png', 'White', '41', 1),
(10, 'img/sp1_4.png', 'White', '42', 1),
(10, 'img/sp1_black.png', 'Black', '38', 1),
(10, 'img/sp1_black.png', 'Black', '39', 1),
(10, 'img/sp1_black.png', 'Black', '40', 1),
(10, 'img/sp1_black.png', 'Black', '41', 1),
(10, 'img/sp1_black.png', 'Black', '42', 1),
(10, 'img/sp1_gray.png', 'Gray', '38', 1),
(10, 'img/sp1_gray.png', 'Gray', '39', 1),
(10, 'img/sp1_gray.png', 'Gray', '40', 1),
(10, 'img/sp1_gray.png', 'Gray', '41', 1),
(10, 'img/sp1_gray.png', 'Gray', '42', 1),
(10, 'img/sp3_1.png', 'Green', '35', 2),
(10, 'img/sp3_1.png', 'Green', '36', 2),
(10, 'img/sp3_1.png', 'Green', '37', 2),
(10, 'img/sp3_1.png', 'Green', '38', 2),
(10, 'img/sp3_1.png', 'Green', '39', 2),
(10, 'img/sp3_1.png', 'Green', '40', 2),
(10, 'img/sp3_black.png', 'Black', '38', 2),
(10, 'img/sp3_black.png', 'Black', '39', 2),
(10, 'img/sp3_black.png', 'Black', '40', 2),
(10, 'img/sp3_black.png', 'Black', '36', 2),
(10, 'img/sp3_black.png', 'Black', '37', 2),
(10, 'img/sp3_black.png', 'Black', '35', 2),
(10, 'img/sp3_pink.png', 'Pink', '35', 2),
(10, 'img/sp3_pink.png', 'Pink', '36', 2),
(10, 'img/sp3_pink.png', 'Pink', '37', 2),
(10, 'img/sp3_pink.png', 'Pink', '38', 2),
(10, 'img/sp3_pink.png', 'Pink', '39', 2),
(10, 'img/sp3_pink.png', 'Pink', '40', 2),
(10, 'img/sp3_white.png', 'White', '35', 2),
(10, 'img/sp3_white.png', 'White', '36', 2),
(10, 'img/sp3_white.png', 'White', '37', 2),
(10, 'img/sp3_white.png', 'White', '38', 2),
(10, 'img/sp3_white.png', 'White', '39', 2),
(10, 'img/sp3_white.png', 'White', '40', 2),
(10, 'img/sp5_black.png', 'Black', '35', 3),
(10, 'img/sp5_black.png', 'Black', '36', 3),
(10, 'img/sp5_black.png', 'Black', '37', 3),
(10, 'img/sp5_black.png', 'Black', '38', 3),
(10, 'img/sp5_black.png', 'Black', '39', 3),
(10, 'img/sp5_black.png', 'Black', '40', 3),
(10, 'img/sp5_brown.png', 'Brown', '35', 3),
(10, 'img/sp5_brown.png', 'Brown', '36', 3),
(10, 'img/sp5_brown.png', 'Brown', '37', 3),
(10, 'img/sp5_brown.png', 'Brown', '38', 3),
(10, 'img/sp5_brown.png', 'Brown', '39', 3),
(10, 'img/sp5_brown.png', 'Brown', '40', 3),
(10, 'img/sp6_black.png', 'Black', '38', 4),
(10, 'img/sp6_black.png', 'Black', '39', 4),
(10, 'img/sp6_black.png', 'Black', '40', 4),
(10, 'img/sp6_black.png', 'Black', '41', 4),
(10, 'img/sp6_black.png', 'Black', '42', 4),
(10, 'img/sp6_black.png', 'Black', '43', 4),
(10, 'img/sp6_gray.png', 'Gray', '38', 4),
(10, 'img/sp6_gray.png', 'Gray', '39', 4),
(10, 'img/sp6_gray.png', 'Gray', '40', 4),
(10, 'img/sp6_gray.png', 'Gray', '41', 4),
(10, 'img/sp6_gray.png', 'Gray', '42', 4),
(10, 'img/sp6_gray.png', 'Gray', '43', 4),
(10, 'img/sp8_gray.png', 'Gray', '38', 5),
(10, 'img/sp8_gray.png', 'Gray', '39', 5),
(10, 'img/sp8_gray.png', 'Gray', '40', 5),
(10, 'img/sp8_gray.png', 'Gray', '41', 5),
(10, 'img/sp8_gray.png', 'Gray', '42', 5),
(10, 'img/sp8_gray.png', 'Gray', '43', 5),
(10, 'img/sp8_black.png', 'Black', '38', 5),
(10, 'img/sp8_black.png', 'Black', '39', 5),
(10, 'img/sp8_black.png', 'Black', '40', 5),
(10, 'img/sp8_black.png', 'Black', '41', 5),
(10, 'img/sp8_black.png', 'Black', '42', 5),
(10, 'img/sp8_black.png', 'Black', '43', 5),
(10, 'img/sp9nu_black.png', 'Black', '35', 6),
(10, 'img/sp9nu_black.png', 'Black', '36', 6),
(10, 'img/sp9nu_black.png', 'Black', '37', 6),
(10, 'img/sp9nu_black.png', 'Black', '38', 6),
(10, 'img/sp9nu_black.png', 'Black', '39', 6),
(10, 'img/sp9nu_black.png', 'Black', '40', 6),
(10, 'img/sp9nu_white.png', 'White', '35', 6),
(10, 'img/sp9nu_white.png', 'White', '36', 6),
(10, 'img/sp9nu_white.png', 'White', '37', 6),
(10, 'img/sp9nu_white.png', 'White', '38', 6),
(10, 'img/sp9nu_white.png', 'White', '39', 6),
(10, 'img/sp9nu_white.png', 'White', '40', 6),
(10, 'img/sp9nu_yellow.png', 'Yellow', '35', 6),
(10, 'img/sp9nu_yellow.png', 'Yellow', '36', 6),
(10, 'img/sp9nu_yellow.png', 'Yellow', '37', 6),
(10, 'img/sp9nu_yellow.png', 'Yellow', '38', 6),
(10, 'img/sp9nu_yellow.png', 'Yellow', '39', 6),
(10, 'img/sp9nu_yellow.png', 'Yellow', '40', 6),
(10, 'img/sp10_white.png', 'White', '35', 7),
(10, 'img/sp10_white.png', 'White', '36', 7),
(10, 'img/sp10_white.png', 'White', '37', 7),
(10, 'img/sp10_white.png', 'White', '38', 7),
(10, 'img/sp10_white.png', 'White', '39', 7),
(10, 'img/sp10_white.png', 'White', '40', 7),
(10, 'img/sp10nu_black.png', 'Black', '35', 7),
(10, 'img/sp10nu_black.png', 'Black', '36', 7),
(10, 'img/sp10nu_black.png', 'Black', '37', 7),
(10, 'img/sp10nu_black.png', 'Black', '38', 7),
(10, 'img/sp10nu_black.png', 'Black', '39', 7),
(10, 'img/sp10nu_black.png', 'Black', '40', 7),
(10, 'img/sp11nu_black.png', 'Black', '35', 8),
(10, 'img/sp11nu_black.png', 'Black', '36', 8),
(10, 'img/sp11nu_black.png', 'Black', '37', 8),
(10, 'img/sp11nu_black.png', 'Black', '38', 8),
(10, 'img/sp11nu_black.png', 'Black', '39', 8),
(10, 'img/sp11nu_black.png', 'Black', '40', 8),
(10, 'img/sp11nu_brown.png', 'Brown', '35', 8),
(10, 'img/sp11nu_brown.png', 'Brown', '36', 8),
(10, 'img/sp11nu_brown.png', 'Brown', '37', 8),
(10, 'img/sp11nu_brown.png', 'Brown', '38', 8),
(10, 'img/sp11nu_brown.png', 'Brown', '39', 8),
(10, 'img/sp11nu_brown.png', 'Brown', '40', 8),
(10, 'img/sp12_brown.png', 'Brown', '38', 9),
(10, 'img/sp12_brown.png', 'Brown', '39', 9),
(10, 'img/sp12_brown.png', 'Brown', '40', 9),
(10, 'img/sp12_brown.png', 'Brown', '41', 9),
(10, 'img/sp12_brown.png', 'Brown', '42', 9),
(10, 'img/sp12_brown.png', 'Brown', '43', 9),
(10, 'img/sp12_gray.png', 'Gray', '38', 9),
(10, 'img/sp12_gray.png', 'Gray', '39', 9),
(10, 'img/sp12_gray.png', 'Gray', '40', 9),
(10, 'img/sp12_gray.png', 'Gray', '41', 9),
(10, 'img/sp12_gray.png', 'Gray', '42', 9),
(10, 'img/sp12_gray.png', 'Gray', '43', 9),
(10, 'img/sp12_green.png', 'Green', '38', 9),
(10, 'img/sp12_green.png', 'Green', '39', 9),
(10, 'img/sp12_green.png', 'Green', '40', 9),
(10, 'img/sp12_green.png', 'Green', '41', 9),
(10, 'img/sp12_green.png', 'Green', '42', 9),
(10, 'img/sp12_green.png', 'Green', '43', 9),
(10, 'img/sp13_gray.png', 'Gray', '38', 10),
(10, 'img/sp13_gray.png', 'Gray', '39', 10),
(10, 'img/sp13_gray.png', 'Gray', '40', 10),
(10, 'img/sp13_gray.png', 'Gray', '41', 10),
(10, 'img/sp13_gray.png', 'Gray', '42', 10),
(10, 'img/sp13_gray.png', 'Gray', '43', 10),
(10, 'img/sp13_black.png', 'Black', '38', 10),
(10, 'img/sp13_black.png', 'Black', '39', 10),
(10, 'img/sp13_black.png', 'Black', '40', 10),
(10, 'img/sp13_black.png', 'Black', '41', 10),
(10, 'img/sp13_black.png', 'Black', '42', 10),
(10, 'img/sp13_black.png', 'Black', '43', 10),
(10, 'img/sp14nu_black.png', 'Black', '35', 11),
(10, 'img/sp14nu_black.png', 'Black', '36', 11),
(10, 'img/sp14nu_black.png', 'Black', '37', 11),
(10, 'img/sp14nu_black.png', 'Black', '38', 11),
(10, 'img/sp14nu_black.png', 'Black', '39', 11),
(10, 'img/sp14nu_black.png', 'Black', '40', 11),
(10, 'img/sp14nu_white.png', 'White', '35', 11),
(10, 'img/sp14nu_white.png', 'White', '36', 11),
(10, 'img/sp14nu_white.png', 'White', '37', 11),
(10, 'img/sp14nu_white.png', 'White', '38', 11),
(10, 'img/sp14nu_white.png', 'White', '39', 11),
(10, 'img/sp14nu_white.png', 'White', '40', 11),
(10, 'img/sp15nu_white.png', 'White', '35', 12),
(10, 'img/sp15nu_white.png', 'White', '36', 12),
(10, 'img/sp15nu_white.png', 'White', '37', 12),
(10, 'img/sp15nu_white.png', 'White', '38', 12),
(10, 'img/sp15nu_white.png', 'White', '39', 12),
(10, 'img/sp15nu_white.png', 'White', '40', 12),
(10, 'img/sp15nu_black.png', 'Black', '35', 12),
(10, 'img/sp15nu_black.png', 'Black', '36', 12),
(10, 'img/sp15nu_black.png', 'Black', '37', 12),
(10, 'img/sp15nu_black.png', 'Black', '38', 12),
(10, 'img/sp15nu_black.png', 'Black', '39', 12),
(10, 'img/sp15nu_black.png', 'Black', '40', 12),
(10, 'img/sp16_black.png', 'Black', '35', 13),
(10, 'img/sp16_black.png', 'Black', '36', 13),
(10, 'img/sp16_black.png', 'Black', '37', 13),
(10, 'img/sp16_black.png', 'Black', '38', 13),
(10, 'img/sp16_black.png', 'Black', '39', 13),
(10, 'img/sp16_black.png', 'Black', '40', 13),
(10, 'img/sp16_white.png', 'White', '35', 13),
(10, 'img/sp16_white.png', 'White', '36', 13),
(10, 'img/sp16_white.png', 'White', '37', 13),
(10, 'img/sp16_white.png', 'White', '38', 13),
(10, 'img/sp16_white.png', 'White', '39', 13),
(10, 'img/sp16_white.png', 'White', '40', 13),
(10, 'img/sp17_black.png', 'Black', '35', 14),
(10, 'img/sp17_black.png', 'Black', '36', 14),
(10, 'img/sp17_black.png', 'Black', '37', 14),
(10, 'img/sp17_black.png', 'Black', '38', 14),
(10, 'img/sp17_black.png', 'Black', '39', 14),
(10, 'img/sp17_black.png', 'Black', '40', 14),
(10, 'img/sp17nu_be.png', 'Be', '35', 14),
(10, 'img/sp17nu_be.png', 'Be', '36', 14),
(10, 'img/sp17nu_be.png', 'Be', '37', 14),
(10, 'img/sp17nu_be.png', 'Be', '38', 14),
(10, 'img/sp17nu_be.png', 'Be', '39', 14),
(10, 'img/sp17nu_be.png', 'Be', '40', 14),
(10, 'img/sp17nu_blue.png', 'Blue', '35', 14),
(10, 'img/sp17nu_blue.png', 'Blue', '36', 14),
(10, 'img/sp17nu_blue.png', 'Blue', '37', 14),
(10, 'img/sp17nu_blue.png', 'Blue', '38', 14),
(10, 'img/sp17nu_blue.png', 'Blue', '39', 14),
(10, 'img/sp17nu_blue.png', 'Blue', '40', 14),
(10, 'img/sp22nu_pink.png', 'Pink', '35', 15),
(10, 'img/sp22nu_pink.png', 'Pink', '36', 15),
(10, 'img/sp22nu_pink.png', 'Pink', '37', 15),
(10, 'img/sp22nu_pink.png', 'Pink', '38', 15),
(10, 'img/sp22nu_pink.png', 'Pink', '39', 15),
(10, 'img/sp22nu_pink.png', 'Pink', '40', 15),
(10, 'img/sp22nu_white.png', 'White', '35', 15),
(10, 'img/sp22nu_white.png', 'White', '36', 15),
(10, 'img/sp22nu_white.png', 'White', '37', 15),
(10, 'img/sp22nu_white.png', 'White', '38', 15),
(10, 'img/sp22nu_white.png', 'White', '39', 15),
(10, 'img/sp22nu_white.png', 'White', '40', 15),
(10, 'img/sp18nu_white.png', 'White', '35', 16),
(10, 'img/sp18nu_white.png', 'White', '36', 16),
(10, 'img/sp18nu_white.png', 'White', '37', 16),
(10, 'img/sp18nu_white.png', 'White', '38', 16),
(10, 'img/sp18nu_white.png', 'White', '39', 16),
(10, 'img/sp18nu_white.png', 'White', '40', 16),
(10, 'img/sp18nu_pink.png', 'Pink', '35', 16),
(10, 'img/sp18nu_pink.png', 'Pink', '36', 16),
(10, 'img/sp18nu_pink.png', 'Pink', '37', 16),
(10, 'img/sp18nu_pink.png', 'Pink', '38', 16),
(10, 'img/sp18nu_pink.png', 'Pink', '39', 16),
(10, 'img/sp18nu_pink.png', 'Pink', '40', 16),
(10, 'img/sp18nu_black.png', 'Black', '35', 16),
(10, 'img/sp18nu_black.png', 'Black', '36', 16),
(10, 'img/sp18nu_black.png', 'Black', '37', 16),
(10, 'img/sp18nu_black.png', 'Black', '38', 16),
(10, 'img/sp18nu_black.png', 'Black', '39', 16),
(10, 'img/sp18nu_black.png', 'Black', '40', 16),
(10, 'img/sp19nu_black.png', 'Black', '35', 17),
(10, 'img/sp19nu_black.png', 'Black', '36', 17),
(10, 'img/sp19nu_black.png', 'Black', '37', 17),
(10, 'img/sp19nu_black.png', 'Black', '38', 17),
(10, 'img/sp19nu_black.png', 'Black', '39', 17),
(10, 'img/sp19nu_black.png', 'Black', '40', 17),
(10, 'img/sp19nu_white.png', 'White', '35', 17),
(10, 'img/sp19nu_white.png', 'White', '36', 17),
(10, 'img/sp19nu_white.png', 'White', '37', 17),
(10, 'img/sp19nu_white.png', 'White', '38', 17),
(10, 'img/sp19nu_white.png', 'White', '39', 17),
(10, 'img/sp19nu_white.png', 'White', '40', 17),
(10, 'img/sp20nu_white.png', 'White', '35', 18),
(10, 'img/sp20nu_white.png', 'White', '36', 18),
(10, 'img/sp20nu_white.png', 'White', '37', 18),
(10, 'img/sp20nu_white.png', 'White', '38', 18),
(10, 'img/sp20nu_white.png', 'White', '39', 18),
(10, 'img/sp20nu_white.png', 'White', '40', 18),
(10, 'img/sp20nu_black.png', 'Black', '35', 18),
(10, 'img/sp20nu_black.png', 'Black', '36', 18),
(10, 'img/sp20nu_black.png', 'Black', '37', 18),
(10, 'img/sp20nu_black.png', 'Black', '38', 18),
(10, 'img/sp20nu_black.png', 'Black', '39', 18),
(10, 'img/sp20nu_black.png', 'Black', '40', 18)
;

-- Thêm dữ liệu vào bảng Account
INSERT INTO Account (username, `password`, first_name, last_name, email, address, phone, gender, birthday, `role`, create_date) VALUES
('admin', '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Admin', 'User', 'admin@example.com', '123 Main St','0365774124', 'UNKNOWN', '1990-01-01', 'ADMIN', NOW()),
('employee1', '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Nguyễn', 'Thanh Trúc', 'thanhtruc@gmail.com', 'Phường 5, TP Trà Vinh', '0365774124','FEMALE', '2002-05-15', 'EMPLOYEE', NOW()),
('employee2', '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Nguyễn Thị', 'Mỹ Yến', 'myyen@gmail.com', 'Phường 4, TP Trà Vinh','0365774124', 'FEMALE', '2002-05-29', 'EMPLOYEE', NOW()),
('yenphuong', '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Nguyễn', 'Yến Phượng', 'yenphuong@gmail.com', 'Phường 1, TP Trà Vinh','0365774124', 'FEMALE', '2002-10-20', 'CUSTOMER', NOW()),
('huyentran', '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Nguyễn Thị', 'Huyền Trân', 'huyentran@gmail.com', 'Châu Thành, Trà Vinh', '0365774124','FEMALE', '2002-09-29', 'CUSTOMER', NOW());

-- Thêm dữ liệu vào bảng Feedback
-- INSERT INTO Feedback (customerId, productId, rating, `comment`, feedbackDate) VALUES
-- (4, 1, 'GOOD', 'Great shoes!', '2023-06-10'),
-- (4, 1, 'GOOD', 'Comfortable sandals!', '2023-05-12');

-- Thêm dữ liệu vào bảng PaymentMethod
INSERT INTO PaymentMethod (paymentName, descriptionPayment) VALUES
('Credit Card', 'Pay with your credit card'),
('PayPal', 'Secure online payments'),
('Cash on Delivery', 'Pay when you receive the product');

-- Thêm dữ liệu vào bảng Order
INSERT INTO `Order` (customerId, orderDate, totalAmount, oderStatus) VALUES
(4, NOW(), 0, 'ADDED_TO_CARD'),
(5, NOW(), 0, 'ADDED_TO_CARD');


-- Thêm dữ liệu vào bảng OrderItem
-- INSERT INTO OrderItem (orderId, product_detailId, quantity, sellPrice, subtotal) VALUES
-- (1, 1, 2, 99.99, 199.98),
-- (2, 2, 1, 19.99, 19.99);



