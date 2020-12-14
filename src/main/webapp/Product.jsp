<%@ page import="com.helman.Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<form action="ProductAct" method="post">
    <input type="button" value="Home">
    <input type="submit" value="Show Product">
    Product Code: <input type="text" name="procode">
</form>
<table border="1px">
    <tr>
        <td>productCode</td>
        <td>productName</td>
        <td>productLine</td>
        <td>productScale</td>
        <td>productVendor</td>
        <td>productDescription</td>
        <td>quantityInStock</td>
        <td>buyPrice</td>
        <td>MSRP</td>
    </tr>
    <%
        List<Product> productList = (List<Product>) request.getAttribute("product");
        if (productList == null){
            %>
        <h2 align="center" style="color: darkred">There is no data.</h2>
            <%
        }else{
        for (Product product : productList){
            if (product != null){
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
    <%}}}%>
</table>
</body>
</html>
