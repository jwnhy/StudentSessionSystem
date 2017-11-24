<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Page</title>

<link href=<c:url value="/resources/css/table.css" /> rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
	<h1>Hello ${requestScope.personName}</h1>
	<table id="table-2">
	<tr>
		<th>UserName</th><th>PersonName</th><th>UserIdentity</th><th>ChangeIdentity</th>
	</tr>
	<c:forEach items="${requestScope.users}" var="user">
	<tr>
		<td>${user.getUserName() }</td>
		<td>${user.getPersonName()}</td>
		<td>${user.getUserIdentity()}</td> 
		<td>
		<form id="IDForm" action="manager" method="POST">
			<select name="newIdentity">
				<option value="MANAGER">Manager</option>
				<option value="TEACHER">Teacher</option>
				<option value="STUDENT">Student</option>
			</select>
		</form>
		</td>
		<td><input id="IDForm" type="submit"></td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>