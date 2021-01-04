<%@ page import="com.helman.Entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.helman.General.GregorianDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="hely" uri="http://helman.com" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='OrderAdd.jsp';">
<form action="orderAct" method="post">
    Order Number: <input type="number" name="ordnum">
    <input type="submit" value="Show Order" >
    <input type="hidden" value="read" name="crud">
</form>
<c:if test="${requestScope.message ne null}">
    <h2 align="center" style="color: #8b0000">${requestScope.message}</h2>
</c:if>
<table border="1px">
    <tr>
        <td>OrderNumber</td>
        <td>OrderDate</td>
        <td>RequiredDate</td>
        <td>ShippedDate</td>
        <td>Status</td>
        <td>Comments</td>
        <td>CustomerNumber</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:if test="${requestScope.Order eq null}">
        <h2 align="center" style="color: darkred">There is no data.</h2>
    </c:if>
    <c:if test="${requestScope.Order ne null}">
        <c:forEach var="order" items="${requestScope.Order}">
            <tr>
                <td>${order.orderNumber}</td>
                <td>${hely:shamsiStr(order.orderDate)}</td>
                <td>${hely:shamsiStr(order.requiredDate)}</td>
                <td>${hely:shamsiStr(order.shippedDate)}</td>
                <td>${order.status}</td>
                <td>${order.comments}</td>
                <td>${order.customerNumber}</td>
                <td><a href="/orderAct?onum=${order.orderNumber}&crud=delete">Delete</a></td>
                <td><a href="/orderAct?onum=${order.orderNumber}&crud=edit">Edit</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
