<%@ page import="com.helman.Entity.Productline" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/17/20
  Time: 4:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: #045ea1">Edit productline:</h2>
<form action="ProductlineAct" method="post" enctype="multipart/form-data">
    <%Productline productline =(Productline) request.getAttribute("prolobj");%>
    <input type="hidden" value="<%=productline.getProductLine()%>" name="pline"><br>
    TextDescription: <input value="<%=productline.getTextDescription()%>" type="text"  name="tdesc"><br>
    HtmlDescription: <input value="<%=productline.getHtmlDescription()%>" type="text" name="hdesc"><br>
    Image: <img src="data:image/jpg+jpeg+png+gif;base64,<%=Base64.getEncoder().encodeToString(productline.getImage())%>" width="200" height="200"><br>
    Change Image: <input type="file" name="img" size="50"><br><br>
    <input type="submit" value="Update">
    <input type="hidden" name="crud" value="update">
</form>
</body>
</html>
