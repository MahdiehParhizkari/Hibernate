<%@ page import="com.helman.Entity.Productline" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Productline</title>
</head>
<body>
<form action="ProductlineAct" method="post">
    <input type="button" value="Home" onclick="location.href='index.jsp';">
    <input type="submit" value="Show Prodductline">
    Product Line: <input type="text" name="proline">
</form>
<table border="1px">
    <tr>
        <td>productLine</td>
        <td>textDescription</td>
        <td>htmlDescription</td>
        <td>image</td>
    </tr>
    <%
        List<Productline> productlineList = (List<Productline>) request.getAttribute("productline");
        if (productlineList == null){
            %>
        <h2 align="center" style="color: darkred">There is no data.</h2>
            <%
        }else{
        for (Productline productline : productlineList){
            if (productline != null){
    %>
    <tr>
        <td><%=productline.getProductLine()%></td>
        <td><%=productline.getTextDescription()%></td>
        <td><%=productline.getHtmlDescription()%></td>
        <td><%=productline.getImage()%></td>
    </tr>
    <%}}}%>
</table>

</body>
</html>
