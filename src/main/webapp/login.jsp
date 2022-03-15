<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<html>
<head>
    <title>Login</title>
</head>

<body>

<c:if test="${not empty error}">
    <c:out value = "Wrong Login or password, try again"/>
</c:if>

<form action="/login" method="post" >
    <label>Login:
        <input type="text" name="login" ><br/>
    </label>
    <label>Password:
        <input type="password" name="password" ><br/>
    </label>

    <button type="submit" >Login</button>
</form>

<div>
    <button onclick="location.href='./ '">Back to main</button>
</div>
</body>
</html>