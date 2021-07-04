<%--
  Created by IntelliJ IDEA.
  User: thu
  Date: 11/1/2020
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Display all customers</title>
</head>
<body>
<h1>Customer List</h1>
<table>
    <tr>
<%--        public customer(int id, String name, String address, int age, boolean isMale)--%>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Age</th>
        <th>Gender</th>
    </tr>
    <c:forEach items='${requestScope["customerList"]}' var="customer">
        <tr>
            <td>${customer.getId()}</td>
            <td>${customer.getName()}</td>
            <td>${customer.getAddress()}</td>
            <td>${customer.getAge()}</td>
            <td>
                <c:if test="${customer.isMale()}">
                    Male
                </c:if>
                <c:if test="${!customer.isMale()}">
                    Female
                </c:if>
            </td>
            <td><a href="/customerList?action=edit&id=${customer.getId()}">Edit</a></td>
            <td><a href="/customerList?action=delete&id=${customer.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="7"><a href="/customerList?action=create">Create new customer</a></td>
    </tr>
</table>
</body>
</html>
