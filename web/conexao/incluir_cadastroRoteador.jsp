<%-- 
    Document   : incluir_cadastroRoteador
    Created on : Jun 14, 2018, 9:22:02 AM
    Author     : natan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@page import="me.legrange.mikrotik.ApiConnection"%>
<%

    String ssid = request.getParameter("ssid");
    String modelo = request.getParameter("modelo");
    String largurabanda = request.getParameter("largurabanda");
    String frequencia = request.getParameter("frequencia");
    String iproteador = request.getParameter("iproteador");
    String usuario = request.getParameter("usuario");
    String pass = request.getParameter("pass");

    // Registrar o driver JDBC para PostgreSQL
    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
    // Conectar o banco
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
    // Statement para executar a query
    Statement st = conn.createStatement();
    try {
        ApiConnection con = ApiConnection.connect("" + iproteador + ""); // connect
        con.login("user1", ""); // log in to router
        con.execute("/interface/wireless/set ssid ='" + ssid + "'  numbers=0");
        Thread.sleep(2000);
        con.execute("/interface/wireless/set frequency='" + frequencia + "' numbers=0");
        Thread.sleep(2000);
        con.execute("/user/set name='" + usuario + "' numbers=user1");
        Thread.sleep(2000);
        con.execute("/user/set password='" + pass + "' numbers='" + usuario + "'");
        //ResultSet rs;
        int i = st.executeUpdate("INSERT INTO pontoacesso (ssid, modelo, largurabanda, frequencia, iproteador, usuario, pass, disponibilidade ) VALUES ('" + ssid + "','" + modelo + "','" + largurabanda + "','" + frequencia + "','" + iproteador + "','" + usuario + "','" + pass + "', true)");
        // Verificar se o insert foi bem sucedido
        if (i > 0) {
            response.sendRedirect("sucesso_registro.jsp");
        } else {
            response.sendRedirect("listarRoteador.jsp");
        }
    } catch (Exception e) {
        out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
        out.print(" <br> <a href='listarRoteador.jsp'>Voltar.</a>");

    }
%>
