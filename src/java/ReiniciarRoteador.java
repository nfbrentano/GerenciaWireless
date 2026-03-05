
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.legrange.mikrotik.ApiConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natan
 */
public class ReiniciarRoteador extends HttpServlet {

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
        String idroteador = request.getParameter("cod");

        if (idroteador != null) {
            try {
                service.RoteadorService service = new service.RoteadorService();
                service.reiniciar(Integer.parseInt(idroteador));

                request.getRequestDispatcher("/conexao/listarRoteador.jsp").forward(request, response);
            } catch (Exception e) {
                response.getWriter().println("<script>alert('Ocorreu um erro ao reiniciar o roteador: " + e.getMessage() + "'); window.location.href='conexao/listarRoteador.jsp';</script>");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/conexao/listarRoteador.jsp");
        }
    }
           

        } catch (Exception e) {
            response.getWriter().println("<script>alert('Ocorreu um erro ao executar comando: '" + e.getMessage() + ")</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para reinciiar roteador";
    }

}