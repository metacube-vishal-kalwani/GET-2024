DROP DATABASE StoreFront;
CREATE DATABASE StoreFront;
Use StoreFront;




CREATE TABLE Category
(category_id varchar(20) PRIMARY KEY,
category_name varchar(50) NOT NULL,
parent_category varchar(50) DEFAULT 'top_category'
);




CREATE TABLE Product
(product_id varchar(20) PRIMARY KEY,
product_name varchar(50),
price INT,
quantity INT,
category_id varchar(20),
FOREIGN KEY (category_id) REFERENCE Category(category_id)
);


CREATE TABLE 
ProductCategory
(Product_id varchar(20),
category_id varchar(20),
FOREIGN KEY 
	(product_id) 
REFERENCES 
	product(product_id) ON DELETE CASCADE,
FOREIGN KEY 
	(category_id) REFERENCES Category(category_id) ON DELETE CASCADE
);



CREATE TABLE 
	Images(
	img_id varchar(20) PRIMARY KEY,
	img_url VARCHAR(50) NOT NULL,
	product_id VARCHAR(20),
FOREIGN KEY 
	(product_id) REFERENCES product(product_id)
);


CREATE TABLE 
    User (
    user_id VARCHAR(20) PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    user_type ENUM('Shopper', 'Administration') NOT NULL,
    user_email VARCHAR(50),
    phone_number VARCHAR(15)
);


CREATE TABLE Address
(address_id varchar(20) PRIMARY KEY,
country varchar(20),
state varchar(20),
district varchar(30),
city varchar(30),
postal_code INT,
user_id varchar(20),
FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE CustomerOrders (
    order_id VARCHAR(20) PRIMARY KEY,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount INT, 
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Item
(item_id varchar(20) PRIMARY KEY,
item_status Enum("Cancelled","Return","Shipped"),
product_id varchar(20),
order_id varchar(20),
FOREIGN KEY (product_id) REFERENCES Product(product_id),
FOREIGN KEY (order_id) REFERENCES CustomerOrders(order_id)
);























