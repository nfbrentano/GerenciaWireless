<%-- 
    Document   : sucesso_registro
    Created on : May 23, 2018, 9:50:23 PM
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">

        <title>Registro Concluído</title>

    </head>
    <body>

        <div class="container">
            <div class="row">
                <div align="center" >
                    <img src="https://drive.google.com/uc?id=1lMOEmaHfzNaUZWyWPmjfiu5M7LP5BSDL" class="img-responsive" alt="Responsive image" />
                    <h3> Cadastro efetuado com sucesso! <a class="btn btn-primary " name="voltar" type="submit" href="<%=request.getContextPath()%>/cadastroPessoa/listarCadastroPessoa.jsp" > Voltar </a></h3>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </body>
</html>
