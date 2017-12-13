<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher Page</title>
</head>
<body>
<div id="sessionInsertion">
<c:if test="${requestScope.errorInfo != null && requestScope.errorType=='insertError' }">
		${requestScope.errorInfo }
		Please retry.
</c:if>
<form action="/system/teacher/${userName}/insertSession/" method="post">
	<dl>
	<dt><label>Session Address</label></dt>
	<dt><textarea name="sessionAddress" id="sessionAddress" rows="4" cols="30" onfocus="this.value=' '">Please Input Session Address</textarea></dt> 	<dt><label>Session Date</label></dt>
 	<dt><input type="date" name="sessionDate" id="sessionDate" min=${requestScope.presentDate } value=${requestScope.presentDate }></dt>
 	<dt><label>Session Start Time</label></dt>
	<dt><input type="time" name="sessionStartTime" id="sessionStartTime" value="14:00"></dt>
	<dt><label>Session End Time</label></dt>
	<dt><input type="time" name="sessionEndTime" id="sessionEndTime" value="16:00"></dt>
	<dt><label>Session Times Limit</label></dt>
	<dt><input type="number" name="TimesLimit" id="TimesLimit" min="1" max="30" value="10">
	<dt><label>Session Total Time Limit</label></dt>
	<dt><input type="number" name="TotalTimeLimit" id="TotalTimeLimit" min="10" max="60" value="30">
	<dt><input type="submit" value="Insert Session"></dt>
	</dl>
</form>
</div>
</body>
</html>