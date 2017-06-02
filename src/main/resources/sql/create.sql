create table UserType (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);
create table Users (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  phoneNumber VARCHAR(30),
  active BOOLEAN NOT NULL,
  userType INT NOT NULL,
  FOREIGN KEY (userType) REFERENCES UserType(id),
  PRIMARY KEY (id),
);
create table Mobile_Phones (
  id INT NOT NULL AUTO_INCREMENT,
  company VARCHAR(30) NOT NULL,
  model VARCHAR(30) NOT NULL,
  series VARCHAR(30),
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
create table BlackList (
  id INT NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  phoneNumber VARCHAR(30),
  PRIMARY KEY (id)
);