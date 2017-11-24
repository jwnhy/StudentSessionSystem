<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Page</title>

<link href=<c:url value="/resources/css/table.css" /> rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
	<table id="table-2">
	<tr>
		<th>UserName</th><th>PersonName</th><th>UserIdentity</th><th>ChangeIdentity</th>
	</tr>
	<c:forEach items="${requestScope.users}" var="user">
	<tr>
		<td>${user.getUserName() }</td>
		<td>${user.getPersonName()}</td>
		<td>${user.getUserIdentity()}</td> 
		<form id="IDForm" action="manager/changeIdentity/${user.getUserName() }" method="POST">
		<td>
			<select name="newUserIdentity">
				<option value="MANAGER">Manager</option>
				<option value="TEACHER">Teacher</option>
				<option value="STUDENT">Student</option>
			</select>
		</td>
			<td><input id="IDForm" value="Change Identity" type="submit"></td>
		</form>
		<td><a href="manager/deleteUser/${user.getUserName() }">Delete</a></td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>