
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
public class ExcluirBairro extends HttpServlet {

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
        String idbairro = request.getParameter("cod");

        if (idbairro != null) {
            try {
                service.BairroService service = new service.BairroService();
                service.excluir(Integer.parseInt(idbairro));

                request.getRequestDispatcher("/endereco/listarBairros.jsp").forward(request, response);
            } catch (Exception e) {
                response.getWriter().println("<script>alert('Ocorreu um erro ao excluir o bairro: " + e.getMessage() + "'); window.location.href='endereco/listarBairros.jsp';</script>");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/endereco/listarBairros.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para excluir bairros";
    }

}
