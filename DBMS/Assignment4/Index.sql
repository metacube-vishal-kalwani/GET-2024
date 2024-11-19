-- For getting faster the query involves top caterogry
CREATE INDEX idx_top_category_id ON Category(top_category_id);


-- For searching product by name faster
CREATE INDEX idx_product_name ON Product(product_name);


-- For filtering product by price
CREATE INDEX idx_product_price ON Product(product_price);



-- For searching particular user order
CREATE INDEX idx_user_id ON Orders(user_id);


-- For getting order by date
CREATE INDEX idx_order_date ON Orders(order_date);


-- For getting order by address id and getting address from address table
CREATE INDEX idx_address_id ON Orders(address_id);