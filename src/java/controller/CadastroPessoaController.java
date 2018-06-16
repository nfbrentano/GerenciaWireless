/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CadastroPessoaDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroPessoa;

/**
 *
 * @author natan
 */
public class CadastroPessoaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/view/formCadastroPessoa.jsp";
    private static String VIEW_LISTAR = "/view/listaCadastroPessoa.jsp";

    private CadastroPessoaDao dao;

    public CadastroPessoaController() {
        super();

        // Instanciar um objeto do tipo CadastroPessoaDao, que já possuirá a conexão ao banco
        dao = new CadastroPessoaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoCadastroPessoa = request.getParameter("idcadastroPessoa");
            dao.deleteCadastroPessoa(Integer.parseInt(codigoCadastroPessoa));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("cadastroPessoa", dao.getAllCadastroPessoa());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoCadastroPessoa = request.getParameter("idcadastroPessoa");

            CadastroPessoa cadastroPessoa = dao.getCadastroPessoaByCodigo(Integer.parseInt(codigoCadastroPessoa));
            request.setAttribute("cadastroPessoa", cadastroPessoa);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("cadastroPessoa", dao.getAllCadastroPessoa());

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

        // Instanciar objeto cadastroPessoa
        CadastroPessoa cadastroPessoa = new CadastroPessoa();

        // Atribuir parâmetros recebidos
        cadastroPessoa.setNome(request.getParameter("nome"));
        cadastroPessoa.setSobrenome(request.getParameter("sobrenome"));
        cadastroPessoa.setDocumento(request.getParameter("documento"));
        cadastroPessoa.setPais(request.getParameter("pais"));
        cadastroPessoa.setEstado(request.getParameter("estado"));
        cadastroPessoa.setCidade(request.getParameter("cidade"));
        cadastroPessoa.setBairro(request.getParameter("bairro"));
        cadastroPessoa.setEndereco(request.getParameter("endereco"));
        cadastroPessoa.setNumeroendereco(request.getParameter("numeroendereco"));
        cadastroPessoa.setNomeusuario(request.getParameter("nomeusuario"));
        cadastroPessoa.setSenhaacesso(request.getParameter("senhaacesso"));
        String codigoCadastroPessoa = request.getParameter("idcadastroPessoa");

        // Verificar se tem código.
        // Se não tiver, deve-se inserir um novo cadastroPessoa
        if (codigoCadastroPessoa == null || codigoCadastroPessoa.isEmpty()) {

            dao.insertCadastroPessoa(cadastroPessoa);

        } else { // Se tiver código, significa que deve atualizar

            cadastroPessoa.setIdcadastroPessoa(Integer.parseInt(codigoCadastroPessoa));
            dao.updateCadastroPessoa(cadastroPessoa);

        }

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("cadastroPessoa", dao.getAllCadastroPessoa());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a cadastroPessoa";
    }

}
