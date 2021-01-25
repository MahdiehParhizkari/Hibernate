<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Employee</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .all{
            position: absolute;
            left:0px;
            width: 100%;
            background-repeat: no-repeat;
            background-size: contain;
        }
        body {
            background: #ddf8ff;
        }
        input[type=number] {
            margin: 10px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        #home{
            position: absolute;
            right: 0;
            margin: 5px;
            cursor: pointer;
        }
        #add{
            position: absolute;
            right: 90px;
            margin: 5px;
            cursor: pointer;
        }
        .table{
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
    </style>
</head>
<div class="all">
    <body>
    <br><div class="container">
        <form action="EmployeeAct" method="post" class="form-inline">
            <div>
                Employee Number: <input type="number" name="empnum">
                <input type="hidden" name="crud" value="read">
                <input type="submit" value="Show Employee" class="btn btn-info" id="submit">
                <input type="button" value="Home" class="btn btn-info" id="home" onclick="location.href='index.jsp';">
                <input type="button" value="Add" class="btn btn-info" id="add" onclick="location.href='Employeeadd.jsp';">
            </div>
        </form>
        <form action="EmployeeAct" method="get" class="form-inline">
            <div>
                <input type="button" value="Report Page" class="btn btn-info" onclick="location.href='EmployeeReport.jsp';">
            </div>
        </form>
    </div>
    <c:if test="${requestScope.message ne null}">
        <h2>${requestScope.message}</h2>
    </c:if>
    <div class="container">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
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
            </thead>
            <c:if test="${requestScope.employees eq null}">
                <h2>There is no data.</h2>
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
    </div>
    </body>
</div>
</html>