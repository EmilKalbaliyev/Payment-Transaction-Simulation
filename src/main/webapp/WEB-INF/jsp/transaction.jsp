<%-- 
    Document   : welcome
    Created on : Feb 15, 2018, 11:43:58 AM
    Author     : Mardan Safarov & Emil Kalbaliyev
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/transaction/index" class="linksNav">Log Out</a></li>
            </ul> 
        </div>
    </nav>
    <div class="container">

        <h1>Transactions </h1> 

        <c:if test="${not empty msj}">
            <div class="alert alert-success" role="alert">
                ${msj}
            </div>
        </c:if>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Modification Date</th>
                </tr>
            </thead>

            <c:forEach var = "transaction" items="${transaction}">
                <tbody>
                    <tr>
                        <td>${transaction.id}</td>
                        <td>${transaction.from}</td>
                        <td>${transaction.to}</td>
                        <td>${transaction.amount}</td>
                        <td>${transaction.date}</td>
                        <td>${transaction.status}</td>
                        <td>${transaction.status_date}</td>
                        <td>
                            <a class="btn btn-primary" href="/transaction/cancel/${transaction.id}" <c:if test="${transaction.status == 'Canceled'}"><c:out value="disabled='disabled'"/></c:if>>Cancel</a>
                            </td>
                        </tr>
                    </tbody>
            </c:forEach>


        </table>
    </div>
</body>
</html>




