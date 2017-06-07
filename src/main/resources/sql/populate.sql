INSERT INTO Users (id, password, firstName, lastName, cash, email, phoneNumber, active, admin)
VALUES (1, 'admin', 'Yelisey', 'Kohanevich', 0, 'irunnie@gmail.com', '+380992337446', TRUE, TRUE ),
  (2, 'pwd', 'first', 'last', 0, 'email@email.com', '12345', TRUE, FALSE );
INSERT INTO Product(company, model, series, price, stock, product_type)
VALUES ('Motorola', 'MOTO G4', 'XT1622', 6495, 10, 'Mobile'),
  ('Samsung','Galaxy J7', 'J700H/DS', 4999, 10, 'Mobile'),
  ('Apple','iPhone', '5s', 9799, 10, 'Mobile'),
  ('Apple', 'MacBook Air', 'MMGF2UA/A', 27099, 10, 'Laptop'),
  ('HP', '250 G5', 'Z2Z93ES', 8499, 10, 'Laptop'),
  ('Asus', 'Vivobook', 'X556UQ', 22777, 10, 'Laptop');
INSERT INTO Orders(id, userId, totalPrice)
VALUES (1, 1, 100),
  (2, 1, 215),
  (3, 2, 300);
INSERT INTO Order_Entry(id, orderId, productId, price, quantity)
VALUES (1, 1, 1, 50, 2),
  (2, 2, 1, 100, 2),
  (3, 2, 1, 15, 1),
  (4, 3, 1, 300, 1);
