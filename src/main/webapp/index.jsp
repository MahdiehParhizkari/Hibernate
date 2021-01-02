<html>
<style>
    a.btn{
        background-color: #045ea1;
        color: white;
        padding: 9px 18px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
    }
</style>
<body>
<h2>Welcom to order app!</h2>
Select a form:
<select name="entity" id="selectentity">
    <option value="Customer.jsp">ShowCustomers</option>
    <option value="Employee.jsp">ShowEmployees</option>
    <option value="Office.jsp">ShowOffices</option>
    <option value="Order.jsp">ShowOrders</option>
    <option value="Orderdetail.jsp">ShowOrderdetails</option>
    <option value="Payment.jsp">ShowPayments</option>
    <option value="Product.jsp" selected="Product.jsp">ShowProducts</option>
    <option value="Productline.jsp">ShowProductlines</option>
    <option value="User.jsp">ShowUsers</option>
</select>
<input type="button" value="ok" onclick="location.href=document.getElementById('selectentity').value">
</body>
</html>