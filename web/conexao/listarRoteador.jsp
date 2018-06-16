<%-- 
    Document   : listarRoteador
    Created on : Jun 8, 2018, 10:30:10 AM
    Author     : natan
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
        <!-- Outros -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
        <script type="text/javascript">
<!-- Função javascript para chamar edição de registro -->
            function editarRegistro(idcadastroPessoa) {
                window.location.href = "<%=request.getContextPath()%>/conexao/editarCadastroRoteador.jsp?cod=" + idcadastroPessoa;
            }

<!-- Função javascript para chamar Servlet de exclusão de registro -->
            function excluirRegistro(idpontoacesso, ssid) {
                if (confirm('Confirma exclusão do Roteador de código: ' + idpontoacesso + ' - SSID: ' + ssid + '?')) {
                    window.location.href = "<%=request.getContextPath()%>/ExcluirRoteador?cod=" + idpontoacesso;
                } else {
                    alert('Exclusão cancelada.');
                }
            }
        </script>
        <title>Cadastro de Roteadores</title>

    </head>
    <body>

        <%@ include file = "nav.jsp"%>


        <form id="custom-search-form" class="form-search form-horizontal pull-right" name="frmLocalizar" action="listarCadastroPessoa.jsp" method="POST">
            <div class="panel-body">
                <div class="input-group">

                    <input class="form-control" placeholder="Buscar pelo nome..." type="text" name="localizaRoteador" value="" >             
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit" value="Pesquisar" name="btnLocaliza"><span class="glyphicon glyphicon-search"</span> Buscar  </button>
                    </div>
                    <div class="input-group-btn">                            
                        <a class="btn btn-default" type="submit" href="<%=request.getContextPath()%>/conexao/incluirCadastroRoteador.jsp" name="incluirRoteador"><span class="glyphicon glyphicon-plus-sign" ></span> Incluir </a>
                    </div>
                </div>
            </div>
        </form>
        <!-- Tabela -->


        <table class="table table-responsive">

            <thead>
                <tr>
                    <th >Código</th>
                    <th >SSID</th>
                    <th >Modelo</th>
                    <th >Largura de Banda Mhz</th>
                    <th >Frequência</th>
                    <th >IP Roteador</th>
                    <th >Usuário</th>
                    <th >Senha</th>
                    <th >Editar</th>
                    <th >Deletar</th>

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
                            String query = "SELECT idpontoacesso, ssid, modelo, largurabanda, frequencia, iproteador, usuario, pass FROM pontoacesso WHERE disponibilidade = true ";

                            // Se foi informado um valor no campo de pesquisa, adicionar ao SQL
                            if (request.getParameter("localizaRoteador") != null && request.getParameter("localizaRoteador") != "") {
                                String localizarValor = request.getParameter("localizaRoteador").toLowerCase();
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

                    <td> <%= rs.getString("idpontoacesso")%> </td>
                    <td> <%= rs.getString("ssid")%> </td>
                    <td> <%= rs.getString("modelo")%> </td>
                    <td> <%= rs.getString("largurabanda")%> </td>
                    <td> <%= rs.getString("frequencia")%> </td>
                    <td> <%= rs.getString("iproteador")%> </td>
                    <td> <%= rs.getString("usuario")%> </td>
                    <td> <%= rs.getString("pass")%> </td>
                    <td class="text-center"> <a class='btn btn-info btn-xs' onclick="editarRegistro(<%= rs.getInt("idpontoacesso")%>);"><span class="glyphicon glyphicon-edit"></span>  Editar</a> </td> 
                    <td class="text-center"> <a class='btn btn-danger btn-xs' onclick="excluirRegistro(<%= rs.getInt("idpontoacesso")%>, '<%= rs.getString("ssid")%>');"><span class="glyphicon glyphicon-remove"></span> Del</a></td>

                </tr>
                <%
                            } // Fim do while

                            rs.close();

                        } catch (Exception e) {
                            out.write("Ocorreu um erro ao buscar os cadastros de usuários <span style='color: red'>" + e.getMessage() + "</span>");
                        } finally {
                        }

                    } catch (Exception e) {
                        out.write("Ocorreu um erro conectando a base: <span style='color: red'>" + e.getMessage() + "</span>");
                    }

                %>                

            </tbody>
        </table> 

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
