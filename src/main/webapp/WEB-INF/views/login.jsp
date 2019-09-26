<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 25.09.2019
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div>
        <c:if test="${Login != null}">
            <p>${Login}</p>
        </c:if>
        <c:if test="${Roles != null}">
            <p>${Roles}</p>
        </c:if>
        <form action="/" method="POST">
            <label>Name:
                <input type="text" name="name"><br />
            </label>
            <label>Password:
                <input type="password" name="password"><br />
            </label>
            <input type="submit" value="Sign in">
        </form>
    </div>
    <div>
        <button onclick="location.href='/registration'">Регистрация</button>
    </div>
</body>
</html>
