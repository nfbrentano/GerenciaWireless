
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
public class ExcluirPais extends HttpServlet {

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
        String cod = request.getParameter("cod");

        if (cod != null) {
            try {
                service.PaisService service = new service.PaisService();
                service.excluir(Integer.parseInt(cod));

                request.getRequestDispatcher("/endereco/listarPaises.jsp").forward(request, response);
            } catch (Exception e) {
                response.getWriter().println("<script>alert('Ocorreu um erro ao excluir o país: " + e.getMessage() + "'); window.location.href='endereco/listarPaises.jsp';</script>");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/endereco/listarPaises.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para excluir paises";
    }

}
