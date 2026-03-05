
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
public class ExcluirEstado extends HttpServlet {

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
        String idestado = request.getParameter("cod");

        if (idestado != null) {
            try {
                service.EstadoService service = new service.EstadoService();
                service.excluir(Integer.parseInt(idestado));

                request.getRequestDispatcher("/endereco/listarEstados.jsp").forward(request, response);
            } catch (Exception e) {
                response.getWriter().println("<script>alert('Ocorreu um erro ao excluir o estado: " + e.getMessage() + "'); window.location.href='endereco/listarEstados.jsp';</script>");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/endereco/listarEstados.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para excluir estados";
    }

}
