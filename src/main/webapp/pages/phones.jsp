<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Phones</title>
</head>
<body>
    PHONES:
    <c:forEach var="phone" items="${phones}">
        <c:out value="${phone}"/><br/>
    </c:forEach>
</body>
</html>
