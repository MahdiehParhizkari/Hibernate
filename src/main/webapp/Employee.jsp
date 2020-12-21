<%@ page import="com.helman.Entity.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%  String payam = (String) request.getAttribute("message");
    if(payam != null){%>
        <h2 align="center" style="color: darkred">Employee is deleted.</h2>
    <%}%>
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
    <%
    List<Employee> employeeList = (List<Employee>) request.getAttribute("employees");
    if (employeeList == null){
        %>
    <h2 align="center" style="color: darkred">There is no data.</h2>
        <%
    }else{
        for (Employee employee : employeeList){
            if(employee !=null){
    %>
    <tr>
        <td><a href="/EmployeeAct?managerof=<%=employee.getEmployeeNumber()%>&crud=mngrof"><%=employee.getEmployeeNumber()%></a></td>
        <td><%=employee.getLastName()%></td>
        <td><%=employee.getFirstName()%></td>
        <td><%=employee.getExtension()%></td>
        <td><%=employee.getEmail()%></td>
        <td><%=employee.getOfficeCode()%></td>
        <td><a href="/EmployeeAct?reportto=<%=employee.getReportsTo()%>&crud=rpto"><%=employee.getReportsTo()%></a></td>
        <td><%=employee.getJobTitle()%></td>
        <td><a href="/EmployeeAct?empnumber=<%=employee.getEmployeeNumber()%>&crud=delete">Delete</a></td>
        <td><a href="/EmployeeAct?empnumber=<%=employee.getEmployeeNumber()%>&crud=edit">Edit</a></td>
    </tr>
    <%}}}%>
</table>

</body>
</html>
