<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>Edit TODO List</title>
</head>
<body>

<h3>${list.getName()}: ${list.getDescription()}</h3>

<table>
    <c:forEach var="business" items="${businesses}">
        <tr>
            <td>${business.getDescription()}</td>
        </tr>
    </c:forEach>
</table>

<h3>Add new business to List</h3>
<form:form modelAttribute="business" method="POST" action="/add-business">
    <table>
        <tr>
            <td><form:hidden path="listId" value="${list.getId()}"/></td>
        </tr>
        <tr>
            <td><form:label path="description">Description:</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
    </table>

    <input type="submit" value="Add">
</form:form>

<br>
<br>
<form:form method="GET" action="/get-lists">
    <input type="submit" value="Back to TODO Lists">
</form:form>

</body>
</html>