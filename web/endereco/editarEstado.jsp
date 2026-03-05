<%-- Document : editarEstado Created on : Jun 7, 2018, 10:40:11 AM Author : natan --%>

    <%@page import="dao.PaisDao" %>
        <%@page import="dao.EstadoDao" %>
            <%@page import="java.sql.ResultSet" %>
                <%@page import="java.sql.Statement" %>
                    <%@page import="java.sql.DriverManager" %>
                        <%@page import="java.sql.Connection" %>
                            <%@page import="java.sql.Connection" %>
                                <%-- Para poder utilizar a jstl é necessário adicionar o respectivo .jar nas bibliotecas
                                    --%>
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

                                            <title>Alterar Estado</title>

                                        </head>

                                        <body>

                                            <div class="main-content">

                                                <% try { service.PaisService paisService=new service.PaisService();
                                                    request.setAttribute("listaPaises", paisService.listar()); String
                                                    cod=request.getParameter("cod"); if (cod !=null) {
                                                    service.EstadoService service=new service.EstadoService();
                                                    model.Estado est=service.getById(Integer.parseInt(cod));
                                                    request.setAttribute("est", est); } } catch (Exception e) {
                                                    out.println("Erro ao carregar dados: " + e.getMessage());
                }
            %>

            <%@ include file = " nav.jsp"%>

                                                    <div class="container">
                                                        <form data-abide="ajax" id="order_form" class="form-horizontal"
                                                            role="form" method="post"
                                                            action="<%=request.getContextPath()%>/EstadoController">
                                                            <h2 class="card-title mt-3 text-center">Alterar
                                                                Estado</h2>

                                                            <div class="form-group">
                                                                <label for="cod"
                                                                    class="col-sm-2 control-label">Código:</label>
                                                                <div class="col-sm-10">
                                                                    <input class="form-control" type="text"
                                                                        name="idestado" readonly="true"
                                                                        value='${est.idestado}'>
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="nome"
                                                                    class="col-sm-2 control-label">Nome:</label>
                                                                <div class="col-sm-10">
                                                                    <input class="form-control" type="text" name="nome"
                                                                        maxlength="80" required value='${est.nome}'>
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="sigla"
                                                                    class="col-sm-2 control-label">Sigla:</label>
                                                                <div class="col-sm-10">
                                                                    <input class="form-control" type="text" name="sigla"
                                                                        maxlength="2" required value='${est.sigla}'>
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label for="codpais"
                                                                    class="col-sm-1 control-label">Código da
                                                                    País: </label>
                                                                <div class="col-sm-9">
                                                                    <input class="form-control" type="text" size="50"
                                                                        onkeyup="somenteNumeros(this);" required="true"
                                                                        list="paises" name="pais_idpais" maxlenght=10
                                                                        value='${est.pais_idpais}'>
                                                                    <datalist id="paises">
                                                                        <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                                                                        <c:forEach items="${listaPaises}" var="pais">
                                                                            <option value="${pais.idpais}">
                                                                                ${pais.nome}</option>
                                                                        </c:forEach>

                                                                    </datalist>
                                                                </div>
                                                            </div>


                                                            <div class="form-group">
                                                                <div class="col-sm-9 col-sm-offset-1">
                                                                    <button type="submit"
                                                                        class="btn btn-primary btn-block" name="enviar"
                                                                        onclick="validaFormEstado()">Atualizar</button>
                                                                    <a class="btn btn-primary btn-block"
                                                                        href="<%=request.getContextPath()%>/endereco/listarEstados.jsp"
                                                                        name="voltar">Voltar</a>
                                                                </div>

                                                            </div>
                                                        </form>

                                                    </div>
                                                    <script
                                                        src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                                                    <script
                                                        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                                        </body>

                                        </html>