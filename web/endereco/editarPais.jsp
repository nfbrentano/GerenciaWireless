<%-- Document : editarPais Created on : Jun 7, 2018, 10:54:51 AM Author : natan --%>

    <%@page import="java.sql.ResultSet" %>
        <%@page import="java.sql.Statement" %>
            <%@page import="java.sql.DriverManager" %>
                <%@page import="java.sql.Connection" %>
                    <%@page import="java.sql.Connection" %>

                        <!DOCTYPE html>
                        <html>

                        <head>
                            <!-- Required meta tags -->
                            <meta charset="utf-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

                            <!-- Bootstrap CSS -->
                            <!-- Latest compiled and minified CSS -->
                            <link rel="stylesheet"
                                href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

                            <!-- Optional theme -->
                            <link rel="stylesheet"
                                href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

                            <!-- Latest compiled and minified JavaScript -->
                            <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
                            <!-- Outros -->
                            <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
                            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
                            <script language="JavaScript"
                                src="<%=request.getContextPath()%>/JS/somenteNumeros.js"></script>
                            <script language="JavaScript" src="<%=request.getContextPath()%>/JS/validaForm.js"></script>

                            <title>Alterar País</title>
                        </head>

                        <body>

                            <div class="main-content">

                                <% try { String cod=request.getParameter("cod"); if (cod !=null) { service.PaisService
                                    service=new service.PaisService(); model.Pais
                                    pai=service.getById(Integer.parseInt(cod)); request.setAttribute("pai", pai); } }
                                    catch (Exception e) { out.println("Erro ao buscar país: " + e.getMessage());
                }
            %>


            <%@ include file = " nav.jsp"%>

                                    <div class="container">
                                        <form data-abide="ajax" id="order_form" class="form-horizontal" role="form"
                                            method="post" action="<%=request.getContextPath()%>/PaisController">
                                            <h2 class="card-title mt-3 text-center">Alterar País</h2>


                                            <div class="form-group">
                                                <label for="cod" class="col-sm-1 control-label">Código:</label>
                                                <div class="col-sm-9">
                                                    <input class="form-control" type="text" size="50" required="true"
                                                        name="idpais" readonly="true" value='${pai.idpais}'>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="nome" class="col-sm-1 control-label">Nome:</label>
                                                <div class="col-sm-9">
                                                    <input class="form-control" type="text" size="50" required="true"
                                                        name="nome" maxlenght=80 value='${pai.nome}'>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-sm-9 col-sm-offset-1">
                                                    <button type="submit" class="btn btn-primary btn-block"
                                                        name="enviar" onclick="validaFormPais()">Atualizar</button>
                                                    <a class="btn btn-primary btn-block"
                                                        href="<%=request.getContextPath()%>/endereco/listarPaises.jsp"
                                                        name="voltar">Voltar</a>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                            </div>

                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                        </body>

                        </html>