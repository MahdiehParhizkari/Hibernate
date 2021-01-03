<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>ProductlineJSTL</title>
</head>
<body>
<input type="button" value="Home" onclick="location.href='index.jsp';">
<input type="button" value="Add" onclick="location.href='ProductlineAdd.jsp';">
<form action="ProductlineAct" method="post">
    Product Line: <input type="text" name="proline">
    <input type="submit" value="Show ProdductlineJSTL">
    <input type="hidden" name="crud" value="read">
</form>
<c:if test="${requestScope.message ne null}">
    <h2 style="color: darkred" align="center"><c:out value="${requestScope.message}"/></h2>
</c:if>

<table border="1px">
    <tr>
        <td>ProductLine</td>
        <td>TextDescription</td>
        <td>HtmlDescription</td>
        <td>Image</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:if test="${requestScope.productline == null}">
        <h2 align="center" style="color: darkred">There is no data.</h2>
    </c:if>
    <c:if test="${requestScope.productline != null}">
        <c:forEach  var="productline" items="${requestScope.productline}">
             <tr>
                 <td><c:out value="${productline.productLine}"/></td>
                 <td><c:out value="${productline.textDescription}"/></td>
                 <td><c:out value="${productline.htmlDescription}"/></td>
                 <td><c:if test="${productline.image ne null}">
                         <img src="data:image/jpg+jpeg+png+gif;base64,${productline.photo}>" width="200" height="200"><br>
                     </c:if>
                 </td>
                 <td><a href="/ProductlineAct?proline=${productline.productLine}&crud=delete">Delete</a></td>
                 <td><a href="/ProductlineAct?proline=${productline.productLine}&crud=edit">Edit</a></td>
             </tr>
        </c:forEach>
    </c:if>
</table>

</body>
</html>
