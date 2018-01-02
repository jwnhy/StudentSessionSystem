<%@ page import="student.session.system.session.Session" %>
<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso8859-1"/>
    <title>Appoint Info</title>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
    <link href="${bootstrapCSS }" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
    <script language="javascript" src="${bootstrapJS }"></script>
</head>
<body background="<c:url value="/resources/image/homeBanner.jpg"/>">
<div class="container">
    <form action="/teacher/${userName}/appointInfo/" method="post">
    <div class="row">
        <div class="col-3">
            <input class="form-control" type="date"
                       name="infoStartDate" id="infoStartDate" value=${presentDate }>
        </div>
        <div class="col-3">
            <input class="form-control" type="date"
                   name="infoEndDate" id="infoEndDate" value=${nextMonthDate }>
        </div>
        <div class="col btn-group">
            <input type="submit" class="btn btn-primary" value="Search">
            <a href="/teacher/${userName}" class="btn btn-primary">Back</a>
        </div>
    </div>
    </form>

    <div class="row">
        <div class="col">
            <table class="table table-hover table-dark">
                <tr>
                    <th>Student Name</th>
                    <th>Session Date</th>
                    <th>Session Length</th>
                </tr>
                <c:forEach items="${sessionUserList}" var="sessionUser" varStatus="status">
                    <tr>
                        <td>${userDAO.findByUserName(sessionUser.getUserName()).getPersonName()}</td>
                        <td>${sessionList.get(status.index).getSessionDate()}</td>
                        <td>${sessionList.get(status.index).timeLength}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
