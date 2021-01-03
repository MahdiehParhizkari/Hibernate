<%@ page import="com.helman.Entity.Orderdetail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Orderdetail</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='OrderdetailAdd.jsp';">
<form action="OrderdetailAct" method="post">
    Order Number: <input type="number" name="ordnum">
    Product Code: <input type="text" name="procode">
    <input type="submit" value="Show Orderdetail">
    <input type="hidden" name="crud" value="read">
</form>
<c:if test="${requestScope.message ne null}">
    <h2 align="center" style="color: #1d3782"><c:out value="${requestScope.message}"/></h2>
</c:if>

<table border="1px" style="color: #00aa00">
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
        <h2 align="center" style="color: darkred">There is no data.</h2>
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