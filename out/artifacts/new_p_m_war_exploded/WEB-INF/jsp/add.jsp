<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/6/6
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/add.css">
    <title>Add Product</title>
</head>
<body>
    <h3 align="center">Add Product</h3>
    <form id="productForm" action="addProduct.action" method="post" >
        <div id="add">
            <input type="text" name="barcode" required="required" placeholder="Barcode" /><br/>
            <input type="text" name="name" required="required" placeholder="Name"/><br/>
            <input type="text" name="units" required="required" placeholder="Units"/><br/>
            <input type="text" name="purchaseprice" required="required" placeholder="PurchasePrice"/><br/>
            <input type="text" name="saleprice" required="required" placeholder="SalePrice" /><br/>
            <input type="text" name="inventory" required="required" placeholder="Inventory"/><br/>
            <input class="button" type="submit" name="submit">
            <input class="button" type="reset" name="reset">
        </div>
    </form>
</body>
</html>
