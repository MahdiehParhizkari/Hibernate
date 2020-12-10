<%@ page import="com.helman.Entity.Productline" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <th>
        <td>productLine</td>
        <td>textDescription</td>
        <td>htmlDescription</td>
        <td>image</td>
    </th>
    <%
        List<Productline> productlineList = (List<Productline>) request.getAttribute("productline");
        for (Productline productline : productlineList){
    %>
    <tr>
        <td><%=productline.getProductLine()%></td>
        <td><%=productline.getTextDescription()%></td>
        <td><%=productline.getHtmlDescription()%></td>
        <td><%=productline.getImage()%></td>
    </tr>
    <%}%>
</table>

</body>
</html>
