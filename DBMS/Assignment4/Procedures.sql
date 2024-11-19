
--Create a Stored procedure to retrieve average sales of each product in a month. Month 
--and year will be input parameter to function.

CREATE DEFINER=`root`@`localhost` PROCEDURE `averageSales`(in year INT ,in month INT)
BEGIN
SELECT p.product_id, p.product_name, AVG(CASE WHEN o.order_date
BETWEEN CONCAT(year, '-', month, '-01') AND CONCAT(year, '-', month, '-30')
THEN i.item_quantity ELSE NULL END) AS Average
FROM product p
LEFT JOIN item i ON p.product_id = i.product_id
LEFT JOIN Orders o ON i.order_id = o.order_id
GROUP BY p.product_id, p.product_name
ORDER BY p.product_id;
END 

--Create a stored procedure to retrieve table having order detail with status for a given period. 
--Start date and end date will be input parameter. Put validation on input dates like start date 
--is less than end date. If start date is greater than end date take first date of month as start date.

CREATE DEFINER=`root`@`localhost` PROCEDURE `order_detail`(in start_date date, in end_date date)
BEGIN
if start_date>end_date THEN
    SET start_date = CONCAT(YEAR(start_date), '-', MONTH(start_date), '-01');
END IF;
SELECT * FROM Orders o
WHERE o.order_date BETWEEN start_date AND end_date;
END


