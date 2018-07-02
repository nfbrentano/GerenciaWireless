
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

            // Registrar o driver JDBC para PostgreSQL
            Class.forName("org.postgresql.Driver"); // ou DriverManager.registerDriver(new org.postgresql.Driver());
            // Conectar o banco
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cadastroweb", "postgres", "postgres");
            // Statement para executar os comandos sql
            Statement st = conn.createStatement();
         
            System.out.println("while");
       
                ResultSet res = st.executeQuery("SELECT * FROM pontoacesso WHERE idpontoacesso = '" + idroteador + "'");
                while (res.next()) {
                    String ip = res.getString("iproteador");
                    System.out.println(ip);
                    String userroteador = res.getString("usuario");
                    String passwordroteador = res.getString("pass");
                    ApiConnection con = ApiConnection.connect("" + ip + ""); // connect
                    System.out.println("APi");
                    con.login("" + userroteador + "", "" + passwordroteador + ""); // log in to router
                    System.out.println("user e pass=" + passwordroteador);
                    con.execute("/system/reboot");
                    System.out.println("execute");
                    con.close();
                   
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