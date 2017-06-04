<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body align="center">
<h1>Authorization</h1>
<form align method="post" action="/">
    <p><input type="text" name="email" value="" placeholder="Email"></p>
    <p><input type="password" name="password" value="" placeholder="Password"></p>
    <p class="submit"><input type="submit" name="commit" value="Login"></p>
    <input type="hidden" name="command" value="login">
</form>
<a href="/"><button>Home</button></a>
</body>
</html>
