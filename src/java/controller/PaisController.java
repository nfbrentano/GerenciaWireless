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
    private static String VIEW_INSERT_EDIT = "/view/formPais.jsp";
    private static String VIEW_LISTAR = "/view/listaPaises.jsp";

    private PaisDao dao;

    public PaisController() {
        super();

        // Instanciar um objeto do tipo PaisDao, que já possuirá a conexão ao banco
        dao = new PaisDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoPais = request.getParameter("idpais");
            dao.deletePais(Integer.parseInt(codigoPais));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("paises", dao.getAllPaises());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoPais = request.getParameter("idpais");

            Pais pais = dao.getPaisByCodigo(Integer.parseInt(codigoPais));
            request.setAttribute("pais", pais);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("paises", dao.getAllPaises());

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

        // Instanciar objeto pais
        Pais pais2 = new Pais();

        // Atribuir parâmetros recebidos
        pais2.setNome(request.getParameter("nome"));
        String codigoPais = request.getParameter("idpais");

        // Verificar se tem código.
        // Se não tiver, deve-se inserir uma nova pais
        if (codigoPais == null || codigoPais.isEmpty()) {

            dao.insertPais(pais2);

        } else { // Se tiver código, significa que deve atualizar

            pais2.setIdpais(Integer.parseInt(codigoPais));
            dao.updatePais(pais2);

        }

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("paises", dao.getAllPaises());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a pais";
    }

}
