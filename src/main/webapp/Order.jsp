<%@ page import="com.helman.Entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<body>
<form action="orderAct" method="post">
    <input type="" >
</form>
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
