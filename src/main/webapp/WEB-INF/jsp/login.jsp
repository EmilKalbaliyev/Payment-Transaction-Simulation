<%-- 
Document   : hello
Created on : Feb 15, 2018, 12:13:08 PM
Author     : Mardan Safarov & Emil Kalbaliyev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <title>Add user</title>
    </head>
    <style>

        body{
            padding-top:50px;
        }
        a{
            color:grey;
            text-decoration: none;

        }
        a.btn:hover{
            color:white;
            text-decoration: none;
        }

    </style>
    <body>
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert">
                ${message}
            </div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>  
        </c:if>
        <c:url value='/account/login' var='url'/>
        <form:form action="${url}" method='POST' >
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Please sign in</h3>
                            </div>
                            <div class="panel-body">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Username" name="username" type="text">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                    </div>
                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </body>
</html>
