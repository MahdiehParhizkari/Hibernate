<%@ page import="com.helman.Entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/23/20
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<input type="submit" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='UserAdd.jsp';">
<form action="UserAct" method="post">
    Id : <input type="text" name="id">
    <input type="submit" value="Show User">
    <input type="hidden" name="crud" value="read">
</form>
    <%String payam = (String) request.getAttribute("message");
    if (payam != null){%>
    <h2 align="center" style="color: darkred"><%=payam%></h2>
<%}%>
<table style="color: #00aa00" border="1px">
    <tr>
        <td>Id</td>
        <td>Username</td>
        <td>Password</td>
        <td>Employeefk</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <%List<User> userList= (List<User>) request.getAttribute("Users");
    if (userList == null){%>
    <h2 align="center" style="color: darkred">There is no data.</h2>
    <%}else{
    for (User user : userList){
    if (user != null){%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getUsername()%></td>
        <td><%=user.getPassword()%></td>
        <td><%=user.getEmployeefk()%></td>
        <td><a href="/UserAct?id=<%=user.getId()%>&crud=delete">Delete</a></td>
        <td><a href="/UserAct?id=<%=user.getId()%>&crud=edit">edit</a></td>
    </tr>
    <%}}}%>
</table>
</body>
</html>
