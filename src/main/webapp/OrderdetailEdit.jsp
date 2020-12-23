<%@ page import="com.helman.Entity.Orderdetail" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/21/20
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: #045ea1">Edit orderdetail:</h2>
<form action="OrderdetailAct" method="post">
    <%Orderdetail orderd = (Orderdetail) request.getAttribute("orderdetail");%>
    <input type="hidden" value="<%=orderd.getOrderNumber()%>" name="ordnum">
    <input type="hidden" value="<%=orderd.getProductCode()%>" name="procode">
    QuantityOrdered: <input type="number" value="<%=orderd.getQuantityOrdered()%>" name="quanord">
    PriceEach: <input type="number" value="<%=orderd.getPriceEach()%>" name="peach">
    OrderLineNumber: <input type="number" value="<%=orderd.getOrderLineNumber()%>" name="ordlnum">
    <input type="submit" value="Update">
    <input type="hidden" value="update" name="crud">
</form>
</body>
</html>
