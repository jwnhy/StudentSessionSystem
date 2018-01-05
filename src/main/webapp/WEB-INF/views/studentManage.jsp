<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Student Manage</title>

    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
    <link href="${bootstrapCSS }" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
    <script language="javascript" src="${bootstrapJS }"></script>
    <script type="application/javascript" language="JavaScript">
        function select(id) {
            var checkBox = document.getElementsByTagName("input");
            for (i = 0; i < checkBox.length; i++) {
                if (checkBox[i].type == "checkbox" && checkBox[i].id == id) {
                    checkBox[i].checked = !checkBox[i].checked;
                }
            }
        }
    </script>
</head>
<body background="<c:url value="/resources/image/homeBanner.jpg"/>">
<div class="container">
    <div class="row">
        <div class="col">
            <form
                    action="/teacher/${userName}/addStudent/${session.getSessionID()}"
                    method="post">
                <table class="table table-hover table-dark table-bordered">
                    <tr>
                        <th>Name</th>
                        <th><a href="#" class="btn btn-primary" onclick="select('studentNameList')">(Un)Select</a></th>
                    </tr>
                    <c:forEach items="${requestScope.studentList }" var="student">
                        <tr>
                            <td><a class="hyperlink" href="/showInfo/${student.getUserName()}">${student.getPersonName() }</a></td>
                            <td><input class="form-check" type="checkbox" id="studentNameList" name="studentNameList"
                                       value="${student.getPersonName()}"></td>

                        </tr>
                    </c:forEach>
                    <tr>
                        <td><input class="btn btn-primary" type="submit" value="Add Student"></td>
                        <td><a href="/teacher/${userName}" class="btn btn-primary">Back</a></td>
                    </tr>
                </table>
            </form>
        </div>
        <form action="/teacher/${userName}/deleteMultiStudent" method="post">
        <div class="col">
            <table class="table table-hover table-dark table-bordered">
                <tr>
                    <th>Your Student</th>
                    <th>Violated</th>
                    <th><a href="#" class="btn btn-primary" onclick="select('teacherStudentNameList')">(Un)Select</a>
                    </th>
                    <th><input type="submit" class="btn btn-primary" value="Delete Student"></th>
                </tr>
                <c:forEach items="${requestScope.teacherStudentList }" var="student">
                    <tr>
                        <td><a class="hyperlink" href="/showInfo/${student.getUserName()}">${student.getPersonName() }</a></td>
                        <td>${student.getViolatedTimes(userName)}</td>
                        <td>
                            <input class="form-check" type="checkbox" id="teacherStudentNameList"
                                   name="teacherStudentNameList"
                                   value="${student.getPersonName()}">
                        </td>
                        <td>
                            <a href="/teacher/${userName}/addViolatedTimes/${student.getUserName()}/${session.getSessionID()}"
                               class="btn btn-primary">Add Violated Times</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

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
</body>
</html>
