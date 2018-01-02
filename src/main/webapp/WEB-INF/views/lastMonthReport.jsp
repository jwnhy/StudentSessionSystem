<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Session Page</title>

    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
    <link href="${bootstrapCSS }" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
    <script language="javascript" src="${bootstrapJS }"></script>
</head>
<body background="<c:url value="/resources/image/homeBanner.jpg"/>">
<div class="container">
    <div class="row">
        <div class="col">
            <table class="table table-hover table-dark">
                <tr>
                    <td>Session Number</td><td>${requestScope.userTimes}</td>
                </tr>
                <tr><td>Total Time Length</td><td>${requestScope.userUsedTime}</td></tr>
                <tr><td>Total Times</td><td>${requestScope.userTimes}</td></tr>
                <tr><td>Total Violated Times</td><td>${requestScope.violatedTimes}</td></tr>
            </table>
            <a href="/teacher/${userName}" class="btn btn-primary">Back</a>
        </div>
    </div>
</div>
</body>
</html>
