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
        Hello ${user.firstName}
    </c:when>
    <c:otherwise>
        <p class="logreg">
            <a href="pages/login.jsp">Sign in</a>
            <a href="pages/registration.jsp">Sign up</a>
        </p>
    </c:otherwise>
</c:choose>


<p class="assortment">
    <a class="phones" href="pages/phones_assortment.jsp"></a>
    <button type="button">PHONES</button>
    <a class="laptops" href="pages/laptops_assortment.jsp"></a>
    <button type="button">LAPTOPS</button>
</p>
</body>
</html>
