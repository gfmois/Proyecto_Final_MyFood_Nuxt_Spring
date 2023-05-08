CREATE DATABASE IF NOT EXISTS MyFood_PF;

CREATE TABLE restaurants (
    `id_restaurant` VARCHAR(20) PRIMARY KEY,
    `name` VARCHAR(255),
    `capacity` INTEGER,
    `category` VARCHAR(60),
    `lat` VARCHAR(255),
    `long` VARCHAR(255),
    `slug` VARCHAR(255)
);

CREATE TABLE `reserves` (
  `id_reserve` VARCHAR(20) PRIMARY KEY,
  `id_client` varchar(255),
  `id_restaurant` varchar(255),
  `name` VARCHAR(255),
  `date_reserve` date,
  `type` varchar(255),
  `diners` int,
  `status` varchar(255)
);

CREATE TABLE `products` (
  `id_product` varchar(20) PRIMARY KEY,
  `id_restaurant` varchar(20),
  `slug` varchar(255),
  `name` varchar(255),
  `price` varchar(255),
  `image` varchar(255)
);

CREATE TABLE `products_ordered` (
  `id_order` VARCHAR(20) PRIMARY KEY,
  `id_product` VARCHAR(255),
  `quantity` int
);

CREATE TABLE `clients` (
  `id_client` VARCHAR(20) PRIMARY KEY,
  `name` varchar(255),
  `email` varchar(255),
  `phone` varchar(255),
  `password` varchar(255),
  `avatar` varchar(255)
);

CREATE TABLE `orders` (
  `id_order` VARCHAR(20) PRIMARY KEY,
  `id_client` varchar(20),
  `date` varchar(255),
  `status` varchar(255)
);

CREATE TABLE `categories` (
  `id_category` VARCHAR(20) PRIMARY KEY,
  `slug` varchar(255),
  `name` varchar(255),
  `icon` varchar(255)
);

CREATE TABLE `product_category` (
  `id_category` VARCHAR(20),
  `id_product` varchar(255)
);

CREATE TABLE `allergens` (
  `id_allergen` VARCHAR(20) PRIMARY KEY,
  `name` varchar(255),
  `icon` varchar(255)
);

CREATE TABLE `product_allergens` (
  `id_product` varchar(20),
  `id_allergen` varchar(20)
);

CREATE TABLE `events` (
  `id_event` VARCHAR(20) PRIMARY KEY,
  `id_client` varchar(20),
  `name` varchar(255),
  `n_diners` int,
  `date` VARCHAR(255),
  `type` varchar(255),
  `status` varchar(255)
);

CREATE TABLE `event_menus` (
  `id_menu` varchar(20),
  `id_event` varchar(20),
  `n_plates` int
);

CREATE TABLE `menus` (
  `id_menu` VARCHAR(20) PRIMARY KEY,
  `name` varchar(255)
);

CREATE TABLE `custom_menu` (
  `id_custom_menu` VARCHAR(20) PRIMARY KEY,
  `id_event` varchar(20),
  `name` varchar(255)
);

CREATE TABLE `products_menu` (
  `id_product` varchar(20),
  `id_menu` varchar(20)
);

CREATE TABLE `holidays` (
  `id_holiday` VARCHAR(20) PRIMARY KEY,
  `id_restaurant` varchar(20),
  `date` date,
  `description` varchar(255)
);

CREATE TABLE `employees` (
  `id_employee` VARCHAR(20) PRIMARY KEY,
  `id_restaurant` varchar(20),
  `password` VARCHAR(255),
  `name` varchar(255),
  `email` varchar(255),
  `phone` varchar(255),
  `type` varchar(255)
);

CREATE TABLE `comments` (
  `id_comment` VARCHAR(20) PRIMARY KEY,
  `id_restaurant` varchar(20),
  `id_client` varchar(20),
  `title` varchar(60),
  `description` varchar(255),
  `stars` int
);

ALTER TABLE `products` ADD FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

ALTER TABLE `orders` ADD FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_client`);

ALTER TABLE `products_ordered` ADD FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`);

ALTER TABLE `products_ordered` ADD FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`);

ALTER TABLE `product_category` ADD FOREIGN KEY (`id_category`) REFERENCES `categories` (`id_category`);

ALTER TABLE `product_category` ADD FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`);

ALTER TABLE `product_allergens` ADD FOREIGN KEY (`id_allergen`) REFERENCES `allergens` (`id_allergen`);

ALTER TABLE `product_allergens` ADD FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`);

ALTER TABLE `events` ADD FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_client`);

ALTER TABLE `event_menus` ADD FOREIGN KEY (`id_menu`) REFERENCES `events` (`id_event`);

ALTER TABLE `event_menus` ADD FOREIGN KEY (`id_event`) REFERENCES `menus` (`id_menu`);

ALTER TABLE `products_menu` ADD FOREIGN KEY (`id_menu`) REFERENCES `menus` (`id_menu`);

ALTER TABLE `products_menu` ADD FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`);

ALTER TABLE `custom_menu` ADD FOREIGN KEY (`id_event`) REFERENCES `events` (`id_event`);

ALTER TABLE `products_menu` ADD FOREIGN KEY (`id_menu`) REFERENCES `custom_menu` (`id_custom_menu`);

ALTER TABLE `products` ADD FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

ALTER TABLE `employees` ADD FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

ALTER TABLE `comments` ADD FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

ALTER TABLE `comments` ADD FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_client`);

ALTER TABLE `reserves` ADD FOREIGN KEY (`id_client`) REFERENCES `clients` (`id_client`);

ALTER TABLE `reserves` ADD FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

ALTER TABLE `holidays` ADD FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);