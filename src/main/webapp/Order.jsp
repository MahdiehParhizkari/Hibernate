<%@ page import="com.helman.Entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<form action="orderAct" method="post">
    <input type="button" value="Home" onclick="location.href='index.jsp';">
    <input type="submit" value="Show Order" >
    Order Number: <input type="number" name="ordnum">
</form>
<table border="1px">
    <tr>
        <td>orderNumber</td>
        <td>orderDate</td>
        <td>requiredDate</td>
        <td>shippedDate></td>
        <td>status</td>
        <td>comments</td>
        <td>customerNumber</td>
    </tr>
    <%
        List<Order> orderList = (List<Order>) request.getAttribute("Order");
        if (orderList == null || orderList.isEmpty()){
            %>
    <h2 align="center" style="color: darkred">There is no data.</h2>
            <%
        }else {
        for (Order order : orderList){
            if (order != null){
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
    <%}}}%>
</table>
</body>
</html>
