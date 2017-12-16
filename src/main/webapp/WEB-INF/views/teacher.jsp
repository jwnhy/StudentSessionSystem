<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher Page</title>
<link href=<c:url value="/resources/css/table.css" /> rel="stylesheet" type="text/css"/>
<link href=<c:url value="/resources/css/teacherStyle.css" /> rel="stylesheet" type="text/css"/>

</head>
<body>

<div align="center">
	<table id="table-7" align="center">
	<tr>
		<th>Session Date</th><th>Start Time</th><th>End Time</th><th>Session Address</th><th>Times Limit</th><th>Total Time Limit</th>
	</tr>
	<c:forEach items="${requestScope.sessions}" var="session">
	<tr>
		<td>${session.getSessionDate() }</td>
		<td>${session.getSessionStartTime() }</td>
		<td>${session.getSessionEndTime()}</td>
		<td>${session.getSessionAddress()}</td> 
		<td>${session.getTimesLimit() }</td>
		<td>${session.getTotalTimeLimit() }</td>
	</tr>	
	</c:forEach>
	<form action="/system/teacher/${userName}/insertSession/" method="post">
	<tr>
		<td><input type="date" name="sessionDate" id="sessionDate" min=${requestScope.presentDate } value=${requestScope.presentDate }></td>
		<td><input type="time" name="sessionStartTime" id="sessionStartTime" value="14:00"></td>
		<td><input type="time" name="sessionEndTime" id="sessionEndTime" value="16:00"></td>
		<td><input type="text" name="sessionAddress" id="sessionAddress"></td>
		<td><input type="number" name="TimesLimit" id="TimesLimit" min="1" max="30" value="10"></td>
		<td><input type="number" name="TotalTimeLimit" id="TotalTimeLimit" min="10" max="60" value="30"></td>
		<td><input type="submit" value="Insert Session"></td>
	</tr>
	</form>
	</table>
	<c:if test="${requestScope.errorInfo != null && requestScope.errorType=='insertError' }">
			${requestScope.errorInfo }
			Please retry.
			</c:if>
</div>
</body>
</html>