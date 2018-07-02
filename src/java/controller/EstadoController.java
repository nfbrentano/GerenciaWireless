/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EstadoDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estado;

/**
 *
 * @author natan
 */
public class EstadoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/view/formEstado.jsp";
    private static String VIEW_LISTAR = "/view/listaEstados.jsp";

    private EstadoDao dao;

    public EstadoController() {
        super();

        // Instanciar um objeto do tipo EstadoDao, que já possuirá a conexão ao banco
        dao = new EstadoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoEstado = request.getParameter("idestado");
            dao.deleteEstado(Integer.parseInt(codigoEstado));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("estados", dao.getAllEstados());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoEstado = request.getParameter("idestado");

            Estado estado = dao.getEstadoByCodigo(Integer.parseInt(codigoEstado));
            request.setAttribute("estado", estado);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("estados", dao.getAllEstados());

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

        // Instanciar objeto estado
        Estado estado = new Estado();

        // Atribuir parâmetros recebidos
        estado.setNome(request.getParameter("nome"));
        estado.setPais_idpais(request.getParameter("pais_idpais"));
        String codigoEstado = request.getParameter("idestado");

        // Verificar se tem código.
        // Se não tiver, deve-se inserir uma nova estado
        if (codigoEstado == null || codigoEstado.isEmpty()) {

            dao.insertEstado(estado);

        } else { // Se tiver código, significa que deve atualizar

            estado.setIdestado(Integer.parseInt(codigoEstado));
            dao.updateEstado(estado);

        }

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("estados", dao.getAllEstados());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a estados";
    }

}
