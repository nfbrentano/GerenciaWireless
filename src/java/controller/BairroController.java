/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BairroDao;
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
    private static String VIEW_INSERT_EDIT = "/view/formBairro.jsp";
    private static String VIEW_LISTAR = "/view/listaBairros.jsp";

    private BairroDao dao;

    public BairroController() {
        super();
        
        // Instanciar um objeto do tipo BairroDao, que já possuirá a conexão ao banco
        dao = new BairroDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoBairro = request.getParameter("idbairro");
            dao.deleteBairro(Integer.parseInt(codigoBairro));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("bairros", dao.getAllBairros());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoBairro = request.getParameter("idbairro");

            Bairro bairro = dao.getBairroByCodigo(Integer.parseInt(codigoBairro));
            request.setAttribute("bairro", bairro);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("bairros", dao.getAllBairros());

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
        
        // Instanciar objeto bairro
        Bairro bairro = new Bairro();
        
        // Atribuir parâmetros recebidos
        bairro.setNome(request.getParameter("nome"));
        bairro.setCidade_idcidade(request.getParameter("cidade_idcidade"));
          String codigoBairro = request.getParameter("idbairro");
        
        // Verificar se tem código.
        // Se não tiver, deve-se inserir uma nova bairro
        if (codigoBairro == null || codigoBairro.isEmpty()) {
            
            dao.insertBairro(bairro);
            
        } else { // Se tiver código, significa que deve atualizar
            
            bairro.setIdbairro(Integer.parseInt(codigoBairro) );
            dao.updateBairro(bairro);
            
        }
        
        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("bairros", dao.getAllBairros());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a bairros";
    }

}