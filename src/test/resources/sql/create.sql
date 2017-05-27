create table Users (
  id INT,
  password VARCHAR(30) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  phoneNumber VARCHAR(30),
  accountStatus BOOLEAN NOT NULL,
  PRIMARY KEY (id),
);