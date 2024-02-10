CREATE TABLE IF NOT EXISTS product (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(10) NOT NULL,
                                       price FLOAT NOT NULL,
                                       CONSTRAINT name_unique UNIQUE (name)
);
