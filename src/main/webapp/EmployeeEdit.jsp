<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/14/20
  Time: 3:41 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: darkred" >Edit employee!</h2>
<form action="EmployeeAct" method="post">
    <input type="hidden" value="${requestScope.empobjt.employeeNumber}" name="empnum">
    LastName: <input value="${requestScope.empobjt.lastName}" type="text" name="lname"><br>
    FirstName: <input value="${requestScope.empobjt.firstName}" type="text" name="fname"><br>
    Extension: <input value="${requestScope.empobjt.extension}" type="text" name="exten"><br>
    Email: <input value="${requestScope.empobjt.email}" type="text" name="email"><br>
    OfficeCode: <input value="${requestScope.empobjt.officeCode}" type="text" name="offcode"><br>
    Reportto: <input value="${requestScope.empobjt.reportsTo}" type="number" name="repto"><br>
    JobTitle: <input value="${requestScope.empobjt.jobTitle}" type="text" name="jobtit"><br><br>
    <input type="hidden" name="crud" value="update">
    <input type="submit" value="Update">
    <input type="button" value="Back" onclick="location.href='Employee.jsp';">
</form>
</body>
</html>
