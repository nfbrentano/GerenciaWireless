<%-- 
    Document   : incluir_cadastroPessoa
    Created on : Mar 25, 2018, 8:40:05 PM
    Author     : natan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%

    String nome = request.getParameter("nome");
    String sobrenome = request.getParameter("sobrenome");
    String documento = request.getParameter("documento");
    String pais = request.getParameter("pais");
    String estado = request.getParameter("estado");
    String cidade = request.getParameter("cidade");
    String bairro = request.getParameter("bairro");
    String endereco = request.getParameter("endereco");
    String numeroendereco = request.getParameter("numeroendereco");
    String nomeusuario = request.getParameter("usuario");
    String senhaacesso = request.getParameter("senha");
    String pontoacesso = request.getParameter("pontoacesso");

    // Registrar o driver JDBC para PostgreSQL
    Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
    // Conectar o banco
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
    // Statement para executar a query
    Statement st = conn.createStatement();

    //ResultSet rs;
    int i = st.executeUpdate("INSERT INTO cadastroPessoa (nome, sobrenome, documento, pais, estado, cidade, bairro, endereco, numeroendereco, nomeusuario, senhaacesso, pontoacesso, disponibilidade ) VALUES ('" + nome + "','" + sobrenome + "','" + documento + "','" + pais + "','" + estado + "','" + cidade + "','" + bairro + "','" + endereco + "','" + numeroendereco + "','" + nomeusuario + "','" + senhaacesso + "','" + pontoacesso + "', true)");
    // Verificar se o insert foi bem sucedido
    if (i > 0) {
        response.sendRedirect("sucesso_registro.jsp");
    } else {
        response.sendRedirect("listarCadastroPessoa.jsp");
    }
%>
