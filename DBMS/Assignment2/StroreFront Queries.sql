
-- data for category
INSERT INTO Category 
 VALUES
("C01", "Furniture","top_category"),
("C02", "Kitchenware", "top_category"),
("C03", "Electronics", "top_category"),
("C04", "Outdoor", "top_category"),
("C05", "Office Supplies", "top_category"),
("C06", "Living Room", "C01"),
("C07", "Bedroom", "C01"),
("C08", "Cookware", "C02"),
("C09", "Tableware", "C02"),
("C10", "Computers", "C03"),
("C11", "Mobile Devices", "C03"),
("C12", "Garden", "C04"),
("C13", "Camping", "C04"),
("C14", "Stationery", "C05"),
("C15", "Furniture", "C05")
;

INSERT INTO ProductCategory(product_id, category_id)
VALUES 
("P01", "C03"),
("P02", "C03"),
("P02", "C11"),
("P03", "C10"),
("P06", "C03"),
("P03", "C01"),
("P06", "C02"),
("P07", "C04"),
("P08", "C05"),
("P09", "C09"),
("P10", "C10");






-- data for products
INSERT INTO Product (product_id, product_name, price, quantity, category_id) VALUES
("P01", "Samsung Galaxy", 15000, 15, "C03"),
("P02", "iPhone 13", 70000, 10, "C03"),
("P03", "Dell Laptop", 55000, 8, "C10"),
("P04", "Sony Headphones", 8000, 20, "C03"),
("P05", "Wooden Chair", 3000, 25, "C01"),
("P06", "Ceramic Vase", 1200, 30, "C02"),
("P07", "Plastic Table", 2500, 12, "C04"),
("P08", "Glass Bottle", 500, 50, "C05"),
("P09", "Leather Wallet", 1500, 40, "C09"),
("P10", "Stone Sculpture", 4500, 5, "C10");


-- data for user 
INSERT INTO User (user_id, user_name, user_type, user_email, phone_number)
VALUES 
("u01", "Vishal Kalwani", "Shopper", "vishalkalwani623@gmail.com", 9001243498),
("u02", "Anita Sharma", "Shopper", "anita.sharma@example.com", 9001234567),
("u03", "Rohit Mehta", "Shopper", "rohit.mehta@example.com", 9009876543),
("u04", "Priya Singh", "Shopper", "priya.singh@example.com", 9001122334),
("u05", "Amit Patel", "Shopper", "amit.patel@example.com", 9002233445);


-- images data
INSERT INTO Images (img_id, img_url, product_id)
VALUES 
("img01", "http://example.com/images/img01.jpg", "p01"),
("img02", "http://example.com/images/img02.jpg", "p02"),
("img03", "http://example.com/images/img03.jpg", "p01"),
("img04", "http://example.com/images/img04.jpg", "p04"),
("img05", "http://example.com/images/img05.jpg", "p03"),
("img06", "http://example.com/images/img06.jpg", "p04"),
("img07", "http://example.com/images/img07.jpg", "p07"),
("img08", "http://example.com/images/img08.jpg", "p08"),
("img09", "http://example.com/images/img09.jpg", "p04"),
("img10", "http://example.com/images/img10.jpg", "p03");

-- adding items to stores
INSERT INTO Item (item_id, item_status, product_id, order_id) VALUES
('Item1', 'Shipped', 'P01', 'Order1'),
('Item2', 'Cancelled', 'P02', 'Order1'),
('Item3', 'Return', 'P03', 'Order3'),
('Item4', 'Shipped', 'P04', 'Order4'),
('Item5', 'Cancelled', 'P05', 'Order4');


-- adding order to the db
INSERT INTO CustomerOrders (order_id, user_id, order_date, total_amount) VALUES
     ('Order1', 'u01', '2024-10-01 10:00:00', 5000.00),
     ('Order2', 'u02', '2023-11-02 11:30:00', 3000.50),
     ('Order3', 'u03', '2024-11-03 14:45:00', 4500.75),
     ('Order4', 'u04', '2004-11-04 16:00:00', 2500.00);



--Display Id, Title, Category Title, Price of the products which are Active and recently added products should be at top.

SELECT P.product_id, P.product_name, C.category_name, P.price 
FROM product P, category C 
WHERE P.category_id = C.category_id AND P.quantity > 0 
ORDER BY P.date_added;


-- Display the list of products which dont have any images.
SELECT product_name FROM Product
where product_id NOT IN
(   SELECT DISTINCT(product_id) 
    FROM Images
);





-- display id , name ,parent_category name of a category
SELECT 
    c1.category_id,
    c1.category_name,
    COALESCE(c2.category_name, 'Top Category') AS parent_category
FROM 
    Category c1
LEFT JOIN 
    Category c2 ON c1.parent_category = c2.category_id
ORDER BY 
    Parent_Category,
    c1.category_name;



-- Display Product Title, Price & Description which falls into particular category Title (i.e. “Mobile”)
SELECT product_id, product_name, price 
FROM product
WHERE category_id IN (
    SELECT category_id 
    FROM category
    WHERE category_name = 'Electronics'
);


--Display the list of Products whose Quantity on hand (Inventory) is under 20.
SELECT product_id , product_name FROM Product
where  quantity  < 20;
  

-- Display Recent 50 Orders placed (Id, Order Date, Order Total).
SELECT order_id ,order_date, total_amount FROM CustomerOrders
ORDER BY order_date DESC LIMIT 50;


-- Display 10 most expensive Orders.
SELECT order_id, order_date , total_amount FROM CustomerOrders
ORDER BY total_amount DESC
LIMIT 10;


--Display all the Orders which are placed more than 10 days old and one or more items from those orders are still not shipped.
SELECT 
    O.order_id, 
    O.total_amount 
FROM 
    CustomerOrders O 
JOIN 
    Item I ON I.order_id = O.order_id
WHERE 
    I.item_status <> 'Shipped'
    AND DATEDIFF(CURRENT_DATE(), Order_date) > 10;



--Display list of shoppers which haven't ordered anything since last month.
SELECT 
    u.user_id, 
    u.user_name 
FROM 
    user u
LEFT JOIN 
    CustomerOrders o ON u.user_id = o.user_id 
    AND    DATEDIFF(CURRENT_TIMESTAMP, o.order_date) > 30




--Display list of shopper along with orders placed by them in last 15 days. 
SELECT 
    u.user_id, 
    u.user_name, 
    o.order_id, 
    o.order_date, 
    o.total_amount 
FROM 
    user u
JOIN 
    CustomerOrders o ON u.user_id = o.user_id
WHERE 
    o.order_date >= DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 15 DAY)
    AND user_type = "Shopper";


SELECT p1.product_id AS Id, p1.product_name AS Title, COUNT(p2.category_id)  FROM product p1
LEFT JOIN ProductCategory p2 ON p1.product_id = p2.product_id
GROUP BY p1.product_id HAVING COUNT(p2.category_id)>1;


    
	
    