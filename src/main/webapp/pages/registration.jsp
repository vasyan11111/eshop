<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="DispatcherServlet">
    <label>
        <input type="text" size="20"/>
    </label>
    <small>First name</small><br />
    <label>
        <input type="text" size="20"/>
    </label>
    <small>Last name</small><br />
    <label>
        <input type="text" size="20"/>
    </label>
    <small>Email</small><br />
    <label>
        <input type="password" size="20"/>
    </label>
    <small>Password</small><br />
    <label>
        <input type="text" size="20"/>
    </label>
    <small>Phone number</small><br />
    <input type="submit" value="Send" />
    <input type="hidden" name="command" value="registration">
</form>
</body>
</html>