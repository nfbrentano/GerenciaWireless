<%-- 
    Document   : listarCidades
    Created on : Jun 5, 2018, 9:45:56 AM
    Author     : natan
--%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
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
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rubik" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
        <script>
<!-- Função javascript para chamar edição de registro -->
            function editarRegistroCidade(idcidade) {
                window.location.href = "<%=request.getContextPath()%>/endereco/editarCidade.jsp?cod=" + idcidade;

            }
<!-- Função javascript para chamar Servlet de exclusão de registro -->
            function excluirRegistroCidade(idcidade, nome) {
                if (confirm('Confirma exclusão do cidade de código: ' + idcidade + ' - Nome: ' + nome + '?')) {
                    window.location.href = "<%=request.getContextPath()%>/ExcluirCidade?cod=" + idcidade;
                } else {
                    alert('Exclusão cancelada.');
                }
            }

        </script>

        <title>Cadastro de Cidades</title>

    </head>

    <body>
        <form name="formTeste"  class="form-register1">

            <%@ include file = "nav.jsp"%>

            <h2 class="card-title mt-3 text-center">Cadastro de Cidades</h2>

            <!-- Formulário com campo para pesquisar usuário -->
            <form id="custom-search-form" class="form-search form-horizontal pull-right" name="frmLocalizar" action="listarCidades.jsp" method="POST">
                <div class="panel-body">
                    <div class="input-group">

                        <input class="form-control" placeholder="Buscar pelo nome..." type="text" name="localizaCidade" value="" >             
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit" value="Pesquisar" name="btnLocaliza"><span class="glyphicon glyphicon-search"</span> Buscar  </button>
                        </div>
                        <div class="input-group-btn">                            
                            <a class="btn btn-default" type="submit" href="<%=request.getContextPath()%>/endereco/incluirCidade.jsp" name="incluirCadastro"><span class="glyphicon glyphicon-plus-sign" ></span> Incluir </a>
                        </div>
                    </div>
                </div>
            </form>

            <table class="table table-responsive">

                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Cód Estado</th>
                        <th>Editar</th>
                        <th>Deletar</th>

                    </tr>
                </thead>
                <tbody>

                    <!-- Buscar registros no banco de dados
                         Cada linha da tabela apresentará um registro da tabela "clientes" -->
                    <%
                        try {

                            // Registrar o driver JDBC para PostgreSQL
                            Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
                            // Conectar o banco
                            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
                            // Statement para executar os comandos sql
                            Statement st = conn.createStatement();

                            try {
                                String query = "SELECT idcidade, nome, estado_idestado FROM cidade WHERE disponibilidade = true ";

                                // Se foi informado um valor no campo de pesquisa, adicionar ao SQL
                                if (request.getParameter("localizaCidade") != null && request.getParameter("localizaCidade") != "") {
                                    String localizarValor = request.getParameter("localizaCidade").toLowerCase();
                                    out.write("<p>Pesquisando por: ");
                                    out.write(localizarValor + "</p>");

                                    // Adicionar condição WHERE ao SQL
                                    // Observe que o SQL busca o valor parcial (%) e em minúsculo (LOWER)
                                    // Vamos pesquisar em todos os campos...
                                    query += " AND LOWER(nome) LIKE '%" + localizarValor + "%'";

                                    //out.write(query); // debug SQL
                                }

                                ResultSet rs = st.executeQuery(query);

                                // Processar cada item do banco e adicionar uma linha na tabela
                                while (rs.next()) {
                    %>
                    <!-- Cria nova linha na tabela -->
                    <tr>                              
                        <!-- Colunas da linha -->

                        <td> <%= rs.getString("idcidade")%> </td>
                        <td> <%= rs.getString("nome")%> </td>
                        <td> <%= rs.getString("estado_idestado")%> </td>
                        <td class="text-center"> <a class='btn btn-info btn-xs' onclick="editarRegistroCidade(<%= rs.getInt("idcidade")%>);"><span class="glyphicon glyphicon-edit"></span>  Editar</a> </td> 
                        <td class="text-center"> <a class='btn btn-danger btn-xs' onclick="excluirRegistroCidade(<%= rs.getInt("idcidade")%>, '<%= rs.getString("nome")%>');"><span class="glyphicon glyphicon-remove"></span> Del</a></td>

                    </tr>
                    <%
                                } // Fim do while

                                rs.close();

                            } catch (Exception e) {
                                out.write("Ocorreu um erro ao buscar os cidades <span style='color: red'>" + e.getMessage() + "</span>");
                            } finally {
                            }

                        } catch (Exception e) {
                            out.write("Ocorreu um erro conectando a base: <span style='color: red'>" + e.getMessage() + "</span>");
                        }

                    %>                

                </tbody>
            </table>        
        </form>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
