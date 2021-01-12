<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="hely" uri="http://helman.com" %>
<html>
<head>
    <title>Order</title>
    <style>
        body {
            background: #c2c2c2;
        }
        input[type=button]{
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 70px;
            margin-bottom: 10px;
            font-size: 15px;
            cursor: pointer;
        }
        input[type=number] {
            font: 15px Helvetica, Arial, sans-serif;
            background-color: #f0f4f6;
            border-style: none;
            width: 130px;
            height: 33px;
        }
        #submit{
            font: 15px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 130px;
            font-size: 15px;
            cursor: pointer;
        }
        #span{
            font-size: 15px;
        }
        h2 {
            margin: 0 auto 40px;
            color: #fff;
            font: 40px Helvetica;
        }
    </style>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='OrderAdd.jsp';">
<form action="orderAct" method="post">
    <span id="span">Order Number: <input type="number" name="ordnum"></span>
    <input type="submit" value="Show Order" id="submit">
    <input type="hidden" value="read" name="crud">
</form>
<c:if test="${requestScope.message ne null}">
    <h2>${requestScope.message}</h2>
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
        <h2>There is no data.</h2>
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
