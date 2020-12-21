<%@ page import="com.helman.Entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.helman.General.GregorianDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='OrderAdd.jsp';">
<form action="orderAct" method="post">
    Order Number: <input type="number" name="ordnum">
    <input type="submit" value="Show Order" >
    <input type="hidden" value="read" name="crud">
</form>
    <%String payam = (String) request.getAttribute("message");
    if (payam != null){%>
        <h2 align="center" style="color: #8b0000"><%=payam%></h2>
    <%}%>
<table border="1px">
    <tr>
        <td>OrderNumber</td>
        <td>OrderDate</td>
        <td>RequiredDate</td>
        <td>ShippedDate</td>
        <td>Status</td>
        <td>Comments</td>
        <td>CustomerNumber</td>
        <td>Delete</td>
        <td>Edit</td>
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
        <td><%=GregorianDate.shamsiStr(order.getOrderDate())%></td>
        <td><%=GregorianDate.shamsiStr(order.getRequiredDate())%></td>
        <td><%=GregorianDate.shamsiStr(order.getShippedDate())%></td>
        <td><%=order.getStatus()%></td>
        <td><%=order.getComments()%></td>
        <td><%=order.getCustomerNumber()%></td>
        <td><a href="/orderAct?onum=<%=order.getOrderNumber()%>&crud=delete">Delete</a></td>
        <td><a href="/orderAct?onum=<%=order.getOrderNumber()%>&crud=edit">Edit</a></td>
    </tr>
    <%}}}%>
</table>
</body>
</html>
