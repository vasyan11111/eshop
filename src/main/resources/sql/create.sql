create table Users (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  cash DECIMAL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  phoneNumber VARCHAR(30),
  active BOOLEAN NOT NULL,
  admin BOOLEAN NOT NULL,
  PRIMARY KEY (id),
);
create table Product (
  id INT NOT NULL AUTO_INCREMENT,
  company VARCHAR(30) NOT NULL,
  model VARCHAR(30) NOT NULL,
  series VARCHAR(30),
  price DECIMAL NOT NULL,
  stock INT NOT NULL,
  product_type VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE Orders (
  id INT NOT NULL AUTO_INCREMENT,
  userId INT NOT NULL,
  totalPrice DECIMAL NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userId) REFERENCES Users(id)
);
CREATE TABLE Order_Entry (
  id INT NOT NULL AUTO_INCREMENT,
  orderId INT NOT NULL,
  productId INT NOT NULL,
  price DECIMAL NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (orderId) REFERENCES Orders(id),
  FOREIGN KEY (productId) REFERENCES Product(id) ON DELETE CASCADE
);

