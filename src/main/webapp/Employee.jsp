<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee</title>
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
        .h2 {
            margin: 0 auto 40px;
            color: #fff;
            font: 40px Helvetica;
        }
    </style>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='Employeeadd.jsp';">
<form action="EmployeeAct" method="post">
    <span id="span">Employee Number: <input type="number" name="empnum"></span>
    <input type="hidden" name="crud" value="read">
    <input type="submit" value="Show Employee" id="submit">
</form>
<c:if test="${requestScope.message ne null}">
    <h2 class="h2">${requestScope.message}</h2>
</c:if>
<table border="1px">
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
        <h2 class="h2">There is no data.</h2>
    </c:if>
    <c:if test="${requestScope.employees ne null}">
        <c:forEach var="employee" items="${requestScope.employees}">
            <tr>
                <td><a href="/EmployeeAct?managerof=${employee.employeeNumber}&crud=mngrof">${employee.employeeNumber}</a></td>
                <td>${employee.lastName}</td>
                <td>${employee.firstName}</td>
                <td>${employee.extension}</td>
                <td>${employee.email}</td>
                <td>${employee.officeCode}</td>
                <td><a href="/EmployeeAct?reportto=${employee.reportsTo}&crud=rpto">${employee.reportsTo}</a></td>
                <td><c:out value="${employee.jobTitle}"/></td>
                <td><a href="/EmployeeAct?empnumber=${employee.employeeNumber}&crud=delete">Delete</a></td>
                <td><a href="/EmployeeAct?empnumber=${employee.employeeNumber}&crud=edit">Edit</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
