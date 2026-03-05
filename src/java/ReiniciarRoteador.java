
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

        try {

            // Usar o utilitário de conexão padronizado
            Connection conn = util.Db.getConexao();
            
            // Usar PreparedStatement para evitar Injeção de SQL
            String sql = "SELECT iproteador, usuario, pass FROM pontoacesso WHERE idpontoacesso = ?";
            try (java.sql.PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, Integer.parseInt(idroteador));
                
                try (ResultSet res = st.executeQuery()) {
                    if (res.next()) {
                        String ip = res.getString("iproteador");
                        String userroteador = res.getString("usuario");
                        String passwordroteador = res.getString("pass");
                        
                        ApiConnection apiCon = ApiConnection.connect(ip);
                        apiCon.login(userroteador, passwordroteador);
                        apiCon.execute("/system/reboot");
                        apiCon.close();
                       
                        request.getRequestDispatcher("/conexao/listarRoteador.jsp").forward(request, response);
                    }
                }
            }

                   
                    // Encaminha para a listagem 
                    request.getRequestDispatcher("/conexao/listarRoteador.jsp").forward(request, response);
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