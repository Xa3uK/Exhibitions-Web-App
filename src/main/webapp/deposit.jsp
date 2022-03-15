<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title> Deposit </title>
</head>
<c:out value=" Hello ${user.login} !"/> </br>
<c:out value=" Your money:  ${user.money}"/>

<form action="/deposit-money" method="post" >
    <label>Money:
        <input type="text" name="money" ><br/>
    </label>
    <button type="submit" >Deposit</button>
</form>
<button onclick="location.href='./logout'">Logout</button>
</body>
</html>
