<%-- 
    Document   : alterar_registro
    Created on : Jun 15, 2018, 7:48:31 PM
    Author     : natan
--%>
<%@page import="me.legrange.mikrotik.MikrotikApiException"%>
<%@page import="java.util.Map"%>
<%@page import="me.legrange.mikrotik.ResultListener"%>
<%@page import="me.legrange.mikrotik.ApiConnection"%>
<%@page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String idpontoacesso = request.getParameter("idpontoacesso");
    String roteador = request.getParameter("ssid");
    String modelo = request.getParameter("modelo");
    String largurabanda = request.getParameter("largurabanda");
    String frequencia = request.getParameter("frequencia");
    String iproteador = request.getParameter("iproteador");
    String usuario = request.getParameter("usuario");
    String pass = request.getParameter("pass");

    try {
        ApiConnection con = ApiConnection.connect("'" + iproteador + "'"); // connect to router

        con.login("'" + usuario + "'", "'" + pass + "'"); // log in to router
        System.out.println("SSID");
        con.execute("/interface/wireless/set ssid='" + roteador + "' numbers=0 comment='test comment'");
        Thread.sleep(1000);
        System.out.println("Frequencia");
        con.execute("/interface/wireless/set frequency='" + frequencia + "' numbers=0 ");
        con.close();
    } catch (Exception e) {
        out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
        out.print(" <br> <a href='listarRoteador.jsp'>Voltar.</a>");

    }
    try {
        // Registrar o driver JDBC para PostgreSQL
        Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
        // Conectar o banco
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
        // Statement para executar a query
        Statement st = conn.createStatement();

        //ResultSet rs;
        int i = st.executeUpdate("UPDATE pontoacesso SET ssid = '" + roteador + "', modelo = '" + modelo + "', largurabanda = '" + largurabanda + "', frequencia = '" + frequencia + "', iproteador = '" + iproteador + "', usuario = '" + usuario + "', pass = '" + pass + "' WHERE idpontoacesso =  '" + idpontoacesso + "'");

        // Verificar se o update foi bem sucedido
        if (i > 0) {
            response.sendRedirect("sucesso_registro.jsp");

        } else {
            response.sendRedirect("sucesso_registro.jsp");
        }

    } catch (Exception e) {
        out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
        out.print(" <br> <a href='listarRoteador.jsp'>Voltar.</a>");

    }
%>
