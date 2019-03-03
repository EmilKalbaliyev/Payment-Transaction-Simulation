<%-- 
    Document   : hello
    Created on : Feb 15, 2018, 12:13:08 PM
    Author     : Mardan Safarov & Emil Kalbaliyev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <nav class="navbar nav navbar-inverse navbar-fixed-top" style="background-color: black " >

            <div class="container">
                <ul class="nav navbar-nav">
                    <li ><a href="/account/index" class="linksNav">Accounts</a></li>
                    <li><a href="/transaction/index" class="linksNav">Transactions</a></li>

                </ul>         
            </div>
        </nav>
        <div class="container">
            <h1>New user</h1>
            <p> ${msj} </p>
            <form:form method="POST" action="/account/edit" modelAttribute="account">
                <table >        
                    <tr>    
                        <td><form:label path="name">Name</form:label></td>
                        <td><form:input path="name"/></td>
                    </tr>   
                    <tr>    
                        <td><form:label path="surname">Surname</form:label></td>
                        <td><form:input path="surname"/></td> 
                    </tr>  
                    <tr>    
                        <td><form:label path="patronymic">Patronymic</form:label></td>
                        <td><form:input path="patronymic"/></td> 
                    </tr>
                    <tr>    
                        <td><form:label path="pin">Pin</form:label></td>
                        <td><form:input path="pin"/></td>
                    </tr>   
                    <tr>    
                        <td><form:hidden path="id"/></td>
                    </tr>  
                    <tr>    
                        <td><form:label path="amount">Amount</form:label></td>
                        <td><form:input path="amount"/></td> 
                    </tr>
  
                    <tr>    
                        <td> </td>    
                        <td><input type="submit" value="Save" /></td>    
                    </tr>    
                </table>  
            </form:form>
        </div>
    </body>
</html>
