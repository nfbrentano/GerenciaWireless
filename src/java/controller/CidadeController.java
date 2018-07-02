/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CidadeDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cidade;

/**
 *
 * @author natan
 */
public class CidadeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/view/formCidade.jsp";
    private static String VIEW_LISTAR = "/view/listaCidades.jsp";

    private CidadeDao dao;

    public CidadeController() {
        super();

        // Instanciar um objeto do tipo CidadeDao, que já possuirá a conexão ao banco
        dao = new CidadeDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoCidade = request.getParameter("idcidade");
            dao.deleteCidade(Integer.parseInt(codigoCidade));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("cidades", dao.getAllCidades());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoCidade = request.getParameter("idcidade");

            Cidade cidade = dao.getCidadeByCodigo(Integer.parseInt(codigoCidade));
            request.setAttribute("cidade", cidade);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("cidades", dao.getAllCidades());

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

        // Instanciar objeto cidade
        Cidade cidade = new Cidade();

        // Atribuir parâmetros recebidos
        cidade.setNome(request.getParameter("nome"));
        cidade.setEstado_idestado(request.getParameter("estado_idestado"));
        String codigoCidade = request.getParameter("idcidade");

        // Verificar se tem código.
        // Se não tiver, deve-se inserir uma nova cidade
        if (codigoCidade == null || codigoCidade.isEmpty()) {

            dao.insertCidade(cidade);

        } else { // Se tiver código, significa que deve atualizar

            cidade.setIdcidade(Integer.parseInt(codigoCidade));
            dao.updateCidade(cidade);

        }

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("cidades", dao.getAllCidades());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a cidades";
    }

}
