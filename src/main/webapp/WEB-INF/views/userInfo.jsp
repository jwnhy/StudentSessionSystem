<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso8859-1"/>
    <title>Teacher Page</title>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
    <link href="${bootstrapCSS }" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
    <script language="javascript" src="${bootstrapJS }"></script>
</head>
<body background="<c:url value="/resources/image/homeBanner.jpg"/>">
    <div class="container">
        <div class="row">
            <div class="col">
                <table class="table-dark table table-hover">
                    <tr>
                        <td>${userType} Name</td>
                        <td>${requestScope.user.getPersonName()}</td>
                    </tr>
                    <tr>
                        <td>${userType} Introduction</td>
                        <td>${requestScope.user.getUserIntroduction()}</td>
                    </tr>
                    <tr>
                        <td>${userType} Address</td>
                        <td>${requestScope.user.getUserAddress()}</td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#" onclick="javascript :history.go(-1);" class="btn btn-primary">Back</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
