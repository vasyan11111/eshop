<jsp:useBean id="user" scope="session" type="com.eshop.dao.entities.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<c:if test = "${bucket != null}">
    <c:forEach var="order" items="${bucket}">
        <c:out value="${order}"/> <br/>
    </c:forEach>
    </c:if>
</body>
</html>
