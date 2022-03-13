<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>

<body>

<c:if test="${not empty error}">
    <c:out value = "Login or email allready used"/>
</c:if>

<form action="/registration" method="post" >
    <label>Login:
        <input type="text" name="login" ><br/>
    </label>
    <label>Password:
        <input type="password" name="password" ><br/>
    </label>
    <label>Email:
        <input type="email" name="email" ><br/>
    </label>

    <button type="submit" >Register</button>
</form>

<div>
    <button onclick="location.href='./ '">Back to main</button>
</div>
</body>
</html>