<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>
<html>
<head>
    <title>Home</title>
    <!-- Custom Theme files -->
    <link href=
          <c:url value="/resources/css/homeStyle.css"/> rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=iso8859-1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=iso8859-1"/>
    <meta name="keywords"
          content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <!--Google Fonts-->
    <!--Google Fonts-->
</head>
<body background=<c:url value="/resources/image/homeBanner.jpg"/>>
<div class="login">
    <div class="login-top">
        <h1>LOGIN FORM</h1>
        <c:if test="${requestScope.isValid == false }">
            Invalid user name or password!
            Please retry.
        </c:if>
        <c:if test="${requestScope.isExist == false }">
            Wrong user name!
            Please retry.
        </c:if>
        <c:if test="${requestScope.isWrong == true }">
            Wrong password!
            Please retry.
        </c:if>
        <form action="login" method="post">
            <input type="text" id="userName" name="userName" placeholder="User Id">
            <input type="password" id="userPassword" name="userPassword" placeholder="Password">
            <div class="forgot">
                <input id="submit" type="submit" value="Login">
            </div>
        </form>
    </div>
    <div class="login-bottom">
        <h3>New User &nbsp;<a href="signup">Register</a> Here</h3>
    </div>
</div>


</body>
</html>
