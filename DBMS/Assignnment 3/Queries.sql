
--Assignment 3.1
--Display the list of products (Id, Title, Count of Categories) which fall in more than one Category.
SELECT 
	p1.product_id AS Id, 
	p1.product_name AS Title, 
	COUNT(p2.category_id) 
FROM  product p1
LEFT JOIN ProductCategory p2 
ON  p1.product_id = p2.product_id
GROUP BY p1.product_id 
HAVING COUNT(p2.category_id)>1;


-- Display Count of products as per below price range:
SELECT 
    SUM(CASE WHEN price BETWEEN 0 AND 100 THEN 1 ELSE 0 END) AS "0-100",
    SUM(CASE WHEN price BETWEEN 101 AND 500 THEN 1 ELSE 0 END) AS "101-500",
    SUM(CASE WHEN price > 500 THEN 1 ELSE 0 END) AS "500 and above"
FROM Product;



--Display the Categories along with number of products under each category.
SELECT 
	C.category_id, 
	C.category_name, 
	COUNT(PC.product_id)
FROM Category C 
LEFT JOIN ProductCategory PC
ON C.category_id = PC.category_id
GROUP BY C.category_id;


--Assignment 3.2
--Display Shopper’s information along with number of orders he/she placed during last 30 days
SELECT 
	U.User_id, 
	U.User_name, 
	COUNT(O.order_id)
FROM  User U
LEFT JOIN CustomerOrders O
ON  U.user_id = O.user_id
AND DATEDIFF(current_date(), O.order_date) <= 30
GROUP BY U.user_id;


--Display the top 10 Shoppers who generated maximum number of revenue in last 30 days.
SELECT 
	U.user_id, 
	U.user_name, 
	Sum(O.total_amount) 
FROM User U 
LEFT JOIN CustomerOrders O 
ON  U.user_id = O.user_id
AND DATEDIFF(current_date(), O.order_date) <= 30
GROUP BY U.user_id 
Order By SUM(O.total_amount) DESC
LIMIT 10;


--Display top 20 Products which are ordered most in last 60 days along with numbers.
SELECT P.product_id , P.product_name , Count(O.order_id)
FROM Product P
LEFT JOIN Item I ON I.product_id = P.product_id
LEFT JOIN customerOrders O ON I.order_id = O.order_id AND DATEDIFF(CURRENT_DATE(), O.order_date) <= 60
GROUP BY P.product_id
ORDER BY  Count(O.order_id)  DESC
LIMIT 20;



--Display Monthly sales revenue of the StoreFront for last 6 months. It should display each month’s sale.
SELECT 
    DATE_FORMAT(o.order_date, '%Y-%m') AS month,
    SUM(o.total_amount) AS monthly_revenue
FROM 
    customerOrders o
WHERE 
    o.order_date >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)
GROUP BY 
    DATE_FORMAT(o.order_date, '%Y-%m')
ORDER BY 
    DATE_FORMAT(o.order_date, '%Y-%m');
	

--Mark the products as Inactive which are not ordered in last 90 days
UPDATE Product 
SET product_status =  'Inactive'
WHERE product_id IN
(
Select I.product_id
FROM Item I
JOIN customerOrders O
ON I.order_id = O.order_id
AND datediff(CURRENT_DATE(), O.order_date) >= 90
);


-- Given a category search keyword, display all the Products present in this category/categories. 
SELECT product_id, product_name 
FROM product
WHERE category_id = "C03"
OR  category_id IN 
(
SELECT category_id 
FROM category
WHERE Parent_category = "C03"
);


-- Display top 10 Items which were cancelled most.
SELECT item_id, COUNT(*) AS cancel_count
FROM Item
WHERE item_status = 'Cancelled'
GROUP BY item_id
ORDER BY cancel_count DESC
LIMIT 10;
