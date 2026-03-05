package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Frequencia;
import service.FrequenciaService;

/**
 * Controller para ações relacionadas a frequencia
 * @author natan
 */
public class FrequenciaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    // Atributos com informações das views
    // Nota: Esses caminhos /view/ podem precisar de atualização conforme a estrutura do projeto
    private static final String VIEW_INSERT_EDIT = "/conexao/incluirFrequencia.jsp"; 
    private static final String VIEW_LISTAR = "/conexao/listarFrequencies.jsp";
    
    private final FrequenciaService service;

    public FrequenciaController() {
        super();
        this.service = new FrequenciaService();
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

            String codigoFrequencia = request.getParameter("idfrequencia");
            if (codigoFrequencia != null) {
                service.excluir(Integer.parseInt(codigoFrequencia));
            }

            encaminhar = VIEW_LISTAR;
            request.setAttribute("frequencias", service.listar());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoFrequencia = request.getParameter("idfrequencia");
            if (codigoFrequencia != null) {
                Frequencia frequencia = service.getById(Integer.parseInt(codigoFrequencia));
                request.setAttribute("frequencia", frequencia);
            }

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("frequencias", service.listar());

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

        // Instanciar objeto frequencia
        Frequencia frequencia = new Frequencia();

        // Atribuir parâmetros recebidos
        frequencia.setFrequencia(request.getParameter("frequencia"));
        String codigoFrequencia = request.getParameter("idfrequencia");

        // Verificar se tem código.
        if (codigoFrequencia != null && !codigoFrequencia.isEmpty()) {
            frequencia.setIdfrequencia(Integer.parseInt(codigoFrequencia));
        }
        
        service.salvar(frequencia);

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("frequencias", service.listar());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a frequencia";
    }
}
