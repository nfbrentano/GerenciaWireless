<%-- 
    Document   : editarCidade
    Created on : Jun 7, 2018, 10:17:36 AM
    Author     : natan
--%>

<%@page import="dao.CidadeDao"%>
<%@page import="dao.EstadoDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>

<%-- Para poder utilizar a jstl � necess�rio adicionar o respectivo .jar nas bibliotecas --%>
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

        <title>Alterar Cidade</title>

    </head>

    <body>

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

                    String query = "SELECT idcidade, nome, estado_idestado FROM cidade WHERE idcidade = " + request.getParameter("cod");
                    ResultSet rs = st.executeQuery(query);

                    rs.next();
            %>            



            <%@ include file = "nav.jsp"%>

            <div class="container">
                <form data-abide="ajax" id="order_form" class="form-horizontal" role="form"  method="post" action="alterarCidade.jsp">

                    <h2 class="card-title mt-3 text-center">Alterar Cidade</h2>

                    <div class="form-group">
                        <label for="cod" class="col-sm-1 control-label">C�digo:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" size="50"  required="true" name="idcidade" readonly="true" value='<%= rs.getInt("idcidade")%>'>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nome" class="col-sm-1 control-label">Nome:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" size="50"  required="true" name="nome" maxlenght=80 value='<%= rs.getString("nome")%>'>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="codestado" class="col-sm-1 control-label">C�d. do Estado:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" size="50"   required="true" name="estado_idestado" list="estados" maxlenght=10 value='<%= rs.getString("estado_idestado")%>'>
                            <datalist id="estados">

                                <!-- Cria itens do datalist (a ideia � a mesma para campos select) -->
                                <c:forEach items="${listaEstados}" var="estado">
                                    <option value="${estado.idestado}">${estado.nome}</option>
                                </c:forEach>

                            </datalist>   
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-1">
                            <button type="submit" class="btn btn-primary btn-block" name="enviar"  onclick="validaFormCidade()">Atualizar</button>
                            <a class="btn btn-primary btn-block" href="<%=request.getContextPath()%>/endereco/listarCidades.jsp" name="voltar" >Voltar</a>
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

        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>