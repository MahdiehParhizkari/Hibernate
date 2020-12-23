<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/23/20
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<H2>Add new user!</H2>
<form action="UserAct" method="post">
    Id: <input type="number" name="id"><br>
    Username: <input type="text" name="un"><br>
    Password: <input type="text" name="pw"><br>
    Employeefk: <input type="number" name="empfk"><br><br>
    <input type="submit" value="Add">
    <input type="hidden" value="add" name="crud">
</form>

</body>
</html>
