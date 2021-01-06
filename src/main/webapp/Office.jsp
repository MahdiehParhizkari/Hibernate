<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Office</title>
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
<input type="button" value="Add" onclick="location.href='OfficeAdd.jsp';">
<form action="OfficeAct" method="Post">
    <span id="span">Office Code: <input type="number" name="offcode"></span>
    <input type="submit" value="Show Office" id="submit">
    <input type="hidden" value="read" name="crud">
</form>
<c:if test="${requestScope.message ne null}">
    <h2 ><c:out value="${requestScope.message}"/></h2>
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
        <h2>There is no data.</h2>
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