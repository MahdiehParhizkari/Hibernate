<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/17/20
  Time: 3:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h2 style="color: #045ea1">Add new Productline:</h2>
<form action="ProductlineAct" method="post" enctype="multipart/form-data">
    ProductLine: <input type="text" name="prol"><br>
    TextDescription: <input type="text" name="tdesc"><br>
    HtmlDescription: <input type="text" name="hdesc"><br>
    Image: <input type="file" name="img" size="50"><br><br>
    <input type="hidden" name="crud" value="create">
    <input type="submit" value="Add">
    <input type="button" value="Back" onclick="location.href='Productline.jsp';">
</form>
</body>
</html>
