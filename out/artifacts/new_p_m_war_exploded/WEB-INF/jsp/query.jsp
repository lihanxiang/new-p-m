<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/20
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/query.css">
    <title>Find Product</title>
</head>
<body>
<br><br>
<h3 align="center">Find Product</h3>
<form action="findProduct.action" method="post">
    <div id="query">
        <input type="text" name="barcode" placeholder="Barcode" /><br/>
        <input type="text" name="name" placeholder="Name"/><br/>
        <input type="text" name="units" placeholder="Units"/><br/>
        <input type="text" name="purchaseprice" placeholder="PurchasePrice"/><br/>
        <input type="text" name="saleprice" placeholder="SalePrice" /><br/>
        <input type="text" name="inventory" placeholder="Inventory"/><br/>
        <input class="button" type="submit" name="submit">
        <input class="button" type="reset" name="reset">
    </div>
</form>
</body>
</html>
