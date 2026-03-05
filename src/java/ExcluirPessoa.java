
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

        if (idcadastropessoa != null) {
            try {
                service.CadastroPessoaService service = new service.CadastroPessoaService();
                service.excluir(Integer.parseInt(idcadastropessoa));

                // Encaminha para a listagem 
                request.getRequestDispatcher("/cadastroPessoa/listarCadastroPessoa.jsp").forward(request, response);
            } catch (Exception e) {
                response.getWriter().println("<script>alert('Ocorreu um erro ao excluir o usuário: " + e.getMessage() + "'); window.location.href='cadastroPessoa/listarCadastroPessoa.jsp';</script>");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/cadastroPessoa/listarCadastroPessoa.jsp");
        }
    }

        } catch (Exception e) {
            response.getWriter().println("<script>alert('Ocorreu um erro ao excluir o usuário: '" + e.getMessage() + ")</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para excluir cadastropessoa";
    }

}
