<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="right">
    <c:choose>
        <c:when test="${user.userType == 1}">
            Hello <a href="/?command=admin"> ${user.firstName}</a> <br/>
        </c:when>
        <c:otherwise>
            Hello <a href="/?command=account"> ${user.firstName}</a> <br/>
            Cash = ${user.cash} <br/>
            <form method="post" action="/">
                <p><input name="amount" type="number" min="1" max="20000" size="3"> Add money</p>
                <input type="submit" value="+">
                <input type="hidden" name="command" value="addMoney">
            </form>
            <br/>
        </c:otherwise>
    </c:choose>
    <a href="/?command=bucket"><img src="bucket.png" alt="bucket"></a>
    <form method="post" action="/">
        <input name="command" type="submit" value="logout">
        <input type="hidden" name = "toBucket" value="toBucket">
    </form>
</div>
