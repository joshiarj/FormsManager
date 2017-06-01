<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="SITE_URL" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forms Manager</title>
        <link href="${SITE_URL}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${SITE_URL}/static/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="${SITE_URL}/static/css/header.css" rel="stylesheet" type="text/css"/>
        <script src="${SITE_URL}/static/js/jquery.min.js" type="text/javascript"></script>
        <script src="${SITE_URL}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body background="${SITE_URL}/img/bg-wallpaper2.jpg" style="background-attachment:fixed">
        <div class="container" style="background: #f9f9f9">
        <div class="img-rounded" id="top-header" style="background:darkgreen;opacity:0.9">
            <table style="width: 100%; height: 40px; vertical-align: middle; table-layout: fixed">
                <tr>
                    <td style="padding-left:5px">
                        <%--<c:if test="${empty loggedIn}">--%>
                            <a class="header-link" href="${SITE_URL}/user/register">
                                <span class="glyphicon glyphicon-user"></span> Register
                            </a> | 
                            <a class="header-link" href="${SITE_URL}/login">
                                <span class="glyphicon glyphicon-log-in"></span> Log In
                            </a>
                        <%--</c:if>--%>
                        <%--<c:if test="${not empty loggedIn}">--%>
<!--                            Hello, ${loggedInUser.username}! | 
                            <a href="${SITE_URL}/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a>-->
                        <%--</c:if>--%>
                    </td>
                    <td align="center"><a href="${SITE_URL}">
                            <img class="img-rounded" src="${SITE_URL}/img/logo.png" height="35" width="200"/></a>
                    </td>
                    <td align="right" style="padding-left:5px">
                        <form class="navbar-form" role="search">
                            <div>
                            <input type="text" class="form-control" name="searchParam" placeholder="Search"/>
                            <button type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                            </div>
                        </form>
                    </td>
                </tr>
            </table>
        </div>