/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import dao.RoteadorDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Roteador;

/**
 *
 * @author natan
 */
public class RoteadorController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/view/formRoteador.jsp";
    private static String VIEW_LISTAR = "/view/listaRoteadores.jsp";
    private RoteadorDao dao;

    public RoteadorController() {
        super();
        // Instanciar um objeto do tipo RoteadorDao, que já possuirá a conexão ao banco
        dao = new RoteadorDao();
    }
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoRoteador = request.getParameter("idpontoacesso");
            dao.deleteRoteador(Integer.parseInt(codigoRoteador));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("roteadoeres", dao.getAllRoteadores());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoRoteador = request.getParameter("idpontoacesso");

            Roteador roteador = dao.getRoteadorByCodigo(Integer.parseInt(codigoRoteador));
            request.setAttribute("roteador", roteador);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("roteadores", dao.getAllRoteadores());

        } else {
            encaminhar = VIEW_INSERT_EDIT;
        }

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(encaminhar);
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instanciar objeto roteador
        Roteador roteador = new Roteador();

        // Atribuir parâmetros recebidos
        roteador.setSsid(request.getParameter("ssid"));
        roteador.setModelo(request.getParameter("modelo"));
        roteador.setLargurabanda(request.getParameter("largurabanda"));
        roteador.setFrequencia(request.getParameter("frequencia"));
        roteador.setIproteador(request.getParameter("iproteador"));
        roteador.setUsuario(request.getParameter("usuario"));
        roteador.setPass(request.getParameter("pass"));
        String codigoRoteador = request.getParameter("idpontoacesso");

        // Verificar se tem código.
        // Se não tiver, deve-se inserir uma nova roteador
        if (codigoRoteador == null || codigoRoteador.isEmpty()) {

            dao.insertRoteador(roteador);

        } else { // Se tiver código, significa que deve atualizar

            roteador.setIdpontoacesso(Integer.parseInt(codigoRoteador));
            try {
                dao.updateRoteador(roteador);
            } catch (SQLException ex) {
                Logger.getLogger(RoteadorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("roteadores", dao.getAllRoteadores());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a roteador";
    }
}
