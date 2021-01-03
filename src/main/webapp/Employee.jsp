<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='Employeeadd.jsp';">
<form action="EmployeeAct" method="post">
    Employee Number: <input type="number" name="empnum">
    <input type="hidden" name="crud" value="read">
    <input type="submit" value="Show Employee" >
</form>
<c:if test="${requestScope.message ne null}">
    <h2 align="center" style="color: darkred"><c:out value="${requestScope.message}"/></h2>
</c:if>
<table border="1px" style="color: darkgreen">
    <tr>
        <td>employeeNumber</td>
        <td>lastName</td>
        <td>firstName</td>
        <td>extension</td>
        <td>email</td>
        <td>officeCode</td>
        <td>reportsTo</td>
        <td>jobTitle</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:if test="${requestScope.employees eq null}">
        <h2 align="center" style="color: darkred">There is no data.</h2>
    </c:if>
    <c:if test="${requestScope.employees ne null}">
        <c:forEach var="employee" items="${requestScope.employees}">
            <tr>
                <td><a href="/EmployeeAct?managerof=<c:out value="${employee.employeeNumber}"/>&crud=mngrof"><c:out value="${employee.employeeNumber}"/></a></td>
                <td><c:out value="${employee.lastName}"/></td>
                <td><c:out value="${employee.firstName}"/></td>
                <td><c:out value="${employee.extension}"/></td>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.officeCode}"/></td>
                <td><a href="/EmployeeAct?reportto=<c:out value="${employee.reportsTo}"/>&crud=rpto"><c:out value="${employee.reportsTo}"/></a></td>
                <td><c:out value="${employee.jobTitle}"/></td>
                <td><a href="/EmployeeAct?empnumber=<c:out value="${employee.employeeNumber}"/>&crud=delete">Delete</a></td>
                <td><a href="/EmployeeAct?empnumber=<c:out value="${employee.employeeNumber}"/>&crud=edit">Edit</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
