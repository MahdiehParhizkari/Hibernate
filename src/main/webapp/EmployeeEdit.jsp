<%@ page import="com.helman.Dao.Employeedao" %>
<%@ page import="com.helman.Entity.Employee" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/14/20
  Time: 3:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action="EmployeeAct" method="post">
    <%Employee employee = (Employee) request.getAttribute("empobjt");%>
    <input type="hidden" value="<%=employee.getEmployeeNumber()%>" name="empnum">
    LastName: <input value="<%=employee.getLastName()%>" name="lname"><br>
    FirstName: <input value="<%=employee.getFirstName()%>" name="fname"><br>
    Extension: <input value="<%=employee.getExtension()%>" name="exten"><br>
    Email: <input value="<%=employee.getEmail()%>" name="email"><br>
    OfficeCode: <input value="<%=employee.getOfficeCode()%>" name="offcode"><br>
    Reportto: <input value="<%=employee.getReportsTo()%>" name="repto"><br>
    JobTitle: <input value="<%=employee.getJobTitle()%>" name="jobtit"><br><br>
    <input type="hidden" name="crud" value="update">
    <input type="submit" value="Update">
</form>
</body>
</html>
