<%-- Document : listarEstados Created on : Jun 5, 2018, 10:28:30 AM Author : natan --%>

    <%@page import="java.sql.DriverManager" %>
        <%@page import="java.sql.Connection" %>
            <%@page import="java.sql.Connection" %>
                <%@page import="java.sql.Statement" %>
                    <%@page import="java.sql.ResultSet" %>
                        <%@page contentType="text/html" pageEncoding="UTF-8" %>
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
                                <script>
                                    <!-- Função javascript para chamar edição de registro -->
                                    function editarRegistroEstado(idestado) {
                                        window.location.href = "<%=request.getContextPath()%>/endereco/editarEstado.jsp?cod=" + idestado;

                                    }
                                    < !--Função javascript para chamar Servlet de exclusão de registro-- >
                                        function excluirRegistroEstado(idestado, nome) {
                                            if (confirm('Confirma exclusão do estado de código: ' + idestado + ' - Nome: ' +
                                                nome + '?')) {
                                                window.location.href = "<%=request.getContextPath()%>/ExcluirEstado?cod=" +
                                                    idestado;
                                            } else {
                                                alert('Exclusão cancelada.');
                                            }
                                        }
                                </script>

                                <title>Cadastro de Estados</title>

                            </head>

                            <body>
                                <form name="formTeste" class="form-register1">

                                    <%@ include file="nav.jsp" %>

                                        <h2 class="card-title mt-3 text-center">Cadastro de Estados</h2>

                                        <!-- Formulário com campo para pesquisar usuário -->

                                        <form id="custom-search-form" class="form-search form-horizontal pull-right"
                                            name="frmLocalizar" action="listarEstados.jsp" method="POST">
                                            <div class="panel-body">
                                                <div class="input-group">

                                                    <input class="form-control" placeholder="Buscar pelo nome..."
                                                        type="text" name="localizaEstado" value="">
                                                    <div class="input-group-btn">
                                                        <button class="btn btn-default" type="submit" value="Pesquisar"
                                                            name="btnLocaliza"><span class="glyphicon glyphicon-search"
                                                                </span> Buscar </button>
                                                    </div>
                                                    <div class="input-group-btn">
                                                        <a class="btn btn-default" type="submit"
                                                            href="<%=request.getContextPath()%>/endereco/incluirEstado.jsp"
                                                            name="incluirCadastro"><span
                                                                class="glyphicon glyphicon-plus-sign"></span> Incluir
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>

                                        <table class="table table-responsive">

                                            <thead>
                                                <tr>
                                                    <th>Código</th>
                                                    <th>Nome</th>
                                                    <th>Sigla</th>
                                                    <th>Cód Pais</th>
                                                    <th></th>
                                                    <th></th>

                                                </tr>
                                            </thead>
                                            <tbody>

                                                <!-- Buscar registros no banco de dados
                         Cada linha da tabela apresentará um registro da tabela "clientes" -->
                                                <% try { // Conectar o banco usando o utilitário padronizado Connection
                                                    conn=util.Db.getConexao(); try { String
                                                    query="SELECT idestado, nome, sigla, pais_idpais FROM estado WHERE disponibilidade = true "
                                                    ; String localizarValor=request.getParameter("localizaEstado"); if
                                                    (localizarValor !=null && !localizarValor.isEmpty()) { query
                                                    +=" AND LOWER(nome) LIKE ?" ; } try (java.sql.PreparedStatement
                                                    st=conn.prepareStatement(query)) { if (localizarValor !=null &&
                                                    !localizarValor.isEmpty()) { st.setString(1, "%" +
                                                    localizarValor.toLowerCase() + "%" ); out.write("<p>Pesquisando por:
                                                    " + localizarValor + "</p>");
                                                    }

                                                    try (ResultSet rs = st.executeQuery()) {
                                                    // Processar cada item do banco e adicionar uma linha na tabela
                                                    while (rs.next()) {
                                                    %>

                                                    <!-- Cria nova linha na tabela -->
                                                    <tr>
                                                        <!-- Colunas da linha -->

                                                        <td>
                                                            <%= rs.getString("idestado")%>
                                                        </td>
                                                        <td>
                                                            <%= rs.getString("nome")%>
                                                        </td>
                                                        <td>
                                                            <%= rs.getString("sigla")%>
                                                        </td>
                                                        <td>
                                                            <%= rs.getString("pais_idpais")%>
                                                        </td>
                                                        <td class="text-center"> <a class='btn btn-info btn-xs'
                                                                onclick="editarRegistroEstado(<%= rs.getInt("
                                                                idestado")%>);"><span
                                                                    class="glyphicon glyphicon-edit"></span> Editar</a>
                                                        </td>
                                                        <td class="text-center"> <a class='btn btn-danger btn-xs'
                                                                onclick="excluirRegistroEstado(<%= rs.getInt("
                                                                idestado")%>, '<%= rs.getString("nome")%>');"><span
                                                                        class="glyphicon glyphicon-remove"></span>
                                                                    Del</a></td>

                                                    </tr>
                                                    <% } // Fim do while } } } catch (Exception e) { out.write("Ocorreu
                                                        um erro ao buscar os estados <span style='color: red'>" +
                                                        e.getMessage() + "</span>");
                                                        }
                                                        } catch (Exception e) {
                                                        out.write("Ocorreu um erro conectando a base: <span
                                                            style='color: red'>" + e.getMessage() + "</span>");
                                                        }
                                                        %>


                                            </tbody>
                                        </table>
                                </form>
                                </form>

                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                                <script
                                    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                            </body>

                            </html>