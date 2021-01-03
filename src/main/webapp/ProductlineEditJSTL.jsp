<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 1/2/21
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: #045ea1">Edit productline:</h2>
<form action="ProductlineAct" method="post" enctype="multipart/form-data">
    <input type="hidden" value="${requestScope.prolobj.productLine}" name="pline"><br>
    TextDescription: <input value="${requestScope.prolobj.textDescription}" type="text"  name="tdesc"><br>
    HtmlDescription: <input value="${requestScope.prolobj.htmlDescription}" type="text" name="hdesc"><br>
    <c:if test="${not empty productline.image}">
        Image: <img src="data:image/jpg+jpeg+png+gif;base64,${productline.photo}" width="200" height="200"><br>
    </c:if>
    Change Image: <input type="file" name="img" size="50"><br><br>
    <input type="submit" value="Update">
    <input type="hidden" name="crud" value="update">
</form>
</body>
</html>
