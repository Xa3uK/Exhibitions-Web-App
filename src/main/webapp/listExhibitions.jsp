
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Exhibitions</title>
</head>
<body>
<table>
    <c:forEach var="exhibition" items="${exhibitions}" >
        <tr><td><c:out value="${exhibition}"></c:out> </td></tr>
    </c:forEach>
</table>
</body>
</html>
