
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<html>
<head>
    <title>Exhibitions</title>
</head>
<body>

<c:if test="${not empty error}">
    <c:out value = "No money - no honey"/> </br>
</c:if>

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

            <c:if test="${user.role == 'admin'}">
            <td>
                <a href='<c:url value="/exhibition-del?id=${exhibition.id}" />'>Delete</a>
                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${exhibition.id}">
                </form>
            </td>
            </c:if>

            <c:if test="${user.role == 'user'}">
                <td>
                    <a href='<c:url value="/exhibition-buy?id=${exhibition.id}&price=${exhibition.price}" />'>Buy</a>
                    <form method="post" action='<c:url value="/buy" />' style="display:inline;">
                        <input type="hidden" name="id" value="${exhibition.id}">
                    </form>
                </td>
            </c:if>

        </tr>
    </c:forEach>
</table>

<table>
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="exhibitions?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

</br>

<button class="w3-btn w3-round-large" onclick="location.href='/userPanel.jsp'">Back</button>
</body>
</html>
