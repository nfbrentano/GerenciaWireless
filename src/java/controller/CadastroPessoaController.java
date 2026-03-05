package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroPessoa;
import service.CadastroPessoaService;

public class CadastroPessoaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/cadastroPessoa/incluirCadastroPessoa.jsp"; // Ajustado para o caminho correto
    private static String VIEW_LISTAR = "/cadastroPessoa/listarCadastroPessoa.jsp";   // Ajustado para o caminho correto

    private CadastroPessoaService service;

    public CadastroPessoaController() {
        super();
        service = new CadastroPessoaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao != null && acao.equalsIgnoreCase("deletar")) {

            String id = request.getParameter("idcadastroPessoa");
            if (id != null) {
                try {
                    service.excluir(Integer.parseInt(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            encaminhar = VIEW_LISTAR;
            request.setAttribute("cadastroPessoas", service.listarTodos());

        } else if (acao != null && acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String id = request.getParameter("idcadastroPessoa");
            if (id != null) {
                CadastroPessoa pessoa = service.buscarPorId(Integer.parseInt(id));
                request.setAttribute("cadastroPessoa", pessoa);
            }

        } else if (acao != null && acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("cadastroPessoas", service.listarTodos());

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

        // Instanciar novo objeto da model CadastroPessoa
        CadastroPessoa cadastroPessoa = new CadastroPessoa();

        // Atribuir valores vindos da view via "name" ao objeto da model
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
        cadastroPessoa.setPontoacesso(request.getParameter("pontoacesso"));
        
        String idStr = request.getParameter("idcadastroPessoa");

        // Se tiver ID, atualiza, senão insere
        if (idStr != null && !idStr.isEmpty()) {
            cadastroPessoa.setIdcadastroPessoa(Integer.parseInt(idStr));
        }
        
        service.salvar(cadastroPessoa);

        // Redireciona para a listagem
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        request.setAttribute("cadastroPessoas", service.listarTodos());
        view.forward(request, response);
    }
}
