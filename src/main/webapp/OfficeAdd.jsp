<%--
  Created by IntelliJ IDEA.
  User: afshin
  Date: 12/16/20
  Time: 5:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h2 style="color: darkgreen">Add an office:</h2>
<form action="OfficeAct" method="post">
    OfficeCode: <input type="text" name="offcode"><br>
    City: <input type="text" name="city"><br>
    Phone: <input type="text" name="phone"><br>
    AddressLine1: <input type="text" name="addl1"><br>
    AddressLine2: <input type="text" name="addl2"><br>
    State: <input type="text" name="state"><br>
    Country: <input type="text" name="coun"><br>
    PostalCode: <input type="text" name="pcode"><br>
    Territory: <input type="text" name="ter"><br><br>
    <input type="submit" value="Add">
    <input type="hidden" value="create" name="crud">
</form>

</body>
</html>
