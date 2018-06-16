<%-- 
    Document   : editarEndereco
    Created on : Jun 7, 2018, 10:35:52 AM
    Author     : natan
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="dao.EnderecoDao"%>
<%@page import="dao.BairroDao"%>
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
        <title>Alterar Rua</title>

    </head>

    <body>

        <div class="main-content">

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

                    String query = "SELECT idendereco, rua, bairro_idbairro FROM endereco WHERE idendereco = " + request.getParameter("cod");
                    ResultSet rs = st.executeQuery(query);

                    rs.next();
            %>                       

            <%@ include file = "nav.jsp"%>

            <div class="container">
                <form data-abide="ajax" id="order_form" class="form-horizontal" role="form" method="post" action="alterarEndereco.jsp">

                    <h2 class="card-title mt-3 text-center">Alterar Rua</h2>

                    <div class="form-group">
                        <label for="cod" class="col-sm-1 control-label">Código:  </label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" size="50"  required="true" name="idendereco" readonly="true" value='<%= rs.getInt("idendereco")%>'>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rua" class="col-sm-1 control-label">Rua:</label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" size="50"  required="true" name="rua" maxlenght=80 value='<%= rs.getString("rua")%>'>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="codbairro" class="col-sm-1 control-label" >Cód do Bairro:  </label>
                        <div class="col-sm-9">
                            <input class="form-control" type="text" size="50"  required="true" list="bairro_idbairro" name="bairro_idbairro" maxlenght="50"  required value='<%= rs.getString("bairro_idbairro")%>'>
                            <datalist id="bairro_idbairro">
                                <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                                <c:forEach items="${listaBairros}" var="bairro_idbairro">
                                    <option value="${bairro_idbairro.idbairro}">${bairro_idbairro.nome}</option>
                                </c:forEach>

                            </datalist> 
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-1">
                            <button type="submit" class="btn btn-primary btn-block" name="enviar"  onclick="validaFormEndereco()">Atualizar</button>
                            <a class="btn btn-primary btn-block" href="<%=request.getContextPath()%>/endereco/listarEnderecos.jsp" name="voltar" >Voltar</a>
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