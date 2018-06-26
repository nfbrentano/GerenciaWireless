<%-- 
    Document   : painelRoteador
    Created on : Jun 15, 2018, 1:29:24 PM
    Author     : natan
--%>

<%@page import="org.snmp4j.Snmp"%>
<%@page import="org.snmp4j.transport.DefaultUdpTransportMapping"%>
<%@page import="org.snmp4j.TransportMapping"%>
<%@page import="dao.RoteadorDao"%>
<%@page import="model.Roteador"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
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
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/somenteNumeros.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/JS/validaForm.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
        <title>Painel de Roteador</title>
    </head>
    <body onload="document.formTeste.nome.focus()">

        <%
            // Instancia Objeto de Acesso aos Dados (DAO)
            RoteadorDao roteador = new RoteadorDao();
            // Buscar dados no banco
            request.setAttribute("listaRoteadores", roteador.getAllRoteadores());
        %>
        <%
            try {

                // Registrar o driver JDBC para PostgreSQL
                Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
                // Conectar o banco
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
                // Statement para executar os comandos sql
                Statement st = conn.createStatement();

                String query = "SELECT idpontoacesso, ssid, modelo, largurabanda, frequencia FROM pontoacesso WHERE disponibilidade = true AND idpontoacesso = " + request.getParameter("cod");
                ResultSet rs = st.executeQuery(query);

                rs.next();
        %>       


        <%@ include file = "nav.jsp"%> 

        <div class="container">
            <form class="form-horizontal" role="form"  method="post" action="alterar_registro.jsp">

                <h2 class="card-title mt-3 text-center">Painel de Roteador</h2>

                <div class="form-group">
                    <label for="roteador" class="col-sm-1 control-label">Roteador: </label>
                    <div class="col-sm-9">
                        <select class="form-control" type="text" required="true" list="listaRoteadores" name="roteador" >
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
                    <label for="teste" class="col-sm-1 control-label">teste </label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text"  required="true" name="teste" maxlength="14" size="50" value='<%= rs.getString("largurabanda")%>'>
                    </div>
                </div>

     
            </form>
        </div>


        <%            }
            catch (Exception e

            
                ) {
                out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
            }
        %>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
