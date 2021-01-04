<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.helman.Entity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='CustomerAdd.jsp';">
<form action="CustomerAct" method="post">
    Customer Number: <input type="number" name="custnum">
    <input type="submit" value="Show Customer">
    <input type="hidden" name="crud" value="read">
</form>
<c:if test="${requestScope.message ne null}">
    <h2 align="center" style="color: darkred">${requestScope.message}</h2>
</c:if>
<table border="1px" style="color: darkgreen">
    <tr>
        <td>customerNumber</td>
        <td>customerName</td>
        <td>contactLastName</td>
        <td>contactFirstName</td>
        <td>phone</td>
        <td>addressLine1</td>
        <td>addressLine2</td>
        <td>city</td>
        <td>state</td>
        <td>postalCode</td>
        <td>country</td>
        <td>salesRepEmployeeNumber</td>
        <td>creditLimit</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:if test="${requestScope.customers eq null}">
        <h2 align="center" style="color: darkred">There is no data.</h2>
    </c:if>
    <c:if test="${requestScope.customers ne null}">
        <c:forEach var="customer" items="${requestScope.customers}">
            <!--for(Customer customer :customers)-->
            <tr>
                <td>${customer.customerNumber}</td>
                <td>${customer.customerName}</td>
                <td>${customer.contactLastName}</td>
                <td>${customer.contactFirstName}</td>
                <td>${customer.phone}</td>
                <td>${customer.addressLine1}</td>
                <td>${customer.addressLine2}</td>
                <td>${customer.city}</td>
                <td>${customer.state}</td>
                <td>${customer.postalCode}</td>
                <td>${customer.country}</td>
                <td>${customer.salesRepEmployeeNumber}</td>
                <td>${customer.creditLimit}</td>
                <td><a href="/CustomerAct?custnum=${customer.customerNumber}&crud=delete">Delete</a></td>
                <td><a href="/CustomerAct?custnum=${customer.customerNumber}&crud=edit" >Edit</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
