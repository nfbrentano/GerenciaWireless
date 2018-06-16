<%-- 
    Document   : editarBairro
    Created on : Jun 7, 2018, 10:05:29 AM
    Author     : natan
--%>

<%@page import="dao.EnderecoDao"%>
<%@page import="dao.BairroDao"%>
<%@page import="dao.CidadeDao"%>
<%@page import="dao.EstadoDao"%>
<%@page import="dao.PaisDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>

<%-- Para poder utilizar a jstl é necessário adicionar o respectivo .jar nas bibliotecas --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

        <title>Alterar Bairro</title>

    </head>
    <body>

        <div class="main-content">

            <%
                // Instancia Objeto de Acesso aos Dados (DAO)
                PaisDao pais = new PaisDao();
                // Buscar dados no banco
                request.setAttribute("listaPaises", pais.getAllPaises());
            %>
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
                // Instancia Objeto de Acesso aos Dados (DAO)
                BairroDao bairro = new BairroDao();
                // Buscar dados no banco
                request.setAttribute("listaBairros", bairro.getAllBairros());
            %>
            <%
                // Instancia Objeto de Acesso aos Dados (DAO)
                EnderecoDao endereco = new EnderecoDao();
                // Buscar dados no banco
                request.setAttribute("listaEnderecos", endereco.getAllEnderecos());
            %>
            <%
                try {

                    // Registrar o driver JDBC para PostgreSQL
                    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
                    // Conectar o banco
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
                    // Statement para executar os comandos sql
                    Statement st = conn.createStatement();

                    String query = "SELECT idbairro, nome, cidade_idcidade FROM bairro WHERE idbairro = " + request.getParameter("cod");
                    ResultSet rs = st.executeQuery(query);

                    rs.next();
            %>            



            <%@ include file = "nav.jsp"%>
            <div class="container">
                <form data-abide="ajax" id="order_form" class="form-horizontal" role="form" method="post" action="alterarBairro.jsp">

                    <h2 class="card-title mt-3 text-center">Alterar Bairro</h2>

                    <div class="form-group">
                        <label for="cod" class="col-sm-1 control-label">Código:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" required="true" name="idbairro" readonly="true" size="50" value='<%= rs.getInt("idbairro")%>'>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nome" class="col-sm-1 control-label">Nome: </label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" required="true" name="nome" maxlength="45" size="50" value='<%= rs.getString("nome")%>'>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="codcidade" class="col-sm-1 control-label">Cód da Cidade:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" onkeyup="somenteNumeros(this);"  required="true" name="cidade_idcidade" list="cidades" maxlength="45" size="50" value='<%= rs.getString("cidade_idcidade")%>'>
                            <datalist id="cidades">

                                <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                                <c:forEach items="${listaCidades}" var="cidade">
                                    <option value="${cidade.idcidade}">${cidade.nome}</option>
                                </c:forEach>

                            </datalist>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-1">
                            <button type="submit" class="btn btn-primary btn-block" name="enviar"  onclick="validaFormBairro()">Atualizar</button>
                            <a class="btn btn-primary btn-block" href="<%=request.getContextPath()%>/endereco/listarBairros.jsp" name="voltar" >Voltar</a>
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