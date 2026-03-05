
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

        try {

            // Usar o utilitário de conexão padronizado
            Connection conn = util.Db.getConexao();

            String sqlPessoa = "SELECT nomeusuario, pontoacesso FROM cadastropessoa WHERE idcadastropessoa = ?";
            try (java.sql.PreparedStatement stPessoa = conn.prepareStatement(sqlPessoa)) {
                stPessoa.setInt(1, Integer.parseInt(idcadastropessoa));
                try (ResultSet rs = stPessoa.executeQuery()) {
                    if (rs.next()) {
                        String user = rs.getString("nomeusuario");
                        String pontoacesso = rs.getString("pontoacesso");

                        String sqlPonto = "SELECT iproteador, usuario, pass FROM pontoacesso WHERE ssid = ?";
                        try (java.sql.PreparedStatement stPonto = conn.prepareStatement(sqlPonto)) {
                            stPonto.setString(1, pontoacesso);
                            try (ResultSet res = stPonto.executeQuery()) {
                                if (res.next()) {
                                    String ip = res.getString("iproteador");
                                    String userroteador = res.getString("usuario");
                                    String passwordroteador = res.getString("pass");
                                    
                                    ApiConnection apiCon = ApiConnection.connect(ip);
                                    apiCon.login(userroteador, passwordroteador);
                                    apiCon.execute("/ip/hotspot/user/remove numbers='" + user + "'");
                                    apiCon.close();
                                }
                            }
                        }

                        // SQL para excluir (soft delete)
                        String query = "UPDATE cadastropessoa SET disponibilidade = false WHERE idcadastropessoa = ?";
                        try (java.sql.PreparedStatement stDel = conn.prepareStatement(query)) {
                            stDel.setInt(1, Integer.parseInt(idcadastropessoa));
                            stDel.executeUpdate();
                        }

                        // Encaminha para a listagem 
                        request.getRequestDispatcher("/cadastroPessoa/listarCadastroPessoa.jsp").forward(request, response);
                    }
                }
            }


                    // Encaminha para a listagem 
                    request.getRequestDispatcher("/cadastroPessoa/listarCadastroPessoa.jsp").forward(request, response);
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
