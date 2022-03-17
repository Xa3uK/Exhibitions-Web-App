
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<html>
<head>
    <title>Tickets</title>
</head>
<body>

<c:if test="${not empty user}">
    <c:out value=" Hello ${user.login} !"/> </br>
    <c:out value=" Your money:  ${user.money}"/>
    <button onclick="location.href='./deposit.jsp'">Deposit</button> </br>
</c:if>

<table>
    <tr>
        <th>Theme</th>
        <th>Hall</th>
        <th>StartDate</th>
        <th>EndDate</th>
        <th>StartTime</th>
        <th>EndTime</th>
        <th>Price</th>
        <th>Count</th>
        <th></th>
    </tr>
    <c:forEach var="ticket" items="${tickets}" >
        <tr>
            <td>${ticket.theme}</td>
            <td>${ticket.hall}</td>
            <td>${ticket.startDate}</td>
            <td>${ticket.endDate}</td>
            <td>${ticket.startTime}</td>
            <td>${ticket.endTime}</td>
            <td>${ticket.price}</td>
            <td>${ticket.ticketsCount}</td>

        </tr>
    </c:forEach>
</table>

</br>

<button class="w3-btn w3-round-large" onclick="location.href='/userPanel.jsp'">Back</button>
</body>
</html>
