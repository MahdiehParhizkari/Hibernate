<%@ page import="com.helman.Entity.Productline" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Productline</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='ProductlineAdd.jsp';">
<form action="ProductlineAct" method="post">
    Product Line: <input type="text" name="proline">
    <input type="submit" value="Show Prodductline">
    <input type="hidden" name="crud" value="read">
</form>
<%String payam = (String) request.getAttribute("message");
if (payam != null){%>
<h2 style="color: darkred" align="center"><%=payam%></h2>
<%}%>
<table border="1px">
    <tr>
        <td>ProductLine</td>
        <td>TextDescription</td>
        <td>HtmlDescription</td>
        <td>Image</td>
        <td>Delete</td>
        <td>Edit</td>
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
        <td>
        <%if(productline.getImage()!=null){%>
            <img src="data:image/jpg+jpeg+png+gif;base64,<%=Base64.getEncoder().encodeToString(productline.getImage())%>" width="200" height="200"><br>
        <%}%>
        </td>
        <td><a href="/ProductlineAct?proline=<%=productline.getProductLine()%>&crud=delete">Delete</a></td>
        <td><a href="/ProductlineAct?proline=<%=productline.getProductLine()%>&crud=edit">Edit</a></td>
    </tr>
    <%}}}%>
</table>

</body>
</html>
