INSERT INTO UserType (id, name)
VALUES (1, 'ADMIN'),
  (2, 'USER'),
  (3, 'GUEST');
INSERT INTO Users (id, password, firstName, lastName, email, phoneNumber, active, userType)
VALUES (1, 'admin', 'Yelisey', 'Kohanevich', 'irunnie@gmail.com', '+380992337446', TRUE, 1),
  (2, 'pwd', 'first', 'last', 'email@email.com', '12345', TRUE, 2);
INSERT INTO Mobile_Phones(company, model, series, price, amount, color)
VALUES ('Motorola', 'MOTO G4', 'XT1622', 6495, 10, 'Black'),
  ('Samsung','Galaxy J7', 'J700H/DS', 4999, 10, 'Black'),
  ('Apple','iPhone', '5s', 9799, 10, 'Black');
INSERT INTO Laptops(company, model, series, price, amount)
VALUES ('Apple', 'MacBook Air', 'MMGF2UA/A', 27099, 10),
  ('HP', '250 G5', 'Z2Z93ES', 8499, 10),
  ('Asus', 'Vivobook', 'X556UQ', 22777, 10);