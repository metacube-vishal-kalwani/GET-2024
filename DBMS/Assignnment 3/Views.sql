-- Create a view displaying the order information (Id, Title, Price, Shopper’s name, Email, Orderdate, Status) with latest ordered items should be displayed first for last 60 days.


CREATE VIEW Order_Information AS
SELECT o.order_id AS Id, p.product_name AS Name, o.total_amount AS Price, u.user_name AS Shopper_Name, o.order_date AS Orderdate,
i.item_status AS OrderStatus FROM CustomerOrders o
RIGHT JOIN User u ON o.user_id = u.user_id
LEFT JOIN Item i ON o.order_id = i.order_id
RIGHT JOIN Product p ON i.product_id = p.product_id
WHERE DATEDIFF(current_date(), o.order_date) <61
ORDER BY o.order_date DESC;




-- Use the above view to display the Products(Items) which are in ‘shipped’ state.
Select Name, order_status FROM Order_Information
WHERE order_status = 'Shipped';


-- Use the above view to display the top 5 most selling products.
SELECT name FROM Order_information
GROUP BY name
ORDER BY COUNT(name) DESC
LIMIT 5;