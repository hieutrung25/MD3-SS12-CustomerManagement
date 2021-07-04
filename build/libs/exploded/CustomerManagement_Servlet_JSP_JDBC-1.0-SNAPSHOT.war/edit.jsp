<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thu
  Date: 11/1/2020
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit existed customer</title>
</head>
<body>
<form method="post">
    <fieldset>
        <legend>Customer information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["customer"].getName()}"></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><input type="text" name="address" id="address" value="${requestScope["customer"].getAddress()}"></td>
            </tr>
            <tr>
                <td>Age: </td>
                <td><input type="text" name="age" id="age" value="${requestScope["customer"].getAge()}"></td>
            </tr>

            <tr>
                <td>Gender: </td>
                <td>
                    <c:if test="${customer.isMale()}">
                        <label for="male">Male</label>
                        <input type="radio" name="gender" id="male" value="${male}" checked="checked">
                        <label for="male">Female</label>
                        <input type="radio" name="gender" id="female" value="${female}">
                    </c:if>
                    <c:if test="${!customer.isMale()}">
                        <label for="male">Male</label>
                        <input type="radio" name="gender" id="male" value="${male}">
                        <label for="male">Female</label>
                        <input type="radio" name="gender" id="female" value="${female}" checked="checked">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update customer"></td>
            </tr>
        </table>
    </fieldset>
</form>
<p>
    <a href="/customerList">Back to customer list</a>
</p>
</body>
</html>
