<%-- 
    Document   : incluirPais
    Created on : Jun 7, 2018, 1:48:34 PM
    Author     : natan
--%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%-- Para poder utilizar a jstl é necessário adicionar o respectivo .jar nas bibliotecas --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <head>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->

        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rubik" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">

        <title>Cadastro de País</title>

    </head>

    <body onload="document.formTeste.nome.focus()">

        <div class="main-content">

            <%
                try {

                    // Registrar o driver JDBC para PostgreSQL
                    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
                    // Conectar o banco
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
                    // Statement para executar os comandos sql
                    Statement st = conn.createStatement();

                    String query = "SELECT max(idpais+1) FROM pais";
                    ResultSet rs = st.executeQuery(query);

                    rs.next();
            %>    

            <%@ include file = "nav.jsp"%>

            <div class="container">
                <form data-abide="ajax" id="order_form" class="form-horizontal" role="form" method="post" action="incluir_pais.jsp">


                    <h2 class="card-title mt-3 text-center">Cadastro de País</h2>

                    <div class="form-group">
                        <label for="cod" class="col-sm-1 control-label">Código:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text"  id="cod" name="idpais" maxlengt=10 readonly="true" value='<%= rs.getString("max")%>'>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nome" class="col-sm-1 control-label">Nome:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text"  id="nome" name="nome" maxlengt=80 required >
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-1">
                            <button type="submit" class="btn btn-primary btn-block" name="enviar"  onclick="validaFormPais()">Registrar</button>
                            <button type="reset" class="btn btn-primary btn-block" name="limpar" >Limpar</button>
                            <a class="btn btn-primary btn-block" name="voltar" type="submit" href="<%=request.getContextPath()%>/endereco/listarPaises.jsp" > Voltar </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <%
            } catch (Exception e) {
                out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
            }
        %>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </body>
</html>