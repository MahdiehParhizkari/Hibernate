<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.helman.Entity.Office" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/16/20
  Time: 6:31 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 style="color: darkgreen">Edit the office: </h2>
<form action="OfficeAct" method="post">
    <%Office office = (Office) request.getAttribute("offobjct");%>
    <input type="hidden" value="<c:out value="${requestScope.offobjct.officeCode}"/>" name="offcode">
    City: <input value="<c:out value="${requestScope.offobjct.city}"/>" type="text" name="city"><br>
    Phone: <input value="<c:out value="${requestScope.offobjct.phone}"/>" type="text" name="phone"><br>
    AddressLine1: <input value="<c:out value="${requestScope.offobjct.addressLine1}"/>" type="text" name="addl1"><br>
    AddressLine2: <input value="<c:out value="${requestScope.offobjct.addressLine2}"/>" type="text" name="addl2"><br>
    State: <input value="<c:out value="${requestScope.offobjct.state}"/>" type="text" name="state"><br>
    Country: <input value="<c:out value="${requestScope.offobjct.country}"/>" type="text" name="coun"><br>
    PostalCode: <input value="<c:out value="${requestScope.offobjct.postalCode}"/>" type="text" name="pcode"><br>
    Territory: <input value="<c:out value="${requestScope.offobjct.territory}"/>" type="text" name="ter"><br><br>
    <input type="hidden" value="update" name="crud">
    <input type="submit" value="Update">
    <input type="button" value="Back" onclick="location.href='Office.jsp';">

</form>
</body>
</html>
