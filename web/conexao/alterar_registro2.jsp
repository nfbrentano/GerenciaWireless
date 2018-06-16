<%-- 
    Document   : alterar_registro
    Created on : Jun 14, 2018, 9:21:11 AM
    Author     : natan
--%>


<!DOCTYPE html>
<%@ page import ="java.sql.*" %>

<%
    String idpontoacesso = request.getParameter("idpontoacesso");
    String ssid = request.getParameter("ssid");
    String modelo = request.getParameter("modelo");
    String largurabanda = request.getParameter("largurabanda");
    String frequencia = request.getParameter("frequencia");
    String iproteador = request.getParameter("iproteador");
   
    try {
        // Registrar o driver JDBC para PostgreSQL
        Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
        // Conectar o banco
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
        // Statement para executar a query
        Statement st = conn.createStatement();
        
        
        //ResultSet rs;
        int i = st.executeUpdate("UPDATE pontoacesso SET ssid = '" + ssid + "', modelo = '" + modelo + "', largurabanda = '" + largurabanda + "', frequencia = '" + frequencia + "', iproteador = '" + iproteador + "' WHERE idpontoacesso =  '" + idpontoacesso + "'");

        // Verificar se o update foi bem sucedido
        if (i > 0) {
            response.sendRedirect("sucesso_registro.jsp");

        } else {

        }

    } catch (Exception e) {
        out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
        out.print(" <br> <a href='listarRoteador.jsp'>Voltar.</a>");

    }
%>
