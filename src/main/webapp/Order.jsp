<%@ page import="com.helman.Entity.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/10/20
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <th>
    <td>orderNumber</td>
    <td>orderDate</td>
    <td>requiredDate</td>
    <tdshippedDate></tdshippedDate>
    <td>status</td>
    <td>comments</td>
    <td>customerNumber</td>
    </th>
    <%
        List<Order> orderList = (List<Order>) request.getAttribute("Order");
        for (Order order : orderList){
    %>
    <tr>
        <td><%=order.getOrderNumber()%></td>
        <td><%=order.getOrderDate()%></td>
        <td><%=order.getRequiredDate()%></td>
        <td><%=order.getShippedDate()%></td>
        <td><%=order.getStatus()%></td>
        <td><%=order.getComments()%></td>
        <td><%=order.getCustomerNumber()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
