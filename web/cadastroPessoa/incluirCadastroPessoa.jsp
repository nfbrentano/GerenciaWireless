<%-- 
    Document   : incluirCadastroPessoa
    Created on : Mar 25, 2018, 8:59:37 PM
    Author     : natan
--%>
<!-- Import das classes necess�rias -->
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="dao.EnderecoDao"%>
<%@page import="dao.PaisDao"%>
<%@page import="dao.EstadoDao"%>
<%@page import="dao.CidadeDao"%>
<%@page import="dao.BairroDao"%>
<%@page import="model.CadastroPessoa"%>
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
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/somenteNumeros.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/validaForm.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
        <title>Cadastro de Usu�rios</title>
    </head>

    <body >


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

                String query = "SELECT max(idcadastroPessoa+1) FROM cadastroPessoa";
                ResultSet rs = st.executeQuery(query);

                rs.next();
        %>    


        <%@ include file = "nav.jsp"%>     
        <div class="container">
            <form  class="form-horizontal" role="form" name="formTeste"  class="form-register1" onsubmit="return validaForm()" method="post" action="incluir_cadastroPessoa.jsp">

                <h2 class="card-title mt-3 text-center">Cadastro de Usu�rios</h2>

                <div class="form-group">
                    <label for="cod" class="col-sm-1 control-label">C�digo:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text"  id="cod" name="idcadastroPessoa" maxlength="10" size="50" readonly="true" value='<%= rs.getString("max")%>'>
                    </div> 
                </div>
                <div class="form-group">

                    <label for="nome" class="col-sm-1 control-label">Nome: </label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="nome" maxlength="45" size="50" >
                    </div>
                </div>
                <div class="form-group">
                    <label  for="sobrenome" class="col-sm-1 control-label">Sobrenome:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="sobrenome" maxlength="45" size="50" >
                    </div>
                </div>

                <div class="form-group">
                    <label for="documento" class="col-sm-1 control-label">Documento: </label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" onkeyup="somenteNumeros(this);"  name="documento" maxlength="14" size="50">
                    </div>
                </div>

                <div class="form-group">
                    <label for="pais" class="col-sm-1 control-label">Pa�s: </label>
                    <div class="col-sm-9">
                        <select class="form-control" type="text" required="true" list="paises" name="pais" >
                            <datalist id="paises">
                                <!-- Cria itens do datalist (a ideia � a mesma para campos select) -->
                                <c:forEach items="${listaPaises}" var="pais">
                                    <option value="${pais.nome}">${pais.nome}</option>
                                </c:forEach>

                            </datalist>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="estado" class="col-sm-1 control-label">Estado:</label>
                    <div class="col-sm-9">
                        <select class="form-control" type="text" required="true" list="estados" name="estado">
                            <datalist id="estados">

                                <!-- Cria itens do datalist (a ideia � a mesma para campos select) -->
                                <c:forEach items="${listaEstados}" var="estado">
                                    <option value="${estado.nome}">${estado.nome}</option>
                                </c:forEach>

                            </datalist> 
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cidade" class="col-sm-1 control-label">Cidade: </label>
                    <div class="col-sm-9">
                        <select class="form-control" type="text" required="true" list="cidades" name="cidade">
                            <datalist id="cidades">

                                <!-- Cria itens do datalist (a ideia � a mesma para campos select) -->
                                <c:forEach items="${listaCidades}" var="cidade">
                                    <option value="${cidade.nome}">${cidade.nome}</option>
                                </c:forEach>

                            </datalist>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="bairro" class="col-sm-1 control-label">Bairro: </label>
                    <div class="col-sm-9">
                        <select class="form-control" type="text" required="true" list="bairros" name="bairro" >
                            <datalist id="bairros">

                                <!-- Cria itens do datalist (a ideia � a mesma para campos select) -->
                                <c:forEach items="${listaBairros}" var="bairro">
                                    <option value="${bairro.nome}">${bairro.nome}</option>
                                </c:forEach>

                            </datalist> 
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rua" class="col-sm-1 control-label">Rua: </label>
                    <div class="col-sm-9">
                        <select class="form-control" type="text" required="true" list="enderecos" name="endereco" >
                            <datalist id="enderecos">

                                <!-- Cria itens do datalist (a ideia � a mesma para campos select) -->
                                <c:forEach items="${listaEnderecos}" var="endereco">
                                    <option value="${endereco.rua}">${endereco.rua}</option>
                                </c:forEach>

                            </datalist> 
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="numero" class="col-sm-1 control-label">N�mero: </label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" onkeyup="somenteNumeros(this);" name="numeroendereco" maxlength="20" size="50" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="usuario" class="col-sm-1 control-label">Usu�rio: </label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" maxlength="32" name="usuario" size="50" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="senha" class="col-sm-1 control-label">Senha:</label>
                    <div class= "col-sm-9" >
                        <input class="form-control" type="text"  name="senha" maxlength="32"  >                            
                    </div>
                </div>
                <div class="form-group">
                    <label for="pontoacesso" class="col-sm-1 control-label">Roteador: </label>
                    <div class="col-sm-9">
                        <select class="form-control" name="pontoacesso" list="cidades">
                            <datalist id="cidades">

                                <!-- Cria itens do datalist (a ideia � a mesma para campos select) -->
                                <c:forEach items="${listaCidades}" var="cidade">
                                    <option value="${cidade.nome}">${cidade.nome}</option>
                                </c:forEach>

                            </datalist>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-1">
                        <button type="submit" class="btn btn-primary btn-block" name="enviar"  onclick="validaFormCadastroPessoa()">Registrar</button>
                        <button type="reset" class="btn btn-primary btn-block" name="limpar" >Limpar</button>
                        <a class="btn btn-primary btn-block" name="voltar" type="submit" href="<%=request.getContextPath()%>/cadastroPessoa/listarCadastroPessoa.jsp" > Voltar </a>
                    </div>
                </div>

            </form>
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