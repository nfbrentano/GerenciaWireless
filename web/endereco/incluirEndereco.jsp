<%-- Document : incluirEndereco Created on : Jun 7, 2018, 1:25:02 PM Author : natan --%>

    <%@page import="dao.EnderecoDao" %>
        <%@page import="dao.BairroDao" %>
            <%@page import="java.sql.Statement" %>
                <%@page import="java.sql.Statement" %>
                    <%@page import="java.sql.ResultSet" %>
                        <%@page import="java.sql.Statement" %>
                            <%@page import="java.sql.DriverManager" %>
                                <%@page import="java.sql.Connection" %>
                                    <%@page import="java.sql.Connection" %>
                                        <%@page import="java.sql.Connection" %>
                                            <%-- Para poder utilizar a jstl é necessário adicionar o respectivo .jar nas
                                                bibliotecas --%>
                                                <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                                                    <!DOCTYPE html>
                                                    <html>

                                                    <head>
                                                        <!-- Required meta tags -->
                                                        <meta charset="utf-8">
                                                        <meta name="viewport"
                                                            content="width=device-width, initial-scale=1, shrink-to-fit=no">

                                                        <!-- Bootstrap CSS -->
                                                        <!-- Latest compiled and minified CSS -->
                                                        <link rel="stylesheet"
                                                            href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

                                                        <!-- Optional theme -->
                                                        <link rel="stylesheet"
                                                            href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

                                                        <!-- Latest compiled and minified JavaScript -->
                                                        <script
                                                            src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
                                                        <!-- Outros -->
                                                        <link rel="stylesheet"
                                                            href="<%=request.getContextPath()%>/CSS/bootstrap.css">
                                                        <script language="JavaScript"
                                                            src="<%=request.getContextPath()%>/JS/somenteNumeros.js"></script>
                                                        <script language="JavaScript"
                                                            src="<%=request.getContextPath()%>/JS/validaForm.js"></script>
                                                        <title>Cadastro de Rua</title>
                                                    </head>

                                                    <body onload="document.formTeste.rua.focus()">

                                                        <div class="main-content">

                                                            <% try { service.BairroService bairroService=new
                                                                service.BairroService();
                                                                request.setAttribute("listaBairros",
                                                                bairroService.listar()); int proximoId=0; try
                                                                (java.sql.Connection conn=util.Db.getConexao();
                                                                java.sql.Statement st=conn.createStatement();
                                                                java.sql.ResultSet rs=st.executeQuery("SELECT
                                                                COALESCE(max(idendereco), 0) + 1 FROM endereco")) { if
                                                                (rs.next()) { proximoId=rs.getInt(1); } }
                                                                request.setAttribute("proximoId", proximoId); } catch
                                                                (Exception e) { out.write("<p class='text-danger'>Erro
                                                                ao carregar dados: " + e.getMessage() + "</p>");
                                                                }
                                                                %>

                                                                <%@ include file=" nav.jsp" %>

                                                                    <div class="container">
                                                                        <form data-abide="ajax" id="order_form"
                                                                            class="form-horizontal" role="form"
                                                                            method="post"
                                                                            action="<%=request.getContextPath()%>/EnderecoController">

                                                                            <h2 class="card-title mt-3 text-center">
                                                                                Cadastro de Rua</h2>

                                                                            <div class="form-group">
                                                                                <label for="cod"
                                                                                    class="col-sm-1 control-label">Código:
                                                                                </label>
                                                                                <div class="col-sm-9">
                                                                                    <input class="form-control"
                                                                                        type="text" id="cod"
                                                                                        name="idendereco" maxlengt=10
                                                                                        readonly="true" size="50"
                                                                                        value='${proximoId}'>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group">
                                                                                <label for="rua"
                                                                                    class="col-sm-1 control-label">Rua:
                                                                                </label>
                                                                                <div class="col-sm-9">
                                                                                    <input class="form-control"
                                                                                        type="text" id="rua" name="rua"
                                                                                        maxlengt=255 size="50" required>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group">
                                                                                <label for="codbairro"
                                                                                    class="col-sm-1 control-label">Cód
                                                                                    do Bairro</label>
                                                                                <div class="col-sm-9">
                                                                                    <input class="form-control"
                                                                                        type="text" required="true"
                                                                                        list="bairros"
                                                                                        name="bairro_idbairro"
                                                                                        maxlenght="50" size="50"
                                                                                        required>

                                                                                    <datalist id="bairros">

                                                                                        <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                                                                                        <c:forEach
                                                                                            items="${listaBairros}"
                                                                                            var="bairro">
                                                                                            <option
                                                                                                value="${bairro.idbairro}">
                                                                                                ${bairro.nome}
                                                                                            </option>
                                                                                        </c:forEach>

                                                                                    </datalist>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group">
                                                                                <div class="col-sm-9 col-sm-offset-1">
                                                                                    <button type="submit"
                                                                                        class="btn btn-primary btn-block"
                                                                                        name="enviar"
                                                                                        onclick="validaFormEndereco()">Registrar</button>
                                                                                    <button type="reset"
                                                                                        class="btn btn-primary btn-block"
                                                                                        name="limpar">Limpar</button>
                                                                                    <a class="btn btn-primary btn-block"
                                                                                        name="voltar" type="submit"
                                                                                        href="<%=request.getContextPath()%>/endereco/listarPaises.jsp">
                                                                                        Voltar </a>
                                                                                </div>
                                                                            </div>


                                                                            <script
                                                                                src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                                                                            <script
                                                                                src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                                                    </body>

                                                    </html>