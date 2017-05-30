create table UserType (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);
create table Users (
  id INT NOT NULL AUTO_INCREMENT,
  password VARCHAR(30) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  phoneNumber VARCHAR(30),
  accountStatus BOOLEAN NOT NULL,
  userType INT NOT NULL,
  FOREIGN KEY (userType) REFERENCES UserType(id),
  PRIMARY KEY (id),
);
create table Mobile_Phones (
  id INT NOT NULL AUTO_INCREMENT,
  company VARCHAR(30) NOT NULL,
  model VARCHAR(30) NOT NULL,
  price INT NOT NULL,
  amount INT NOT NULL,
  color VARCHAR(15) NOT NULL,
  PRIMARY KEY (id)
);
create table Laptops (
  id INT NOT NULL AUTO_INCREMENT,
  company varchar(30) NOT NULL,
  model varchar(30) NOT NULL,
  series VARCHAR(30),
  price int NOT NULL,
  amount int NOT NULL,
  PRIMARY KEY (id)
);