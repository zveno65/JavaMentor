<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 25.09.2019
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <div>
        <h2>Registration</h2>
    </div>
    <c:if test="${isOk != null}">
        <p>This user already exists.</p>
    </c:if>
    <div>
        <form method="POST">
            <label>Name:
                <input type="text" name="name"><br />
            </label>
            <label>Password:
                <input type="password" name="password"><br />
            </label>
            <label>Age:
                <input type="number" name="age"><br />
            </label>
            <input type="submit" value="Sign in">
        </form>
    </div>
</body>
</html>