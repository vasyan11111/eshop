<jsp:useBean id="user" scope="session" type="com.eshop.dao.entities.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<c:if test = "${not empty cart and not empty cart.entries}">
    <c:forEach var="entry" items="${cart.entries}">
        <c:out value="${entry}"/> <br/>
    </c:forEach>
    </c:if>

<a class="order" href="/?command=order"><button type="button">COMPLETE ORDER</button></a>
</body>
</html>
