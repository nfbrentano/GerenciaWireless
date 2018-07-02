<%-- 
    Document   : editarCadastroRoteador
    Created on : Jun 14, 2018, 9:21:24 AM
    Author     : natan
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

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
        <title>Alterar Cadastro de Roteador</title>
    </head>
    <body onload="document.formTeste.nome.focus()">
        <%
            try {

                // Registrar o driver JDBC para PostgreSQL
                Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
                // Conectar o banco
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
                // Statement para executar os comandos sql
                Statement st = conn.createStatement();

                String query = "SELECT idpontoacesso, ssid, modelo, largurabanda, frequencia, iproteador, usuario, pass FROM pontoacesso WHERE disponibilidade = true AND idpontoacesso = " + request.getParameter("cod");
                ResultSet rs = st.executeQuery(query);

                rs.next();

        %>                 

        <%@ include file = "nav.jsp"%>     

        <div class="container">
            <form class="form-horizontal" role="form"  method="post" action="alterar_registro.jsp">


                <h2 class="card-title mt-3 text-center">Alterar Cadastro de Roteador</h2>

                <div class="form-group">
                    <label for="cod" class="col-sm-1 control-label">Código:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="idpontoacesso" readonly="true" value='<%= rs.getInt("idpontoacesso")%>'>
                    </div>   
                </div>
                <div class="form-group">
                    <label for="ssid" class="col-sm-1 control-label">SSID:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="ssid" maxlength="45" size="50" value='<%= rs.getString("ssid")%>'>
                    </div>
                </div>
                <div class="form-group">
                    <label  for="modelo" class="col-sm-1 control-label">Modelo:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="modelo" maxlength="45" size="50" value='<%= rs.getString("modelo")%>'>
                    </div>
                </div>
                <div class="form-group">
                    <label for="largurabanda" class="col-sm-1 control-label">Banda Mhz:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" onkeyup="somenteNumeros(this);" required="true" name="largurabanda" maxlength="14" size="50" value='<%= rs.getString("largurabanda")%>'>
                    </div>
                </div>

                <div class="form-group">
                    <label for="frequencia" class="col-sm-1 control-label">Frequência:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" onkeyup="somenteNumeros(this);" required="true" name="frequencia" maxlength="14" size="50" value='<%= rs.getString("frequencia")%>'>
                    </div>
                </div>   

                <div class="form-group">
                    <label  for="iproteador" class="col-sm-1 control-label">IP:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="iproteador" maxlength="15" size="50" value='<%= rs.getString("iproteador")%>'>
                    </div>
                </div>

                <div class="form-group">
                    <label  for="usuario" class="col-sm-1 control-label">Usuário:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="usuario" maxlength="20" size="50" value='<%= rs.getString("usuario")%>'>
                    </div>
                </div>

                <div class="form-group">
                    <label  for="pass" class="col-sm-1 control-label">Senha:</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" required="true" name="pass" maxlength="25" size="50" value='<%= rs.getString("pass")%>'>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-1">
                        <button type="submit" class="btn btn-primary btn-block" name="enviar"  onclick="validaFormRoteador()">Atualizar</button>
                        <button type="reset" class="btn btn-primary btn-block" name="limpar" >Limpar</button>
                        <a class="btn btn-primary btn-block" href="<%=request.getContextPath()%>/conexao/listarRoteador.jsp" name="voltar" >Voltar</a>

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
