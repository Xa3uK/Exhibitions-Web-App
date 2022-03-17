<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<head>
    <title> HOME PAGE </title>
</head>

<c:if test="${not empty user}">
    <c:out value=" Hello ${user.login} !"/> </br>
    <c:out value=" Your money:  ${user.money}"/>
    <button onclick="location.href='./deposit.jsp'">Deposit</button> </br>
</c:if>

<button onclick="location.href='./exhibitions'">Show all exhibitions</button>

<c:if test="${user.role == 'user'}">
    <button onclick="location.href='./my-tickets'">My tickets</button>
</c:if>

<c:if test="${user.role == 'admin'}">
    <button onclick="location.href='./exhibition-add'">Add exhibition</button>
    <button onclick="location.href='./exhibition-stat'">Show statistics</button>
</c:if>
<br>

<button onclick="location.href='./logout'">Logout</button>
</body>
</html>
