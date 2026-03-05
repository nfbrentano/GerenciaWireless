/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BairroDao;
import service.BairroService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bairro;

/**
 *
 * @author natan
 */
public class BairroController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/endereco/incluirBairro.jsp";
    private static String VIEW_LISTAR = "/endereco/listarBairros.jsp";

    private BairroService servico;

    public BairroController() {
        super();

        // Instanciar um objeto do tipo BairroDao, que já possuirá a conexão ao banco
        servico = new BairroService();
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

            String codigoBairro = request.getParameter("idbairro");
            servico.excluir(Integer.parseInt(codigoBairro));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("bairros", servico.listar());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = "/endereco/editarBairro.jsp";
            String codigoBairro = request.getParameter("idbairro");

            model.Bairro bairro = servico.getById(Integer.parseInt(codigoBairro));
            request.setAttribute("bai", bairro);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("bairros", servico.listar());

        } else {
            encaminhar = VIEW_INSERT_EDIT;
        }

        // Dispatcher para onde será redirecionado
        request.getRequestDispatcher(encaminhar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instanciar objeto bairro
        model.Bairro bairro = new model.Bairro();

        // Atribuir parâmetros recebidos
        bairro.setNome(request.getParameter("nome"));
        bairro.setCidade_idcidade(request.getParameter("cidade_idcidade"));
        String codigoBairro = request.getParameter("idbairro");

        // Verificar se tem código.
        // Se não tiver, deve-se inserir uma nova bairro
        if (codigoBairro == null || codigoBairro.isEmpty()) {

            servico.salvar(bairro);

        } else { // Se tiver código, significa que deve atualizar

            bairro.setIdbairro(Integer.parseInt(codigoBairro));
            servico.salvar(bairro);

        }

        // Redirecionar para a lista de bairros
        response.sendRedirect(request.getContextPath() + VIEW_LISTAR);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a bairros";
    }

}
