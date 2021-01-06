<%@ page import="com.helman.Entity.Orderdetail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Orderdetail</title>
    <style>
        body {
            background: #35dc9b;
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
        .input {
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
        .span{
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
<input type="button" value="Add" onclick="location.href='OrderdetailAdd.jsp';">
<form action="OrderdetailAct" method="post">
    <span class="span">Order Number: <input type="number" name="ordnum" class="input"></span>
    <span class="span">Product Code: <input type="text" name="procode" class="input"></span>
    <input type="submit" value="Show Orderdetail" id="submit">
    <input type="hidden" name="crud" value="read">
</form>
<c:if test="${requestScope.message ne null}">
    <h2><c:out value="${requestScope.message}"/></h2>
</c:if>

<table border="1px">
    <tr>
        <td>OrderNumber</td>
        <td>ProductCode</td>
        <td>QuantityOrdered</td>
        <td>PriceEach</td>
        <td>OrderLineNumber</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:if test="${requestScope.orderdetail eq null}">
        <h2>There is no data.</h2>
    </c:if>
    <c:if test="${requestScope.orderdetail ne null}">
        <c:forEach var="orderdetail" items="${requestScope.orderdetail}">
            <tr>
                <td><c:out value="${orderdetail.orderNumber}"/></td>
                <td><c:out value="${orderdetail.productCode}"/></td>
                <td><c:out value="${orderdetail.quantityOrdered}"/></td>
                <td><c:out value="${orderdetail.priceEach}"/></td>
                <td><c:out value="${orderdetail.orderLineNumber}"/></td>
                <td><a href="/OrderdetailAct?onum=<c:out value="${orderdetail.orderNumber}"/>%>&pcode=<c:out value="${orderdetail.productCode}"/>&crud=delete">Delete</a></td>
                <td><a href="/OrderdetailAct?onum=<c:out value="${orderdetail.orderNumber}"/>&pcode=<c:out value="${orderdetail.productCode}"/>&crud=edit">Edit</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>