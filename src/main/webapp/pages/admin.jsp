<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="user" items="${userList}">
    <c:out value="${user}"/> <br/>
    <form method="post" action="/?command=admin">
        <input type="hidden" name="blockUser" value="${user.email}">
        <input type="submit" value="block">
    </form>
</c:forEach>
<p align="center">
    <a href="/"><button>Home</button></a>
</p>
</body>
</html>
