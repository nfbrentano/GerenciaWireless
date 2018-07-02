<%-- 
    Document   : editarCadastroPessoa
    Created on : Mar 25, 2018, 8:26:20 PM
    Author     : natan
--%>
<%@page import="dao.RoteadorDao"%>
<!-- Import das classes necessárias -->
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="dao.CadastroPessoaDao"%>
<%@page import="dao.EnderecoDao"%>
<%@page import="dao.PaisDao"%>
<%@page import="dao.EstadoDao"%>
<%@page import="dao.CidadeDao"%>
<%@page import="dao.BairroDao"%>
<%@page import="model.Pais"%>
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
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/somenteNumeros.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/validaForm.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
        <title>Alterar Cadastro de Usuário</title>
    </head>
    <body onload="document.formTeste.nome.focus()" >



        <%
            // Instancia Objeto de Acesso aos Dados (DAO)
            RoteadorDao roteador = new RoteadorDao();
            // Buscar dados no banco
            request.setAttribute("listaRoteadores", roteador.getAllRoteadores());
        %>
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

                String query = "SELECT idcadastroPessoa, nome, sobrenome, documento, pais, estado, cidade, bairro, endereco, numeroendereco, nomeusuario, senhaacesso FROM cadastroPessoa WHERE disponibilidade = true AND idcadastroPessoa = " + request.getParameter("cod");
                ResultSet rs = st.executeQuery(query);

                rs.next();
        %>            


        <%@ include file = "nav.jsp"%>      

        <div class="container">
            <form class="form-horizontal" role="form"  method="post" action="alterar_registro.jsp">


                <h2 class="card-title mt-3 text-center">Alterar Cadastro de Usuários</h2>

                <div class="form-group">
                    <label for="cod" class="col-sm-1 control-label">Código:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="idcadastroPessoa" readonly="true" value='<%= rs.getInt("idcadastroPessoa")%>'>
                    </div>   
                </div>
                <div class="form-group">
                    <label for="nome" class="col-sm-1 control-label">Nome:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="nome" maxlength="45" size="50" value='<%= rs.getString("nome")%>'>
                    </div>
                </div>
                <div class="form-group">
                    <label  for="sobrenome" class="col-sm-1 control-label">Sobrenome:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="sobrenome" maxlength="45" size="50" value='<%= rs.getString("sobrenome")%>'>
                    </div>
                </div>
                <div class="form-group">
                    <label for="documento" class="col-sm-1 control-label">Documento:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" onkeyup="somenteNumeros(this);" required="true" name="documento" maxlength="14" size="50" value='<%= rs.getString("documento")%>'>
                    </div>
                </div>
                <div class="form-group">
                    <label for="pais" class="col-sm-1 control-label">País:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" list="paises" maxlength="45" name="pais" size="50" value='<%= rs.getString("pais")%>'>
                        <datalist id="paises">
                            <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                            <c:forEach items="${listaPaises}" var="pais">
                                <option value="${pais.nome}">${pais.nome}</option>
                            </c:forEach>
                        </datalist>
                    </div>
                </div>
                <div class="form-group">
                    <label for="estado" class="col-sm-1 control-label">Estado:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" list="estados" name="estado" maxlength="45" size="50" required value='<%= rs.getString("estado")%>'>
                        <datalist id="estados">
                            <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                            <c:forEach items="${listaEstados}" var="estado">
                                <option value="${estado.nome}">${estado.nome}</option>
                            </c:forEach>
                        </datalist>   
                    </div>
                </div>
                <div class="form-group">
                    <label for="cidade" class="col-sm-1 control-label">Cidade:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" list="cidades" name="cidade" maxlength="60" size="50" required value='<%= rs.getString("cidade")%>'>
                        <datalist id="cidades">
                            <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                            <c:forEach items="${listaCidades}" var="cidade">
                                <option value="${cidade.nome}">${cidade.nome}</option>
                            </c:forEach>
                        </datalist>
                    </div>
                </div>
                <div class="form-group">
                    <label for="bairro" class="col-sm-1 control-label">Bairro:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" list="bairros" name="bairro" maxlength="45" size="50" required value='<%= rs.getString("bairro")%>'>
                        <datalist id="bairros">
                            <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                            <c:forEach items="${listaBairros}" var="bairro">
                                <option value="${bairro.nome}">${bairro.nome}</option>
                            </c:forEach>
                        </datalist> 
                    </div>
                </div>
                <div class="form-group">
                    <label for="rua" class="col-sm-1 control-label">Rua:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="endereco" maxlength="60" list="enderecos" size="50" required value='<%= rs.getString("endereco")%>'>
                        <datalist id="enderecos">

                            <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                            <c:forEach items="${listaEnderecos}" var="endereco">
                                <option value="${endereco.rua}">${endereco.rua}</option>
                            </c:forEach>
                        </datalist> 
                    </div>
                </div>
                <div class="form-group">
                    <label for="numero" class="col-sm-1 control-label">Número:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" onkeyup="somenteNumeros(this);" required="true" name="numeroendereco" maxlength="14" size="50" required value='<%= rs.getString("numeroendereco")%>'>
                    </div>
                </div>
                <div class="form-group">
                    <label for="usuario" class="col-sm-1 control-label">Usuário:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text"  name="nomeusuario" maxlength="32" value='<%= rs.getString("nomeusuario")%>'>
                    </div>
                </div>
                <div class="form-group">
                    <label for="senha" class="col-sm-1 control-label">Senha: </label>
                    <div class= "col-sm-9" >
                        <input class="form-control" type="text" name="senhaacesso" maxlength="32" size="50" value='<%= rs.getString("senhaacesso")%>'>
                    </div>
                </div>
                <div class="form-group">
                    <label for="pontoacesso" class="col-sm-1 control-label">Roteador: </label>
                    <div class="col-sm-9">
                        <select class="form-control" type="text" required="true" list="listaRoteadores" name="pontoacesso" >
                            <datalist id="roteador">
                                <!-- Cria itens do datalist (a ideia é a mesma para campos select) -->
                                <c:forEach items="${listaRoteadores}" var="roteador">
                                    <option value="${roteador.ssid}">${roteador.ssid}</option>
                                </c:forEach>
                            </datalist>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-1">
                        <button type="submit" class="btn btn-primary btn-block" name="enviar"  onclick="validaFormCadastroPessoa()">Atualizar</button>
                        <button type="reset" class="btn btn-primary btn-block" name="limpar" >Limpar</button>
                        <a class="btn btn-primary btn-block" href="<%=request.getContextPath()%>/cadastroPessoa/listarCadastroPessoa.jsp" name="voltar" >Voltar</a>

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