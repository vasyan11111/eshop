<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="right">
    <c:choose>
        <c:when test="${user.admin}">
            Hello <a href="/?command=admin"> ${user.firstName}</a> <br/>
        </c:when>
        <c:otherwise>
            Hello  ${user.firstName}<br/>
            Cash = ${user.cash} <br/>
            <form method="post" action="/">
                <p><input name="amount" type="number" min="1" max="20000" size="3" required> Add money</p>
                <input type="submit" value="add money">
                <input type="hidden" name="command" value="addMoney">
            </form>
            <a href="/?command=bucket">bucket</a> <br/>
            <br/>
        </c:otherwise>
    </c:choose>
    <form method="post" action="/">
        <input name="command" type="submit" value="logout">
        <input type="hidden" name = "toBucket" value="toBucket">
    </form>
</div>
