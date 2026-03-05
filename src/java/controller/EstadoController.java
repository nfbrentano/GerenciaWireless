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
    private static String VIEW_INSERT_EDIT = "/endereco/incluirEstado.jsp";
    private static String VIEW_LISTAR = "/endereco/listarEstados.jsp";

    private service.EstadoService servico;

    public EstadoController() {
        super();
        servico = new service.EstadoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String encaminhar = "";

        if (acao == null) {
            acao = "listar";
        }

        if (acao.equalsIgnoreCase("deletar")) {
            String codigoEstado = request.getParameter("idestado");
            servico.excluir(Integer.parseInt(codigoEstado));
            encaminhar = VIEW_LISTAR;
            request.setAttribute("estados", servico.listar());
        } else if (acao.equalsIgnoreCase("editar")) {
            encaminhar = "/endereco/editarEstado.jsp";
            String codigoEstado = request.getParameter("idestado");
            model.Estado estado = servico.getById(Integer.parseInt(codigoEstado));
            request.setAttribute("est", estado);
        } else if (acao.equalsIgnoreCase("listar")) {
            encaminhar = VIEW_LISTAR;
            request.setAttribute("estados", servico.listar());
        } else {
            encaminhar = VIEW_INSERT_EDIT;
        }

        request.getRequestDispatcher(encaminhar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        model.Estado estado = new model.Estado();
        estado.setNome(request.getParameter("nome"));
        estado.setSigla(request.getParameter("sigla"));
        estado.setPais_idpais(request.getParameter("pais_idpais"));
        String codigoEstado = request.getParameter("idestado");

        if (codigoEstado == null || codigoEstado.isEmpty()) {
            servico.salvar(estado);
        } else {
            estado.setIdestado(Integer.parseInt(codigoEstado));
            servico.salvar(estado);
        }

        response.sendRedirect(request.getContextPath() + VIEW_LISTAR);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a estados";
    }

}
