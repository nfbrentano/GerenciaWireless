package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;
import service.EnderecoService;

/**
 * Controller para ações relacionadas a enderecos
 * @author natan
 */
public class EnderecoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Atributos com informações das views
    private static final String VIEW_INSERT_EDIT = "/endereco/incluirEndereco.jsp";
    private static final String VIEW_LISTAR = "/endereco/listarEnderecos.jsp";
    private static final String VIEW_EDIT = "/endereco/editarEndereco.jsp";

    private final EnderecoService service;

    public EnderecoController() {
        super();
        this.service = new EnderecoService();
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
            String codigoEndereco = request.getParameter("idendereco");
            if (codigoEndereco != null) {
                service.excluir(Integer.parseInt(codigoEndereco));
            }
            encaminhar = VIEW_LISTAR;
            request.setAttribute("enderecos", service.listar());
        } else if (acao.equalsIgnoreCase("editar")) {
            encaminhar = VIEW_EDIT;
            String codigoEndereco = request.getParameter("idendereco");
            if (codigoEndereco != null) {
                Endereco endereco = service.getById(Integer.parseInt(codigoEndereco));
                request.setAttribute("end", endereco);
            }
        } else if (acao.equalsIgnoreCase("listar")) {
            encaminhar = VIEW_LISTAR;
            request.setAttribute("enderecos", service.listar());
        } else {
            encaminhar = VIEW_INSERT_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(encaminhar);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Endereco endereco = new Endereco();
        endereco.setRua(request.getParameter("rua"));
        endereco.setBairro_idbairro(request.getParameter("bairro_idbairro"));
        String codigoEndereco = request.getParameter("idendereco");

        if (codigoEndereco != null && !codigoEndereco.isEmpty()) {
            endereco.setIdendereco(Integer.parseInt(codigoEndereco));
        }
        
        service.salvar(endereco);

        // Redireciona para a listagem (usando forward com atributo para manter padrão)
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        request.setAttribute("enderecos", service.listar());
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a enderecos";
    }
}
