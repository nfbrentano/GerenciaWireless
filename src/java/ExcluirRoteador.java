
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
 * @author natan
 */
public class ExcluirRoteador extends HttpServlet {

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
        String idpontoacesso = request.getParameter("cod");

        try {
            // Usar o utilitário de conexão padronizado
            Connection conn = util.Db.getConexao();

            // SQL para excluir
            String query = "UPDATE pontoacesso SET disponibilidade = false WHERE idpontoacesso = ?";

            // Executa o SQL de exclusão
            try (java.sql.PreparedStatement st = conn.prepareStatement(query)) {
                st.setInt(1, Integer.parseInt(idpontoacesso));
                st.executeUpdate();
            }


            // Encaminha para a listagem 
            request.getRequestDispatcher("/conexao/listarRoteador.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().println("<script>alert('Ocorreu um erro ao excluir o roteador: '" + e.getMessage() + ")</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para excluir roteadores";
    }
}
