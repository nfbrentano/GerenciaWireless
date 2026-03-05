package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Roteador;
import service.RoteadorService;

/**
 * Controller para ações relacionadas a roteador
 * @author natan
 */
public class RoteadorController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    // Atributos com informações das views
    private static final String VIEW_INSERT_EDIT = "/conexao/incluirCadastroRoteador.jsp";
    private static final String VIEW_LISTAR = "/conexao/listarRoteador.jsp";
    private static final String VIEW_EDIT = "/conexao/editarCadastroRoteador.jsp";
    
    private final RoteadorService service;

    public RoteadorController() {
        super();
        this.service = new RoteadorService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao != null && acao.equalsIgnoreCase("deletar")) {

            String codigoRoteador = request.getParameter("idpontoacesso");
            if (codigoRoteador != null) {
                service.excluir(Integer.parseInt(codigoRoteador));
            }

            encaminhar = VIEW_LISTAR;
            request.setAttribute("roteadores", service.listar());

        } else if (acao != null && acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_EDIT;
            String codigoRoteador = request.getParameter("idpontoacesso");
            if (codigoRoteador != null) {
                Roteador roteador = service.getById(Integer.parseInt(codigoRoteador));
                request.setAttribute("roteador", roteador);
            }

        } else if (acao != null && acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("roteadores", service.listar());

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

        // Instanciar objeto roteador
        Roteador roteador = new Roteador();

        // Atribuir parâmetros recebidos
        roteador.setSsid(request.getParameter("ssid"));
        roteador.setModelo(request.getParameter("modelo"));
        roteador.setLargurabanda(request.getParameter("largurabanda"));
        roteador.setFrequencia(request.getParameter("frequencia"));
        roteador.setIproteador(request.getParameter("iproteador"));
        roteador.setUsuario(request.getParameter("usuario"));
        roteador.setPass(request.getParameter("pass"));
        
        String codigoRoteador = request.getParameter("idpontoacesso");

        // Verificar se tem código.
        if (codigoRoteador != null && !codigoRoteador.isEmpty()) {
            roteador.setIdpontoacesso(Integer.parseInt(codigoRoteador));
        }
        
        service.salvar(roteador);

        // Redireciona para a listagem
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        request.setAttribute("roteadores", service.listar());
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a roteador";
    }
}
