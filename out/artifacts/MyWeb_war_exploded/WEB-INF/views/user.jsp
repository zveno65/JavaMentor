<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
</head>

<body>
<div>
    <div>
        <div>
            <h2>User</h2>
        </div>
<%--        <c:set var="name" value="${addName}"/>--%>
        <c:if test="${addName != null}">
            <p>User ${addName} on!</p>
        </c:if>
        <c:if test="${editName != null}">
            <p>User ${editName} edited!</p>
        </c:if>
        <form method="post">
            <label>Name:
                <input type="text" name="name" value="${user.getName()}"><br />
            </label>
            <label>Password:
                <input type="password" name="password" value="${user.getPassword()}"><br />
            </label>
            <label>Age:
                <input type="number" name="age" value="${user.getAge()}"><br />
            </label>
            <button type="submit">Submit</button>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='../..'">Back to main</button>
</div>
</body>
</html>