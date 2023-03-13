
CREATE TABLE register (
                          `customer_id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(100) NOT NULL,
                          `email` varchar(100) NOT NULL,
                          `mobile_number` varchar(20) NOT NULL,
                          `pwd` varchar(500) NOT NULL,
                          `role` varchar(100) NOT NULL,
                          `create_dt` date DEFAULT NULL,
                           PRIMARY KEY (`customer_id`)
);
CREATE TABLE authorities (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `customer_id` int NOT NULL,
                               `name` varchar(50) NOT NULL,
                                PRIMARY KEY (`id`),
                               CONSTRAINT authorities_ibfk_1 FOREIGN KEY (`customer_id`) REFERENCES `register` (`customer_id`)
);
CREATE TABLE customer (
                               `customer_id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(50) NOT NULL,
                               `street` varchar(50) NOT NULL,
                               `house_number` varchar(50) NOT NULL,
                               `zipcode` varchar(50) NOT NULL,
                               `place` varchar(50) NOT NULL,
                               `phone_number` varchar(50) NOT NULL,
                               `email` varchar(50) NOT NULL,
                               `created_At` date DEFAULT NULL,
                               `updated_At` date DEFAULT NULL,
                                PRIMARY KEY (`customer_id`)
);

