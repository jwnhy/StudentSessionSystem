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
            <form
                    action="/teacher/${userName}/editSession/${session.getSessionID()}"
                    method="post">
                <table class="table table-hover table-dark">
                    <tr>
                        <th>Original Information</th>
                        <th>New Information</th>
                    </tr>
                    <tr>
                        <td>${session.getSessionDate() }</td>
                        <td><input class="form-control" type="date"
                                   name="sessionDate" id="sessionDate"
                                   min=${requestScope.presentDate }
                                           value=${requestScope.presentDate }></td>
                    </tr>
                    <tr>
                        <td>${session.getSessionStartTime() }</td>
                        <td><input class="form-control" type="time"
                                   name="sessionStartTime" id="sessionStartTime" value="14:00"></td>
                    </tr>
                    <tr>
                        <td>${session.getSessionEndTime()}</td>
                        <td><input class="form-control" type="time"
                                   name="sessionEndTime" id="sessionEndTime" value="16:00"></td>
                    </tr>
                    <tr>
                        <td>${session.getSessionAddress()}</td>
                        <td><input class="form-control" type="text"
                                   name="sessionAddress" id="sessionAddress"></td>
                    </tr>
                    <tr>
                        <td>${session.getTimesLimit() }</td>
                        <td><input class="form-control" type="number"
                                   name="timesLimit" id="timesLimit" min="1" max="30" value="10"></td>
                    </tr>
                    <tr>
                        <td>${session.getTotalTimeLimit() }</td>
                        <td><input class="form-control" type="number"
                                   name="totalTimeLimit" id="totalTimeLimit" min="10" max="60"
                                   value="30"></td>
                    </tr>
                    <tr>
                        <td><input class="btn btn-primary" type="submit"
                                   value="Edit Session"></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="col">
            <div class="row">
                <form
                        action="/teacher/${userName}/addStudent/${session.getSessionID()}"
                        method="post">
                    >
                    <table class="table table-hover table-dark">
                        <tr>
                            <th>Name</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${requestScope.studentList }" var="student">
                            <tr>
                                <td>${student.getPersonName() }</td>
                                <td><input class="form-check" type="checkbox" id="studentNameList" name="studentNameList"
                                           value="${student.getPersonName()}"></td>

                            </tr>
                        </c:forEach>
                        <tr>
                            <td><input class="btn btn-primary" type="submit" value="Add Student"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="row">
                <table class="table table-hover table-dark">
                    <tr>
                        <th>Your Student</th>
                        <th>Violated</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${requestScope.teacherStudentList }" var="student">
                        <tr>
                            <td>${student.getPersonName() }</td>
                            <td>${student.getViolatedTimes(userName)}</td>
                            <td><a href="/teacher/${userName}/addViolatedTimes/${student.getUserName()}/${session.getSessionID()}" class="btn btn-primary">
                                Add Violated Times</a> </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
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