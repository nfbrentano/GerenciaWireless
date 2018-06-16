
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Natanael
 */
public class ExcluirPessoa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idcadastropessoa = request.getParameter("cod");

        try {
            // Registrar o driver JDBC para PostgreSQL
            Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
            // Conectar o banco
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
            // Statement para executar os comandos sql
            Statement st = conn.createStatement();

            // SQL para excluir
            String query = "UPDATE cadastropessoa SET disponibilidade = false WHERE idcadastropessoa = " + idcadastropessoa;

            // Executa o SQL de exclusão
            st.execute(query);

            // Encaminha para a listagem 
            request.getRequestDispatcher("/cadastroPessoa/listarCadastroPessoa.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().println("<script>alert('Ocorreu um erro ao excluir o usuário: '" + e.getMessage() + ")</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para excluir cadastropessoa";
    }

}
