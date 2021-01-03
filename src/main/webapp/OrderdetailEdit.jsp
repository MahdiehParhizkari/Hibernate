<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.helman.Entity.Orderdetail" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/21/20
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: #045ea1">Edit orderdetail:</h2>
<form action="OrderdetailAct" method="post">
    <%Orderdetail orderd = (Orderdetail) request.getAttribute("orderdetail");%>
    <input type="hidden" value="<c:out value="${requestScope.orderdetail.orderNumber}"/>" name="ordnum"><br>
    <input type="hidden" value="<c:out value="${requestScope.orderdetail.productCode}"/>" name="procode"><br>
    QuantityOrdered: <input type="number" value="<c:out value="${requestScope.orderdetail.quantityOrdered}"/>" name="quanord"><br>
    PriceEach: <input type="number" value="<c:out value="${requestScope.orderdetail.priceEach}"/>" name="peach"><br>
    OrderLineNumber: <input type="number" value="<c:out value="${requestScope.orderdetail.orderLineNumber}"/>" name="ordlnum"><br><br>
    <input type="hidden" value="update" name="crud">
    <input type="submit" value="Update">
    <input type="button" value="Back" onclick="location.href='Orderdetail.jsp';">
</form>
</body>
</html>
