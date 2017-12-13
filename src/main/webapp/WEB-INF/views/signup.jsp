<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Sign Up</title>
<!-- Custom Theme files -->
<link href=<c:url value="/resources/css/homeStyle.css" /> rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!--Google Fonts-->
<!--Google Fonts-->
</head>
<body background=<c:url value="/resources/image/homeBanner.jpg"/>>
<div class = "login">
	<div class = "login-top">
	<c:if test="${requestScope.isValid == false }">
			Invalid user name or password!
			Please retry.
	</c:if>
	<form action="signup" method="POST">
		<input type="text" value="User Name" id="userName" name="userName" onfocus="this.value = '';">
		<input type="password" value="User Password" id="userPassword" name="userPassword" onfocus="this.value = '';">
		<input type="text" value="Real Name" id="personName" name="personName" onfocus="this.value = '';">
		<div class="forgot">
	    	<input id="submit" type="submit" value="Sign Up" >

	    </div>
	</form>
	</div>
</div>
</body>
</html>