<%@ page import="com.helman.Entity.Orderdetail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orderdetail</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<form action="OrderdetailAct" method="post">
    <input type="submit" value="Show Orderdetail">
    Order Number: <input type="number" name="ordnum">
    Product Code: <input type="text" name="procode">
</form>
<table border="1px">
    <tr>
        <td>orderNumber</td>
        <td>productCode</td>
        <td>quantityOrdered</td>
        <td>priceEach</td>
        <td>orderLineNumber</td>
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
    </tr>
    <%}}}%>
</table>

</body>
</html>
