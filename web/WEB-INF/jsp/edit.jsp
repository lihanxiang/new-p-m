<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/6/4
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../css/edit.css"/>
    <title>修改商品信息</title>

</head>
<body>
    <form id="itemForm" action="${pageContext.request.contextPath }/editProductSubmit.action" method="post" >
        <input type="hidden" name="id" value="${product.id}">
        <div id="edit">
            <input type="text" name="barcode" value="${product.barcode}"/>
            <input type="text" name="name" value="${product.name}"/>
            <input type="text" name="units" value="${product.units}"/>
            <input type="text" name="purchaseprice" value="${product.purchaseprice}"/>
            <input type="text" name="saleprice" value="${product.saleprice}"/>
            <input type="text" name="inventory" value="${product.inventory}"/>
            <input class="button" type="submit" name="submit"/>
            <input class="button" type="reset" name="reset"/>
        </div>
    </form>
</body>
</html>
