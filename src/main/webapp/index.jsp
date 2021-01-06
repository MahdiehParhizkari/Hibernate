<html>
<style>
    body {
        background: #35dc9b;
        display: flex;
        flex-direction: column;
        flex-grow: 0;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }
    h2 {
        margin: 0 auto 40px;
        color: #fff;
        font: 40px Helvetica;
        text-transform: uppercase;
        letter-spacing: 3px;
        padding-bottom: 55px;
    }
    #span{
        margin: 0 auto 40px;
        color: #fff;
        font: 25px Helvetica;
        text-transform: uppercase;
        letter-spacing: 3px;
        padding-bottom: 0px;
    }
    #selectentity{
        font: 15px Helvetica, Arial, sans-serif;
        box-sizing: border-box;
        display: block;
        border: none;
        padding: 15px;
        width: 350px;
        margin-bottom: 20px;
        font-size: 15px;
        outline: none;
        transition: all 0.2s ease-in-out;
        cursor: pointer;
        background-color: #ffffdd;
    }
    #ok{
        font: 18px Helvetica, Arial, sans-serif;
        box-sizing: border-box;
        display: block;
        border: none;
        height: 40px;
        width: 100px;
        font-size: 15px;
        cursor: pointer;
    }
</style>
<body>
<h2>Welcom to order app.</h2>
<span id="span">Select a form:</span>
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
<input id="ok" type="button" value="ok" onclick="location.href=document.getElementById('selectentity').value">
</body>
</html>