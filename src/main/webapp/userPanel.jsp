<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title> HOME PAGE </title>
</head>
<c:out value=" Hello ${user.login} !"/> </br>
<c:out value=" Your money:  ${user.money}"/>

<button onclick="location.href='./deposit.jsp'">Depostit</button> </br>
<button onclick="location.href='./exhibitions'">Show all exhibitions</button>

<c:if test="${user.role == 'admin'}">
    <button onclick="location.href='./exhibition-add'">Add exhibition</button>
    <button onclick="location.href='./exhibition-stat'">Show statistics</button>
</c:if>
<br>

<button onclick="location.href='./logout'">Logout</button>
</body>
</html>
