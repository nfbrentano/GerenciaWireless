
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
public class ExcluirEndereco extends HttpServlet {

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
        String idendereco = request.getParameter("cod");

        if (idendereco != null) {
            try {
                service.EnderecoService service = new service.EnderecoService();
                service.excluir(Integer.parseInt(idendereco));

                request.getRequestDispatcher("/endereco/listarEnderecos.jsp").forward(request, response);
            } catch (Exception e) {
                response.getWriter().println("<script>alert('Ocorreu um erro ao excluir o endereço: " + e.getMessage() + "'); window.location.href='endereco/listarEnderecos.jsp';</script>");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/endereco/listarEnderecos.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para excluir enderecos";
    }

}
