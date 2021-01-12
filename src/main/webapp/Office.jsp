<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Office</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background: #ddf8ff;
        }
        table{
            border: 3px solid #ccc;
            box-sizing: border-box;
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
        h2 {
            margin: 0 auto 40px;
            font: 40px Helvetica;
            alignment: center;
        }
    </style>
</head>
<body>
<br><di class="container">
    <form action="OfficeAct" method="Post">
        <div>
            <span id="span">Office Code: <input type="number" name="offcode"></span>
            <input type="submit" class="btn btn-info" value="Show Office" id="submit">
            <input type="hidden" value="read"  name="crud">
            <input type="button" value="Home" id="home" class="btn btn-info" onclick="location.href='index.jsp';">
            <input type="button" value="Add" id="add" class="btn btn-info" onclick="location.href='OfficeAdd.jsp';">
        </div>
    </form>
</di>
<c:if test="${requestScope.message ne null}">
    <h2 ><c:out value="${requestScope.message}"/></h2>
</c:if>
<div class="container">
    <table>
        <tr>
            <td>OfficeCode</td>
            <td>City</td>
            <td>Phone</td>
            <td>AddressLine1</td>
            <td>AddressLine2</td>
            <td>State</td>
            <td>Country</td>
            <td>PostalCode</td>
            <td>Territory</td>
            <td>Delete</td>
            <td>Edit</td>
        </tr>
        <c:if test="${empty requestScope.Offices}">
            <h2>There is no data.</h2>
        </c:if>
        <c:if test="${not empty requestScope.Offices}">
            <c:forEach var="office" items="${requestScope.Offices}">
                <tr>
                    <td><c:out value="${office.officeCode}"/></td>
                    <td><c:out value="${office.city}"/></td>
                    <td><c:out value="${office.phone}"/></td>
                    <td><c:out value="${office.addressLine1}"/></td>
                    <td><c:out value="${office.addressLine2}"/></td>
                    <td><c:out value="${office.state}"/></td>
                    <td><c:out value="${office.country}"/></td>
                    <td><c:out value="${office.postalCode}"/></td>
                    <td><c:out value="${office.territory}"/></td>
                    <td><a href="/OfficeAct?offcode=<c:out value="${office.officeCode}"/>&crud=delete">Delete</a></td>
                    <td><a href="/OfficeAct?offcode=<c:out value="${office.officeCode}"/>&crud=edit">Edit</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>