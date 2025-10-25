<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>JSTL Set and Out Example</title></head>
<body>
    <c:set var="username" value="Maxim Ivanov" scope="session"/>
    <c:set var="age" value="${24}"/>

    <p>Welcome, <c:out value="${username}"/>!</p>
    <p>Your age is: <c:out value="${age}"/>.</p>

    <ul>
    <c:forEach items="${list}" var="item">
        <li>${item}</li>
    </c:forEach>
    <ul>
</body>
</html>