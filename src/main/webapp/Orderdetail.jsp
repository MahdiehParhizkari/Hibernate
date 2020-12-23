<%@ page import="com.helman.Entity.Orderdetail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orderdetail</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='OrderdetailAdd.jsp';">
<form action="OrderdetailAct" method="post">
    Order Number: <input type="number" name="ordnum">
    Product Code: <input type="text" name="procode">
    <input type="submit" value="Show Orderdetail">
    <input type="hidden" name="crud" value="read">
</form>
    <%String payam = (String) request.getAttribute("message");
    if (payam != null){%>
        <h2 align="center" style="color: #1d3782"><%=payam%></h2>
    <%}%>
<table border="1px" style="color: #00aa00">
    <tr>
        <td>OrderNumber</td>
        <td>ProductCode</td>
        <td>QuantityOrdered</td>
        <td>PriceEach</td>
        <td>OrderLineNumber</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <%
        List<Orderdetail> orderdetailList = (List<Orderdetail>) request.getAttribute("orderdetail");
        if (orderdetailList == null|| orderdetailList.isEmpty()){
            %>
    <h2 align="center" style="color: darkred">There is no data.</h2>
            <%
        }else{
        for (Orderdetail orderdetail : orderdetailList){
            if(orderdetail != null){
    %>
    <tr>
        <td><%=orderdetail.getOrderNumber()%></td>
        <td><%=orderdetail.getProductCode()%></td>
        <td><%=orderdetail.getQuantityOrdered()%></td>
        <td><%=orderdetail.getPriceEach()%></td>
        <td><%=orderdetail.getOrderLineNumber()%></td>
        <td><a href="/OrderdetailAct?onum=<%=orderdetail.getOrderNumber()%>&pcode=<%=orderdetail.getProductCode()%>&crud=delete">Delete</a></td>
        <td><a href="/OrderdetailAct?onum=<%=orderdetail.getOrderNumber()%>&pcode=<%=orderdetail.getProductCode()%>&crud=edit">Edit</a></td>
    </tr>
    <%}}}%>
</table>

</body>
</html>
