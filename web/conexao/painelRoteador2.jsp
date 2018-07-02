<%-- 
    Document   : painelRoteador
    Created on : Jun 15, 2018, 1:29:24 PM
    Author     : natan
--%>

<%@page import="com.github.komcrad.snmp.VarbindCollection"%>
<%@page import="com.github.komcrad.snmp.SnmpFactory"%>
<%@page import="com.github.komcrad.snmp.SnmpContext"%>
<%@page import="com.github.komcrad.snmp.SimpleSnmpV2cTarget"%>
<%@page import="com.github.komcrad.snmp.MibFactory"%>
<%@page import="com.github.komcrad.snmp.Mib"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="me.legrange.mikrotik.ApiConnection"%>
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


        <%@ include file = "nav.jsp"%> 

        <div class="container">
            <form class="form-horizontal" role="form"  method="post" a>

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

                <%
                    try {

                        // Registrar o driver JDBC para PostgreSQL
                        Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
                        // Conectar o banco
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
                        // Statement para executar os comandos sql
                        Statement st = conn.createStatement();

                        String query = "SELECT * FROM pontoacesso WHERE disponibilidade = true AND ssid = 'Analise'";
                        ResultSet rs = st.executeQuery(query);

                        rs.next();
                        {
                            String iproteador = rs.getString("iproteador");
                            String user = rs.getString("usuario");
                            String pass = rs.getString("pass");
                            System.out.println(iproteador);

                            Mib mib = MibFactory.getInstance().newMib();
                            mib.load("SNMPv2-MIB");

                            SimpleSnmpV2cTarget target = new SimpleSnmpV2cTarget();
                            target.setAddress(System.getProperty("tnm4j.agent.address", "172.27.0.3"));
                            target.setCommunity(System.getProperty("tnm4j.agent.community", "public"));

                            SnmpContext context = SnmpFactory.getInstance().newContext(target, mib);
                            System.out.println("ok, acabou");
                            try {
                                VarbindCollection result = context.getNext("sysUpTime").get();
                                System.out.println(result.get("sysUpTime"));

                            } finally {
                                context.close();
                            }
                            System.out.println("Fim do while");
                        }


                %>       

            </form>
        </div>



        <%            } catch (Exception e) {
                out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
            }
        %>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
