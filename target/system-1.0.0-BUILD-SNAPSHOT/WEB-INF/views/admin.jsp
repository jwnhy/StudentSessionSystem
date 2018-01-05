<%@ page language="java" contentType="text/html; charset=iso8859-1"
         pageEncoding="iso8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Admin Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso8859-1"/>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
    <link href="${bootstrapCSS }" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
    <script language="javascript" src="${bootstrapJS }"></script>

</head>
<body background="<c:url value="/resources/image/homeBanner.jpg"/>">
<div class="container">
    <div class="row">
        <div class="col-2">
            <a class="btn btn-primary" href="<c:url value="/"/>">Log out</a>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <table class="table table-hover table-dark table-bordered">
                <thead>
                <tr>
                    <th>User Name</th>
                    <th>Person Name</th>
                    <th>User Identity</th>
                    <th>Change Identity</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <td>${user.getUserName() }</td>
                        <td>${user.getPersonName()}</td>
                        <td>${user.getUserIdentity()}</td>
                        <form id="IDForm"
                              action="admin/changeIdentity/${user.getUserName() }"
                              method="POST">
                            <td><select class="selectpicker show-tick form-control" name="newUserIdentity">
                                <option value="ADMIN">Admin</option>
                                <option value="TEACHER">Teacher</option>
                                <option value="STUDENT">Student</option>
                            </select></td>
                            <td><input class="btn btn-primary" id="IDForm"
                                       value="Change Identity" data-toggle="tooltip" title="This May Cause Session Loss"
                                       type="submit"></td>
                        </form>
                        <td><a class="btn btn-primary"
                               href="admin/deleteUser/${user.getUserName() }">Delete</a></td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>
        <table class="table-hover table table-dark">
            <tr>
                <form class="form-inline" action="admin/changeDisplayLimit" method="POST">
                    <td>
                        <label style="color:white;">Day limit: </label> <input class="form-control" type="number"
                                                                               id="newDisplayLimit"
                                                                               name="newDisplayLimit"
                                                                               value="${displayLimit}">
                    </td>
                    <td>
                        <input
                                class="btn btn-primary"
                                type="submit" value="Change">
                    </td>
                </form>
            </tr>
            <tr>
                <form class="form-inline" action="admin/changeCancelLimit" method="POST">
                    <td>
                        <label style="color:white;">Cancel limit: </label> <input class="form-control" type="number"
                                                                                  id="newCancelLimit"
                                                                                  name="newCancelLimit"
                                                                                  value="${cancelLimit}">
                    </td>
                    <td>
                        <input
                                class="btn btn-primary"
                                type="submit" value="Change">
                    </td>
                </form>
            </tr>
            <tr>
                <form class="form-inline" action="admin/changeViolatedLimit" method="POST">
                    <td>
                        <label style="color:white;">Violated limit: </label> <input class="form-control" type="number"
                                                                                    id="newViolatedLimit"
                                                                                    name="newViolatedLimit"
                                                                                    value="${violatedLimit}">
                    </td>
                    <td>
                        <input
                                class="btn btn-primary"
                                type="submit" value="Change">
                    </td>
                </form>
            </tr>
            <tr>
                <form class="form-inline" action="admin/changeStudentLimit" method="POST">
                    <td>
                        <label style="color:white;">Student limit: </label> <input class="form-control" type="number"
                                                                                   id="newStudentLimit"
                                                                                   name="newStudentLimit"
                                                                                   value="${studentLimit}">
                    </td>
                    <td>
                        <input
                                class="btn btn-primary"
                                type="submit" value="Change">
                    </td>
                </form>
            </tr>
        </table>
    </div>
</body>
</html>