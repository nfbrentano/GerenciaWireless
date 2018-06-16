<%-- 
    Document   : incluirCidade
    Created on : Jun 7, 2018, 11:40:32 AM
    Author     : natan
--%>

<%@page import="dao.CidadeDao"%>
<%@page import="dao.EstadoDao"%>
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
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/somenteNumeros.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/validaForm.js"></script>
        <title>Cadastro de Cidade</title>
    </head>

    <body onload="document.formTeste.nome.focus()">

        <div class="main-content">
            <%
                // Instancia Objeto de Acesso aos Dados (DAO)
                EstadoDao estado = new EstadoDao();
                // Buscar dados no banco
                request.setAttribute("listaEstados", estado.getAllEstados());
            %>
            <%
                // Instancia Objeto de Acesso aos Dados (DAO)
                CidadeDao cidade = new CidadeDao();
                // Buscar dados no banco
                request.setAttribute("listaCidades", cidade.getAllCidades());
            %>

            <%
                try {

                    // Registrar o driver JDBC para PostgreSQL
                    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
                    // Conectar o banco
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
                    // Statement para executar os comandos sql
                    Statement st = conn.createStatement();

                    String query = "SELECT max(idcidade+1) FROM cidade";
                    ResultSet rs = st.executeQuery(query);

                    rs.next();
            %>    



            <%@ include file = "nav.jsp"%>

            <div class="container">
                <form data-abide="ajax" id="order_form" class="form-horizontal" role="form" onsubmit="return validaFormCidade()" method="post" action="incluir_cidade.jsp">

                    <h2 class="card-title mt-3 text-center">Cadastro de Cidade</h2>

                    <div class="form-group">
                        <label for="cod" class="col-sm-1 control-label">Código:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text"  id="cod" name="idcidade" maxlengt=10 readonly="true" size="50"  value='<%= rs.getString("max")%>'>

                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nome" class="col-sm-1 control-label">Nome: </label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text"  id="nome" name="nome" maxlengt=80 size="50"  required >

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="codestado" class="col-sm-1 control-label">Cód do Estado </label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" onkeyup="somenteNumeros(this);" list="estados" name="estado_idestado" size="50"  maxlenght=10>

                            <datalist id="estados">
                                <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                                <c:forEach items="${listaEstados}" var="estado">
                                    <option value="${estado.idestado}">${estado.nome}</option>
                                </c:forEach>

                            </datalist>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-1">
                            <button type="submit" class="btn btn-primary btn-block" name="enviar"  onclick="validaFormCidade()">Registrar</button>
                            <button type="reset" class="btn btn-primary btn-block" name="limpar" >Limpar</button>
                            <a class="btn btn-primary btn-block" name="voltar" type="submit" href="<%=request.getContextPath()%>/endereco/listarCidades.jsp" > Voltar </a>
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