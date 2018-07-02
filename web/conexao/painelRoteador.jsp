<%-- 
    Document   : painelRoteador
    Created on : Jul 2, 2018, 11:39:31 AM
    Author     : natan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>        
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <!-- Outros -->
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/somenteNumeros.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/validaForm.js"></script>

        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
        <% System.out.println("TESTE ");%>

        <title>Painel de Roteador</title>
    </head>

    <body>
        <h1>Hello World!</h1>


        <a class='btn btn-info btn-xs' onclick="snmptest()"><span class="glyphicon glyphicon-edit"></span>  Painel</a>
        <% System.out.println("FIM TESTE");%>
    </body>
</html>
