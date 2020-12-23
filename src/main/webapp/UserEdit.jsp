<%@ page import="com.helman.Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/23/20
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: #00aa00">Edit User!</h2>
<form action="UserAct" method="post">
    <%User user = (User) request.getAttribute("us");%>
    <input type="hidden" value="<%=user.getId()%>" name="id">
    Username: <input type="text" value="<%=user.getUsername()%>" name="un"><br>
    Password: <input type="text" value="<%=user.getPassword()%>" name="pw"><br>
    Employeefk: <input type="number" value="<%=user.getEmployeefk()%>" name="empfk"><br><br>
    <input type="submit" value="Update">
    <input type="hidden" value="update" name="crud">
</form>
</body>
</html>
