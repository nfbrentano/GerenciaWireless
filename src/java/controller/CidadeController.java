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
    private static String VIEW_INSERT_EDIT = "/endereco/incluirCidade.jsp";
    private static String VIEW_LISTAR = "/endereco/listarCidades.jsp";

    private service.CidadeService servico;

    public CidadeController() {
        super();
        servico = new service.CidadeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        if (acao == null) {
            acao = "listar";
        }

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoCidade = request.getParameter("idcidade");
            servico.excluir(Integer.parseInt(codigoCidade));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("cidades", servico.listar());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = "/endereco/editarCidade.jsp";
            String codigoCidade = request.getParameter("idcidade");

            model.Cidade cidade = servico.getById(Integer.parseInt(codigoCidade));
            request.setAttribute("cid", cidade);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("cidades", servico.listar());

        } else {
            encaminhar = VIEW_INSERT_EDIT;
        }

        // Dispatcher para onde será redirecionado
        request.getRequestDispatcher(encaminhar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instanciar objeto cidade
        model.Cidade cidade = new model.Cidade();

        // Atribuir parâmetros recebidos
        cidade.setNome(request.getParameter("nome"));
        cidade.setEstado_idestado(request.getParameter("estado_idestado"));
        String codigoCidade = request.getParameter("idcidade");

        // Verificar se tem código.
        // Se não tiver, deve-se inserir uma nova cidade
        if (codigoCidade == null || codigoCidade.isEmpty()) {

            servico.salvar(cidade);

        } else { // Se tiver código, significa que deve atualizar

            cidade.setIdcidade(Integer.parseInt(codigoCidade));
            servico.salvar(cidade);

        }

        response.sendRedirect(request.getContextPath() + VIEW_LISTAR);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a cidades";
    }

}
