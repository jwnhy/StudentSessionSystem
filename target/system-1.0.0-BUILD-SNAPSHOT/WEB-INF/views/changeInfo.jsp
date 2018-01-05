<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Change Info Page</title>

    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
    <link href="${bootstrapCSS }" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
    <script language="javascript" src="${bootstrapJS }"></script>
</head>
<body background="<c:url value="/resources/image/homeBanner.jpg"/>">
<div class="container">
    <div class="row">
        <div class="col">
            <form action="/changeInfo/${presentUser.getUserName()}" method="post">
                <table class="table table-hover table-dark table-bordered">
                    <tr>
                        <th>Original Info</th>
                        <th>New Info</th>
                    </tr>
                    <tr>
                        <td>${requestScope.presentUser.getPersonName()}</td>
                        <td><input maxlength="20" type="text" value="${presentUser.getPersonName()}" class="form-control"
                                   name="personName" id="personName"></td>
                    </tr>
                    <tr>
                        <td>${requestScope.presentUser.getUserPassword()}</td>
                        <td><input maxlength="20" type="password" value="${presentUser.getUserPassword()}" class="form-control"
                                   name="userPassword" id="userPassword"></td>
                    </tr>
                    <tr>
                        <td>${requestScope.presentUser.getUserIntroduction()}</td>
                        <td>
                            <textarea rows="5" cols="20" class="form-control" name="userIntroduction" id="userIntroduction">${presentUser.getUserIntroduction()}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>${requestScope.presentUser.getUserAddress()}</td>
                        <td>
                            <textarea rows="5" cols="20" class="form-control" name="userAddress" id="userAddress">${presentUser.getUserAddress()}</textarea>
                        </td>
                    </tr>
                </table>
                <div class="btn-group">
                    <input type="submit" class="btn btn-primary" value="Change Info">
                    <a href="/${userType}/${userName}" class="btn btn-primary">Back</a>
                </div>
            </form>
        </div>
    </div>
    <c:if
            test="${requestScope.errorInfo != null}">
        <div class="alert alert-danger">${requestScope.errorInfo }
            Please retry.
        </div>
    </c:if>
</div>
</body>
</html>