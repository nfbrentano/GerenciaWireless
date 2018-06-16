<%-- 
    Document   : alterar_registro
    Created on : Mar 25, 2018, 8:15:20 PM
    Author     : natan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%
    String idcadastroPessoa = request.getParameter("idcadastroPessoa");
    String nome = request.getParameter("nome");
    String sobrenome = request.getParameter("sobrenome");
    String documento = request.getParameter("documento");
    String pais = request.getParameter("pais");
    String estado = request.getParameter("estado");
    String cidade = request.getParameter("cidade");
    String bairro = request.getParameter("bairro");
    String endereco = request.getParameter("endereco");
    String numeroendereco = request.getParameter("numeroendereco");
    String nomeusuario = request.getParameter("nomeusuario");
    String senhaacesso = request.getParameter("senhaacesso");
    String pontoacesso = request.getParameter("pontoacesso");

    try {
        // Registrar o driver JDBC para PostgreSQL
        Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
        // Conectar o banco
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
        // Statement para executar a query
        Statement st = conn.createStatement();

        //ResultSet rs;
        int i = st.executeUpdate("UPDATE cadastropessoa SET nome = '" + nome + "', sobrenome = '" + sobrenome + "', documento = '" + documento + "', pais = '" + pais + "', estado = '" + estado + "', cidade = '" + cidade + "', bairro = '" + bairro + "', endereco = '" + endereco + "', numeroendereco = '" + numeroendereco + "', nomeusuario = '" + nomeusuario + "', senhaacesso = '" + senhaacesso + "', pontoacesso = '" + pontoacesso + "' WHERE idcadastroPessoa =  '" + idcadastroPessoa + "'");

        // Verificar se o update foi bem sucedido
        if (i > 0) {
            response.sendRedirect("sucesso_registro.jsp");

        } else {
            response.sendRedirect("sucesso_registro.jsp");
        }

    } catch (Exception e) {
        out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
        out.print(" <br> <a href='listarCadastroPessoa.jsp'>Voltar.</a>");

    }
%>
