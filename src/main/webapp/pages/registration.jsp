<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="/">
    <p><input name="firstName" required pattern="^[A-Za-zА-Яа-яЁё]+$"> First name</p>
    <p><input name="lastName" required pattern="^[A-Za-zА-Яа-яЁё]+$"> Last name</p>
    <p><input type="password" name="password" required> Password</p>
    <p><input name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"> Email</p>
    <p><input name="phoneNumber" pattern="+[0-9]{2}( [0-9]{3} )-[0-9]{2}-[0-9]{2}-[0-9]{3}"> Phone number</p>
    <input type="submit" value="Send" />
    <input type="hidden" name="command" value="registration">
</form>
</body>
</html>