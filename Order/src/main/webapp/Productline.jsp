<%@ page import="com.helman.Entity.Productline" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Productline</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .all{
            position: absolute;
            left:0px;
            width: 100%;
            background-repeat: no-repeat;
            background-size: contain;
        }
        body {
            background: #ddf8ff;
        }
        input[type=number] {
            margin: 10px;
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
        #home{
            position: absolute;
            right: 0;
            margin: 5px;
            cursor: pointer;
        }
        #add{
            position: absolute;
            right: 90px;
            margin: 5px;
            cursor: pointer;
        }
        .table{
            border: 3px solid #ccc;
            box-sizing: border-box;
        }
    </style>
</head>
<div class="all">
    <body>
    <br><div class="container">
        <form action="ProductlineAct" method="post" class="form-inline">
            <div>
                Product Line: <input type="text" name="proline">
                <input type="submit" class="btn btn-info" value="Show Prodductline" id="submit">
                <input type="hidden" name="crud" value="read">
                <input type="button" value="Home" id="home" class="btn btn-info" onclick="location.href='index.jsp';">
                <input type="button" value="Add" id="add" class="btn btn-info" onclick="location.href='ProductlineAdd.jsp';">
            </div>
        </form>
    </div>
    <%String payam = (String) request.getAttribute("message");
        if (payam != null) {%>
    <h2><%=payam%></h2>
    <%}%>
    <div class="container">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
            <tr>
                <td>ProductLine</td>
                <td>TextDescription</td>
                <td>HtmlDescription</td>
                <td>Image</td>
                <td>Delete</td>
                <td>Edit</td>
            </tr>
            </thead>
            <%List<Productline> productlineList = (List<Productline>) request.getAttribute("productline");
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
                <td><a href="ProductlineAct?proline=<%=productline.getProductLine()%>&crud=delete">Delete</a></td>
                <td><a href="ProductlineAct?proline=<%=productline.getProductLine()%>&crud=edit">Edit</a></td>
            </tr>
            <%}}}%>
        </table>
    </div>
    </body>
</div>
</html>