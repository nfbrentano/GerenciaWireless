<%-- 
    Document   : incluir_bairro
    Created on : Jun 7, 2018, 2:16:47 PM
    Author     : natan
--%>
<%@ page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String idbairro = request.getParameter("idbairro");
    String nome = request.getParameter("nome");
    String cidade_idcidade = request.getParameter("cidade_idcidade");
    

    // Registrar o driver JDBC para PostgreSQL
    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
    // Conectar o banco
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
    // Statement para executar a query
    Statement st = conn.createStatement();

    //ResultSet rs;
    int i = st.executeUpdate("INSERT INTO bairro( idbairro, nome, cidade_idcidade, disponibilidade ) VALUES ('" + idbairro + "','" + nome + "','" + cidade_idcidade + "', true)");

    // Verificar se o insert foi bem sucedido
    if (i > 0) {
        response.sendRedirect("sucesso_registro_bairro.jsp");
    } else {
        response.sendRedirect("listarBairros.jsp");
    }
%>
