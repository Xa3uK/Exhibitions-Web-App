
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Exhibitions</title>
</head>
<body>

<c:out value =" Hello!  ${user.login}"/> </br>

<table>
    <tr>
        <th>Theme</th>
        <th>Hall</th>
        <th>StartDate</th>
        <th>EndDate</th>
        <th>StartTime</th>
        <th>EndTime</th>
        <th>Price</th>
        <th>Sold tickets</th>
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
            <td>${exhibition.soldTickets}</td>
        </tr>
    </c:forEach>
</table>
<button class="w3-btn w3-round-large" onclick="location.href='/userPanel.jsp'">Back</button>
</body>
</html>
