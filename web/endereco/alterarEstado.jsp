<%-- 
    Document   : alterarEstado
    Created on : Jun 7, 2018, 9:32:45 AM
    Author     : natan
--%>

<%@ page import ="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String idestado = request.getParameter("idestado");
    String sigla = request.getParameter("sigla");
    String nome = request.getParameter("nome");
    String pais_idpais = request.getParameter("pais_idpais");

    try {
        // Registrar o driver JDBC para PostgreSQL
        Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
        // Conectar o banco
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
        // Statement para executar a query
        Statement st = conn.createStatement();

        //ResultSet rs;
        int i = st.executeUpdate("UPDATE estado SET idestado = '" + idestado + "', sigla = '" + sigla + "', nome = '" + nome + "', pais_idpais = '" + pais_idpais + "' WHERE idestado =  '" + idestado + "'");

        // Verificar se o update foi bem sucedido
        if (i > 0) {

           response.sendRedirect("sucesso_registro_estado.jsp");
        } else {
            out.print("<h1>Não Alterado</h1>O Registro Não foi alterado!");
        }

        out.print("<br> <a href='listarEstados.jsp'>Voltar.</a>");

    } catch (Exception e) {
        out.write("Ocorreu um erro ao buscar o registro: <span style='color: red'>" + e.getMessage() + "</span>");
        out.print(" <br> <a href='listarEstados.jsp'>Voltar.</a>");

    }
%>