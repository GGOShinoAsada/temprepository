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
        <li>Name <c:out value="${category.Name}"/></li>
        <li>Description <c:out value="${category.Description}"/></li>
        <li>Rating: <c:out value="${category.Rating}"/></li>
    </ul>
    <hr />

</c:forEach>

<h2>Создание новой категории</h2><br />

<form method="post" action="">

    <label><input type="text" name="name"></label>Name<br>

    <label><input type="text" name="description"></label>Description<br>
    <label><input type="number" name="rating"></label>Rating<br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>