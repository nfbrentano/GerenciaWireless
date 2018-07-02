<%-- 
    Document   : alterar_registro
    Created on : Jun 15, 2018, 7:48:31 PM
    Author     : natan
--%>
<%@page import="dao.RoteadorDao"%>
<%@page import="model.Roteador"%>
<%@page import="me.legrange.mikrotik.MikrotikApiException"%>
<%@page import="java.util.Map"%>
<%@page import="me.legrange.mikrotik.ResultListener"%>
<%@page import="me.legrange.mikrotik.ApiConnection"%>
<%@page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    // Registrar o driver JDBC para PostgreSQL
    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
    // Conectar o banco
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
    // Statement para executar os comandos sql
    Statement st = conn.createStatement();

    String idpontoacesso = request.getParameter("idpontoacesso");
    String ssid = request.getParameter("ssid");
    String modelo = request.getParameter("modelo");
    String largurabanda = request.getParameter("largurabanda");
    String frequencia = request.getParameter("frequencia");
    String iproteador = request.getParameter("iproteador");
    String usuario = request.getParameter("usuario");
    String pass = request.getParameter("pass");

    System.out.println("TRY");
    try {
        ResultSet rs = st.executeQuery("SELECT * FROM pontoacesso WHERE idpontoacesso= " + idpontoacesso + "");
        while (rs.next()) {
            String ip = rs.getString("iproteador");
            String user = rs.getString("usuario");
            String password = rs.getString("pass");
            System.out.println("Antes conectar mikrotik...");
            ApiConnection con = ApiConnection.connect("" + ip + ""); // connect
            con.login("" + user + "", "" + password + ""); // log in to router
            con.execute("/interface/wireless/set ssid ='" + ssid + "'  numbers=0");
             Thread.sleep(200); 
            System.out.println(frequencia);
            con.execute("/interface/wireless/set frequency='" + frequencia + "' numbers=0");
            System.out.println(user);
            System.out.println(usuario);
            Thread.sleep(200); 
            con.execute("/user/set name='" + usuario + "' numbers=" + user + "");
            System.out.println(ip);
            System.out.println(iproteador);
            String novoip = iproteador+"/24";
            System.out.println(novoip);
            Thread.sleep(200); 
         //   con.execute("ip/address/add address='"+novoip+"' interface=bridge1");
            System.out.println("depois conectar mikrotik");
        }
        //ResultSet rs;
        int i = st.executeUpdate("UPDATE pontoacesso SET ssid = '" + ssid + "', modelo = '" + modelo + "', largurabanda = '" + largurabanda + "', frequencia = '" + frequencia + "', iproteador = '" + iproteador + "', usuario = '" + usuario + "', pass = '" + pass + "' WHERE idpontoacesso =  '" + idpontoacesso + "'");

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