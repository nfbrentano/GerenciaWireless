<%-- 
    Document   : incluir_endereco
    Created on : Jun 7, 2018, 2:39:01 PM
    Author     : natan
--%>


<%@ page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    String idendereco = request.getParameter("idendereco");
    String rua = request.getParameter("rua");
    String bairro_idbairro = request.getParameter("bairro_idbairro");

    // Registrar o driver JDBC para PostgreSQL
    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
    // Conectar o banco
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
    // Statement para executar a query
    Statement st = conn.createStatement();

    //ResultSet rs;
    int i = st.executeUpdate("INSERT INTO endereco( idendereco, rua, bairro_idbairro, disponibilidade ) VALUES ('" + idendereco + "','" + rua + "','" + bairro_idbairro + "', true)");

    // Verificar se o insert foi bem sucedido
    if (i > 0) {
        response.sendRedirect("sucesso_registro_endereco.jsp");
    } else {
        response.sendRedirect("listarEnderecos.jsp");
    }
%>
