

--Create a function to calculate number of orders in a month. Month and year will be input parameter to function

DELIMITER $$
CREATE FUNCTION getOrderCountInMonth(Year INT, Month INT)
returns INT
deterministic
Begin 
  Declare order_count INT; 
  SELECT COUNT(Order_id) INTO order_count 
  FROM customerOrders
  WHERE order_date  BETWEEN CONCAT(year, '-', Month,'-','-01')
  AND CONCAT(year, '-', Month,'-','-30');
  return order_count;
end;

SELECT getOrderCountInMonth(2024,11);





--Create a function to return month in a year having maximum orders. Year will be input parameter.

DELIMITER $$
create function calculateMonthlyOrders(year INT)
returns VARCHAR(20)
deterministic
begin
    DECLARE order_month VARCHAR(20);
    SELECT MONTHNAME(ORDER_DATE) INTO order_month FROM Orders WHERE order_date BETWEEN
    CONCAT(year, '-01-01')
    AND CONCAT(year, '-12-31')
    GROUP BY MONTHNAME(order_date) 
    ORDER BY COUNT(order_id) DESC LIMIT 1;  
    return order_month;
End;


SELECT calculateMonthlyOrders(2024) AS order_month;