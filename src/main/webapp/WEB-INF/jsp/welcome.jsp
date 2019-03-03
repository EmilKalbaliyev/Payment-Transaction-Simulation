<%-- 
    Document   : welcome
    Created on : Feb 15, 2018, 11:43:58 AM
    Author     : Mardan Safarov & Emil Kalbaliyev
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Accounts</title>
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

    <nav class="navbar nav navbar-inverse navbar-fixed-top" style="background-color: black " >

        <div class="container">
            <ul class="nav navbar-nav">
                <li ><a href="/account/index" class="linksNav">Accounts</a></li>
                <li><a href="/transaction/index" class="linksNav">Transactions</a></li>


            </ul>  
            <c:url value="/logout" var="logoutUrl" />
            <form action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
            <script>
                function formSubmit() {
                    document.getElementById("logoutForm").submit();
                }
            </script>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:formSubmit()" class="linksNav">Log Out</a></li>
            </ul>  

        </div>
    </nav>
    <div class="container">

        <h1>Accounts <a href="add" type="submit" class="btn" style="background-color:black"> Add new </a></h1> 

        <c:if test="${not empty msj}">
            <div class="alert alert-success" role="alert">
                ${msj}
            </div>
        </c:if>
        <table class="table">
            <thead>
                <tr>
                    <th>Account ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Patronymic</th>
                    <th>Amount</th>
                    <th>Action</th>
                </tr>
            </thead>

            <c:forEach var = "account" items="${accounts}">
                <tbody>
                    <tr>
                        <td>${account.id}</td>
                        <td>${account.owner.name}</td>
                        <td>${account.owner.surname}</td>
                        <td>${account.owner.patronymic}</td>
                        <td>${account.amount}</td>
                        <td><a href="edit/${account.id}">Edit</a> <a href="delete/${account.id}">Delete</a></td>

                    </tr>
                </tbody>
            </c:forEach>


        </table>
    </div>
</body>
</html>




