<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thu
  Date: 11/2/2020
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete customer</title>
</head>
<body>
<form method="post">
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>Customer information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td>${requestScope["customer"].getName()}</td>
            </tr>
            <tr>
                <td>Address: </td>
                <td>${requestScope["customer"].getAddress()}</td>
            </tr>
            <tr>
                <td>Age: </td>
                <td>${requestScope["customer"].getAge()}</td>
            </tr>

            <tr>
                <td>Gender: </td>
                <td>
                <c:if test="${customer.isMale()}">
                    <c:out value="Male"></c:out>
                </c:if>
                <c:if test="${!customer.isMale()}">
                    <c:out value="Female"></c:out>
                </c:if>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete customer"></td>
                <td><a href="/customerList">Back to customer list</a></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
