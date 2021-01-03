<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/14/20
  Time: 2:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h2>Add new Employee:</h2>
<form action="EmployeeAct" method="post">
    EmployeeNumber: <input type="number" name="empnum"><br>
    LastName: <input type="text" name="lname"><br>
    FirstName: <input type="text" name="fname"><br>
    Extension: <input type="text" name="exten"><br>
    Email: <input type="text" name="email"><br>
    OfficeCode: <input type="text" name="offcode"><br>
    Reportto: <input type="number" name="repto"><br>
    JobTitle: <input type="text" name="jobtit"><br><br>
    <input type="hidden" value="create" name="crud">
    <input type="submit" value="Add">
    <input type="button" value="Back" onclick="location.href='Employee.jsp';">

</form>
</body>
</html>
