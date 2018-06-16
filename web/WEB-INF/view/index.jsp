<%-- 
    Document   : index
    Created on : May 25, 2018, 11:41:49 AM
    Author     : natan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <head>
        <meta name="viewport" content="text/html; charset=UTF-8, width=device-width, initial-scale=1, shrink-to-fit=no" http-equiv="Content-Type">
        <title>Autenticação de Usuário</title>

    </head>
    <body>
        <div class = "container">
            <div class="wrapper">

                <h3 class="form-signin-heading">Sistema de Gerenciamento Wireless</h3>

                <hr class="colorgraph"><br>
                <h6>Bem vindo de volta!</h6>
                <c:if test="${mensagens.existeErros}">
                    <div id="erro">
                        <ul>
                            <c:forEach var="erro" items="${mensagens.erros}">
                                <li> ${erro} </li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <form method="post" action="index.jsp">
                    <input type="text" name="login" class="form-control" name="Username" placeholder="Username" required="" autofocus="" value="${param.login}" />
                    <input type="password" name="senha" class="form-control" name="Password" placeholder="Password" required=""/>                     
                    <div class="wrapper">


                        <button class="btn btn-lg btn-primary btn-block"  name="bOK" value="Login" type="Submit">Entrar</button>  			




                    </div>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
</html>