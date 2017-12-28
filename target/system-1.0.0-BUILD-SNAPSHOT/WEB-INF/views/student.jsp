<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso8859-1"/>
    <title>Insert title here</title>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
    <link href="${bootstrapCSS }" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
    <script language="javascript" src="${bootstrapJS }"></script>
</head>
<body background=<c:url value="/resources/image/homeBanner.jpg"/>>

<div class="container">
    <div class="row">
        <div class="col-2">
            <a class="btn btn-primary" href="/changeInfo/${userName}">Change Info</a>
        </div>
        <div class="col-2">
            <a class="btn btn-primary" href="/">Log out</a>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <table class="table table-hover table-dark">
                <tr>
                    <th>Teacher</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Address</th>
                </tr>
                <c:forEach items="${requestScope.sessions}" var="session">
                    <tr>
                        <td>${session.getUser().getUserName()}</td>
                        <td>${session.getSessionDate()}</td>
                        <td>${session.getSessionStartTime()}~${session.getSessionEndTime()}</td>
                        <td>${session.getSessionAddress()}</td>
                        <td><a class="btn btn-primary"
                               href="/student/${userName}/appointSession/${session.getSessionID()}">Appoint</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <table class="table table-hover table-dark">
                <tr>
                    <th>Teacher</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Address</th>
                </tr>
                <c:forEach items="${requestScope.studentSessions}" var="session">
                    <tr>
                        <td>${session.getUser().getUserName()}</td>
                        <td>${session.getSessionDate()}</td>
                        <td>${session.getSessionStartTime()}~${session.getSessionEndTime()}</td>
                        <td>${session.getSessionAddress()}</td>
                        <td><a class="btn btn-primary"
                               href="/student/${userName}/deleteSession/${session.getSessionID()}">Cancel</a></td>
                    </tr>
                </c:forEach>
            </table>
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