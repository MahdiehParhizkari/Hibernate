<%@ page import="com.helman.Entity.Orderdetail" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/10/20
  Time: 11:06 PM
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
    <td>productCode</td>
    <td>quantityOrdered</td>
    <td>priceEach</td>
    <td>orderLineNumber</td>
    </th>
    <%
        List<Orderdetail> orderdetailList = (List<Orderdetail>) request.getAttribute("orderdetail");
        for (Orderdetail orderdetail : orderdetailList){
    %>
    <tr>
        <td><%=orderdetail.getOrderNumber()%></td>
        <td><%=orderdetail.getProductCode()%></td>
        <td><%=orderdetail.getQuantityOrdered()%></td>
        <td><%=orderdetail.getPriceEach()%></td>
        <td><%=orderdetail.getOrderLineNumber()%></td>
    </tr>
    <%}%>
</table>

</body>
</html>
