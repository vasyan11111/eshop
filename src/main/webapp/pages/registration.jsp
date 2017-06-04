<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="/">
    <p>First name:  <input name="firstName" required pattern="^[A-Za-zА-Яа-яЁё]+$"></p>
    <p>Last name:  <input name="lastName" required pattern="^[A-Za-zА-Яа-яЁё]+$"></p>
    <p>Password:  <input type="password" name="password" required></p>
    <p>Email:  <input name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"></p>
    <p>Phone number:  <input name="phoneNumber" pattern="+[0-9]{2}( [0-9]{3} )-[0-9]{2}-[0-9]{2}-[0-9]{3}"></p>
    <input type="submit" value="Send" />
    <input type="hidden" name="command" value="registration">
</form>
</body>
</html>