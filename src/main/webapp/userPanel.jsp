<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title> HOME PAGE </title>
  </head>
  <body>

  <c:out value =" Hello!  ${name}"/> </br>
  <button onclick="location.href='./exhibitions'">Show all exhibitions</button>
  <button onclick="location.href='./logout'">Logout</button>
  </body>
</html>
