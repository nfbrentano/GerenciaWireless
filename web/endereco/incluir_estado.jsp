<%-- 
    Document   : incluir_estado
    Created on : Jun 7, 2018, 2:58:00 PM
    Author     : natan
--%>

<%@ page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String idestado = request.getParameter("idestado");
    String nome = request.getParameter("nome");
    String sigla = request.getParameter("sigla");
    String pais_idpais = request.getParameter("pais_idpais");

    // Registrar o driver JDBC para PostgreSQL
    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
    // Conectar o banco
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
    // Statement para executar a query
    Statement st = conn.createStatement();

    //ResultSet rs;
    int i = st.executeUpdate("INSERT INTO estado( idestado, nome, sigla, pais_idpais, disponibilidade ) VALUES ('" + idestado + "','" + nome + "','" + sigla + "','" + pais_idpais + "', true)");

    // Verificar se o insert foi bem sucedido
    if (i > 0) {
        response.sendRedirect("sucesso_registro_estado.jsp");
    } else {
        response.sendRedirect("listarEstados.jsp");
    }
%>
