<%@ page import="com.helman.Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<body>
<form action="ProductAct" method="post">
    <input type="" >
</form>
<table>
    <th>
        <td>productCode</td>
        <td>productName</td>
        <td>productLine</td>
        <td>productScale</td>
        <td>productVendor</td>
        <td>productDescription</td>
        <td>quantityInStock</td>
        <td>buyPrice</td>
        <td>MSRP</td>
    </th>
    <%
        List<Product> productList = (List<Product>) request.getAttribute("product");
        for (Product product : productList){
    %>
    <tr>
        <td><%=product.getProductCode()%></td>
        <td><%=product.getProductName()%></td>
        <td><%=product.getProductLine()%></td>
        <td><%=product.getProductScale()%></td>
        <td><%=product.getProductVendor()%></td>
        <td><%=product.getProductDescription()%></td>
        <td><%=product.getQuantityInStock()%></td>
        <td><%=product.getBuyPrice()%></td>
        <td><%=product.getMSRP()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
