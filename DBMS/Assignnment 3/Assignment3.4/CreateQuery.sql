
CREATE TABLE ZipCodes (
    zip_id INT PRIMARY KEY AUTO_INCREMENT,
    zip_code VARCHAR(6) NOT NULL
    city_id INT UNIQUE KEY 
    FOREIGN KEY (city_id) REFERENCES Cities(city_id) ON DELETE CASCADE ;
);


CREATE TABLE States (
    state_id INT PRIMARY KEY AUTO_INCREMENT,
    state_name VARCHAR(30) NOT NULL
)

CREATE TABLE Cities (
city_id INT PRIMARY KEY AUTO_INCREMENT,
city_name VARCHAR(30) NOT NULL 
state_id INT 
FOREIGN KEY (state_id) REFERENCES States(state_id) ON DELETE CASCADE;
)

INSERT INTO States
VALUES 
("Rajasthan"),
("Maharasthra"), 
("Gujrat"), 
("Madhya Pradesh"), 

INSERT INTO Cities(city_name, state_id)
VALUES("Ajmer", 1),
VALUES("Jaipur", 1), 
VALUES("Kota", 1), 
VALUES("Tonk", 1), 
VALUES("Sikar", 1), 
VALUES("Navi mumbai", 2), 
VALUES("Nasik", 2), 
VALUES("Bareli", 2), 
Values("Ahemdabad", 3),
Values("Rajkot", 3),
Values("Bhopal", 4),
Values("Indore", 4);

INSERT INTO ZipCodes(zip_code, city_id)
Values
('305001' ,1),
('302001' ,2),
('324001' ,3),
('304001' ,4),
('332001' ,5),
('400001' ,6),
('422001' ,7),
('234001' ,8),
('320008' ,9),
('360001' ,10),
('462023', 11),
('451010', 12);














