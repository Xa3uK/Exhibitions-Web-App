<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<head>
    <title> Deposit </title>
</head>

<c:if test="${not empty error}">
    <c:out value = "${error}"/>
    <br>
</c:if>

<c:out value=" Hello ${user.login} !"/> </br>
<c:out value=" Your money:  ${user.money}"/>

<form action="/deposit-money" method="post" >
    <label>Money:
        <input type="number" name="money" ><br/>
    </label>
    <button type="submit" >Deposit</button>
</form>

<button class="w3-btn w3-round-large" onclick="location.href='/userPanel.jsp'">Back</button>
<br>
<button onclick="location.href='./logout'">Logout</button>
</body>
</html>
