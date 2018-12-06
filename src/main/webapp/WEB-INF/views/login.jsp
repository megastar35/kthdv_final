<%--
  Created by IntelliJ IDEA.
  User: fr0stf0x
  Date: 12/07/2018
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Log in</title>
    <link href="/css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div align="center">
    <%--<security:authorize access="isAuthenticated()">--%>
    <%--<c:redirect url="/home"/>--%>
    <%--</security:authorize>--%>
    <form action="/login" method="post">
        <table>
            <tr>
                <td><label for="username">Username</label></td>
                <td><input id="username" type="text" name="username"></td>
            </tr>
            <tr>
                <td><label for="password">Password</label></td>
                <td><input id="password" type="password" name="password"></td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Okay</button>
                    <button type="reset">Reset</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
