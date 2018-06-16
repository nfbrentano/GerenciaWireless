<%-- 
    Document   : alterarEndereco
    Created on : Jun 7, 2018, 9:30:12 AM
    Author     : natan
--%>

<%@ page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String idendereco = request.getParameter("idendereco");
    String nome = request.getParameter("rua");
    String bairro_idbairro = request.getParameter("bairro_idbairro");

    try {
        // Registrar o driver JDBC para PostgreSQL
        Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
        // Conectar o banco
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
        // Statement para executar a query
        Statement st = conn.createStatement();

        //ResultSet rs;
        int i = st.executeUpdate("UPDATE endereco SET idendereco = '" + idendereco + "', rua = '" + nome + "', bairro_idbairro = '" + bairro_idbairro + "' WHERE idendereco =  '" + idendereco + "'");

        // Verificar se o update foi bem sucedido
        if (i > 0) {

            response.sendRedirect("sucesso_registro_endereco.jsp");
        } else {
            out.print("<h1>Não Alterado</h1>O Registro Não foi alterado!");
        }

        out.print("<br> <a href='listarBairros.jsp'>Voltar.</a>");

    } catch (Exception e) {
        out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
        out.print(" <br> <a href='listarEnderecos.jsp'>Voltar.</a>");

    }
%>