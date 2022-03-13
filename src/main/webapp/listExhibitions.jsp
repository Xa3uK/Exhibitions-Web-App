
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Exhibitions</title>
</head>
<body>
<table>
    <tr>
        <th>Theme</th>
        <th>Hall</th>
        <th>StartDate</th>
        <th>EndDate</th>
        <th>StartTime</th>
        <th>EndTime</th>
        <th>Price</th>
        <th></th>
    </tr>
    <c:forEach var="exhibition" items="${exhibitions}" >
        <tr>
            <td>${exhibition.theme}</td>
            <td>${exhibition.hall}</td>
            <td>${exhibition.startDate}</td>
            <td>${exhibition.endDate}</td>
            <td>${exhibition.startTime}</td>
            <td>${exhibition.endTime}</td>
            <td>${exhibition.price}</td>

            <td>
                <a href='<c:url value="/exhibition-del?id=${exhibition.id}" />'>Delete</a>
                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${exhibition.id}">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<button class="w3-btn w3-round-large" onclick="location.href='/autorized.html'">Back to main</button>
</body>
</html>
