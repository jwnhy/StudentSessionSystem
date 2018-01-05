<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Sign Up</title>
    <!-- Custom Theme files -->
    <link href=
          <c:url value="/resources/css/homeStyle.css"/> rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=iso8859-1"/>

</head>
<body background="<c:url value="/resources/image/homeBanner.jpg"/>">
<div class="login">
    <div class="login-top">
        <c:if test="${requestScope.isValid == false }">
            Invalid user name or password!
            Please retry.
        </c:if>
        <form action="signup" method="POST">
            <input type="text" placeholder="User Name" id="userName" name="userName" maxlength="20">
            <input type="password" placeholder="User Password" id="userPassword" name="userPassword" maxlength="20">
            <input type="text" placeholder="Real Name" id="personName" name="personName" maxlength="20">
            <div class="forgot">
                <input id="submit" type="submit" value="Sign Up">
                <a href="#" onclick="javascript :history.go(-1);" class="btn btn-primary">Back</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>