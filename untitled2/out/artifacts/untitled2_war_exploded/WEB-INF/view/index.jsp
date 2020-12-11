<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 08.12.2020
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<h1>Hello from Java Vision!</h1><br />

<h2>Все категории</h2><br />

<c:forEach var="category" items="${requestScope.Categories}">
    <ul>
        <li>Name <c:out value="${category.name}"/></li>
        <li>Description <c:out value="${category.description}"/></li>
        <li>Rating: <c:out value="${category.rating}"/></li>
    </ul>
    <hr />

</c:forEach>

<h2>Создание новой категории</h2><br />
<form method="post" action="">
    <label><input type="text" name="Name"></label>Name<br>
    <label><input type="text" name="Description"></label>Description<br>
    <label><input type="number" name="Rating"></label>Rating<br>
    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>