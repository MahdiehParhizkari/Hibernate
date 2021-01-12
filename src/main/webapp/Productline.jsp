<%@ page import="com.helman.Entity.Productline" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Productline</title>
    <style>
        body {
            background: #c2c2c2;
        }
        input[type=button]{
            font: 18px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 70px;
            margin-bottom: 10px;
            font-size: 15px;
            cursor: pointer;
        }
        input[type=text] {
            font: 15px Helvetica, Arial, sans-serif;
            background-color: #f0f4f6;
            border-style: none;
            width: 130px;
            height: 33px;
        }
        #submit{
            font: 15px Helvetica, Arial, sans-serif;
            box-sizing: content-box;
            border: none;
            padding: 8px;
            width: 130px;
            font-size: 15px;
            cursor: pointer;
        }
        .span{
            font-size: 15px;
        }
        h2 {
            margin: 0 auto 40px;
            color: #fff;
            font: 40px Helvetica;
        }
    </style>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='ProductlineAdd.jsp';">
<form action="ProductlineAct" method="post">
    <span class="span">Product Line: <input type="text" name="proline"></span>
    <span class="span"><input type="submit" value="Show Prodductline" id="submit"></span>
    <input type="hidden" name="crud" value="read">
</form>
<%String payam = (String) request.getAttribute("message");
    if (payam != null) {%>
<h2><%=payam%></h2>
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
        <h2>There is no data.</h2>
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
