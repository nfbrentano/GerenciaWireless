/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EnderecoDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;

/**
 *
 * @author natan
 */
public class EnderecoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/view/formEndereco.jsp";
    private static String VIEW_LISTAR = "/view/listaEnderecos.jsp";

    private EnderecoDao dao;

    public EnderecoController() {
        super();

        // Instanciar um objeto do tipo EnderecoDao, que já possuirá a conexão ao banco
        dao = new EnderecoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoEndereco = request.getParameter("idendereco");
            dao.deleteEndereco(Integer.parseInt(codigoEndereco));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("enderecos", dao.getAllEnderecos());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoEndereco = request.getParameter("idendereco");

            Endereco endereco = dao.getEnderecoByCodigo(Integer.parseInt(codigoEndereco));
            request.setAttribute("endereco", endereco);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("enderecos", dao.getAllEnderecos());

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

        // Instanciar objeto endereco
        Endereco endereco = new Endereco();

        // Atribuir parâmetros recebidos
        endereco.setRua(request.getParameter("rua"));
        endereco.setBairro_idbairro(request.getParameter("bairro_idbairro"));
        String codigoEndereco = request.getParameter("idendereco");

        // Verificar se tem código.
        // Se não tiver, deve-se inserir uma nova endereco
        if (codigoEndereco == null || codigoEndereco.isEmpty()) {

            dao.insertEndereco(endereco);

        } else { // Se tiver código, significa que deve atualizar

            endereco.setIdendereco(Integer.parseInt(codigoEndereco));
            dao.updateEndereco(endereco);

        }

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("enderecos", dao.getAllEnderecos());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a enderecos";
    }

}
