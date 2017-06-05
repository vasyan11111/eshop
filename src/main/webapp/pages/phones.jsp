<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Phones</title>
    <style>
        .list{
            float: left;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${not empty user}">
        <div class="list">
            <c:forEach var="product" items="${phones}">
              <c:out value="${product.model}"/> <br/>
                <form method="post" action="/?command=bucket">
                    <input type="hidden" name="addToBucket" value="${product.series}">
                    <input type="submit" value="add">
                </form>
           </c:forEach>
        </div>
        <jsp:include page="user_frame.jsp"/>
    </c:when>
    <c:otherwise>
            <c:forEach var="product" items="${phones}">
                <c:out value="${product.model}"/> <br/>
            </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
