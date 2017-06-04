<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <style>
        .logreg {
            text-decoration: none;
        }

        .assortment {
            margin: 10%;
        }
    </style>
</head>
<body align="center">

<c:choose>
    <c:when test="${not empty user}">
        <jsp:include page="user_frame.jsp" />
    </c:when>
    <c:otherwise>
        <p class="logreg" align="right">
            <a href="pages/login.jsp">Sign in</a>
            <a href="pages/registration.jsp">Sign up</a>
        </p>
    </c:otherwise>
</c:choose>


<p class="assortment">
    <a class="phones" href="/?command=phones"><button type="button">PHONES</button></a>

    <a class="laptops" href="/?command=laptops"><button type="button">LAPTOPS</button></a>
</p>
</body>
</html>
