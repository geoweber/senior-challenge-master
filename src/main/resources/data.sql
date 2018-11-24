INSERT INTO product (id, name, sku, price) VALUES (1, 'Milch', '102',102.58);
INSERT INTO product (id, name, sku, price) VALUES (2, 'Brot', '2035', 2035.2);
INSERT INTO product (id, name, sku, price) VALUES (3, 'Käse', 'S-155', 135.03);
INSERT INTO product (id, name, sku, price) VALUES (4, 'Wurst', '1488', 1485.44);
INSERT INTO product (id, name, sku, price) VALUES (5, 'Couscous', 'B001',1.05);

INSERT INTO product_eans (product_id, eans) VALUES (1, '12345678');
INSERT INTO product_eans (product_id, eans) VALUES (1, '77777777');
INSERT INTO product_eans (product_id, eans) VALUES (1, '23498128');
INSERT INTO product_eans (product_id, eans) VALUES (2, '34558821');
INSERT INTO product_eans (product_id, eans) VALUES (2, '12323410');
INSERT INTO product_eans (product_id, eans) VALUES (3, '34598146');
INSERT INTO product_eans (product_id, eans) VALUES (3, '43565922');
INSERT INTO product_eans (product_id, eans) VALUES (3, '23454045');
INSERT INTO product_eans (product_id, eans) VALUES (4, '18754629');
INSERT INTO product_eans (product_id, eans) VALUES (4, '46025548');
INSERT INTO product_eans (product_id, eans) VALUES (5, '54342316');


INSERT INTO customer (id, name, password) VALUES (1, 'user', 'welcome');
