<%-- 
    Document   : incluir_cidade
    Created on : Jun 7, 2018, 2:24:51 PM
    Author     : natan
--%>

<%@ page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String idcidade = request.getParameter("idcidade");
    String nome = request.getParameter("nome");
    String estado_idestado = request.getParameter("estado_idestado");
    

    // Registrar o driver JDBC para PostgreSQL
    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
    // Conectar o banco
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
    // Statement para executar a query
    Statement st = conn.createStatement();

    //ResultSet rs;
    int i = st.executeUpdate("INSERT INTO cidade( idcidade, nome, estado_idestado, disponibilidade ) VALUES ('" + idcidade + "','" + nome + "','" + estado_idestado + "', true)");

    // Verificar se o insert foi bem sucedido
    if (i > 0) {
        response.sendRedirect("sucesso_registro_cidade.jsp");
    } else {
        response.sendRedirect("listarCidades.jsp");
    }
%>
