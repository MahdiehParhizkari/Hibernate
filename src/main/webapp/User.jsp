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
    <style>
        body {
            background: #35dc9b;
        }
        input[type=submit]{
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 70px;
            margin-bottom: 10px;
            font-size: 15px;
            cursor: pointer;
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
        input[type=text] {
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
        .span{
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
<input type="submit" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='UserAdd.jsp';">
<form action="UserAct" method="post">
    <span class="span">Id : <input type="text" name="id"></span>
    <input type="submit" value="Show User" id="submit">
    <input type="hidden" name="crud" value="read">
</form>
    <%String payam = (String) request.getAttribute("message");
    if (payam != null){%>
    <h2><%=payam%></h2>
<%}%>
<table border="1px">
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
    <h2>There is no data.</h2>
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
