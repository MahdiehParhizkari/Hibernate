<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Office</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='OfficeAdd.jsp';">
<form action="OfficeAct" method="Post">
    Office Code: <input type="number" name="offcode">
    <input type="submit" value="Show Office">
    <input type="hidden" value="read" name="crud">
</form>
<c:if test="${requestScope.message ne null}">
    <h2 align="center" style="color: #8b0000"><c:out value="${requestScope.message}"/></h2>
</c:if>
<table border="1px">
    <tr>
        <td>OfficeCode</td>
        <td>City</td>
        <td>Phone</td>
        <td>AddressLine1</td>
        <td>AddressLine2</td>
        <td>State</td>
        <td>Country</td>
        <td>PostalCode</td>
        <td>Territory</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:if test="${empty requestScope.Offices}">
        <h2 align="center" style="color: #8b0000">There is no data.</h2>
    </c:if>
    <c:if test="${not empty requestScope.Offices}">
        <c:forEach var="office" items="${requestScope.Offices}">
            <tr>
                <td><c:out value="${office.officeCode}"/></td>
                <td><c:out value="${office.city}"/></td>
                <td><c:out value="${office.phone}"/></td>
                <td><c:out value="${office.addressLine1}"/></td>
                <td><c:out value="${office.addressLine2}"/></td>
                <td><c:out value="${office.state}"/></td>
                <td><c:out value="${office.country}"/></td>
                <td><c:out value="${office.postalCode}"/></td>
                <td><c:out value="${office.territory}"/></td>
                <td><a href="/OfficeAct?offcode=<c:out value="${office.officeCode}"/>&crud=delete">Delete</a></td>
                <td><a href="/OfficeAct?offcode=<c:out value="${office.officeCode}"/>&crud=edit">Edit</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>