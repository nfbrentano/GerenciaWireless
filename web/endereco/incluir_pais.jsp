<%-- 
    Document   : incluir_pais
    Created on : Jun 7, 2018, 3:20:27 PM
    Author     : natan
--%>

<%@ page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String idpais = request.getParameter("idpais");
    String nome = request.getParameter("nome");

    // Registrar o driver JDBC para PostgreSQL
    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
    // Conectar o banco
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
    // Statement para executar a query
    Statement st = conn.createStatement();

    //ResultSet rs;
    int i = st.executeUpdate("INSERT INTO pais(idpais, nome, disponibilidade ) VALUES ('" + idpais + "','" + nome + "', true)");

    // Verificar se o insert foi bem sucedido
    if (i > 0) {
        response.sendRedirect("sucesso_registro_pais.jsp");
    } else {
        response.sendRedirect("listarPaises.jsp");
    }
%>
