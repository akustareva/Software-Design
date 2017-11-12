<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>Add TODO List</title>
</head>
<body>

<h3>All TODO Lists</h3>
<table>
    <c:forEach var="list" items="${lists}">
        <tr>
            <td>${list.getId()}</td>
            <td>${list.getName()}</td>
            <td>${list.getDescription()}</td>
        </tr>
    </c:forEach>
</table>

<h3>Add new TODO List</h3>
<form:form modelAttribute="list" method="POST" action="/add-list">
    <table>
        <tr>
            <td><form:label path="name">Name:</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="description">Description:</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
    </table>

    <input type="submit" value="Add">
</form:form>

<h3>Edit TODO List</h3>
<form:form modelAttribute="list" method="GET">
    <table>
        <tr>
            <td>Select list: </td>
            <td><form:select path="id">
                <form:options items="${lists}" itemValue="id" itemLabel="name" />
                </form:select></td>
            <td><input type="submit" value="Edit" onclick="form.action='/edit-list';"></td>
            <td><input type="submit" value="Delete" onclick="form.action='/remove-list';"></td>
        </tr>
    </table>
</form:form>

</body>
</html>