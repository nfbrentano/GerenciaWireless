/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PaisDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pais;

/**
 *
 * @author natan
 */
public class PaisController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/endereco/incluirPais.jsp";
    private static String VIEW_LISTAR = "/endereco/listarPaises.jsp";

    private service.PaisService servico;

    public PaisController() {
        super();
        servico = new service.PaisService();
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
            String codigoPais = request.getParameter("idpais");
            servico.excluir(Integer.parseInt(codigoPais));
            encaminhar = VIEW_LISTAR;
            request.setAttribute("paises", servico.listar());
        } else if (acao.equalsIgnoreCase("editar")) {
            encaminhar = "/endereco/editarPais.jsp";
            String codigoPais = request.getParameter("idpais");
            model.Pais pais = servico.getById(Integer.parseInt(codigoPais));
            request.setAttribute("pai", pais);
        } else if (acao.equalsIgnoreCase("listar")) {
            encaminhar = VIEW_LISTAR;
            request.setAttribute("paises", servico.listar());
        } else {
            encaminhar = VIEW_INSERT_EDIT;
        }

        request.getRequestDispatcher(encaminhar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        model.Pais pais = new model.Pais();
        pais.setNome(request.getParameter("nome"));
        String codigoPais = request.getParameter("idpais");

        if (codigoPais == null || codigoPais.isEmpty()) {
            servico.salvar(pais);
        } else {
            pais.setIdpais(Integer.parseInt(codigoPais));
            servico.salvar(pais);
        }

        response.sendRedirect(request.getContextPath() + VIEW_LISTAR);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a pais";
    }

}
