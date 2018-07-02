/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FrequenciaDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Frequencia;

/**
 *
 * @author natan
 */
public class FrequenciaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // Atributos com informações das views
    private static String VIEW_INSERT_EDIT = "/view/formFrequencia.jsp";
    private static String VIEW_LISTAR = "/view/listaFrequencias.jsp";
    private FrequenciaDao dao;

    public FrequenciaController() {
        super();
        // Instanciar um objeto do tipo FrequenciaDao, que já possuirá a conexão ao banco
        dao = new FrequenciaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscar a ação requisitada
        String acao = request.getParameter("acao");
        String encaminhar = "";

        // Verificar qual foi a ação solicitada
        if (acao.equalsIgnoreCase("deletar")) {

            String codigoFrequencia = request.getParameter("idfrequencia");
            dao.deleteFrequencia(Integer.parseInt(codigoFrequencia));

            encaminhar = VIEW_LISTAR;

            request.setAttribute("frequencias", dao.getAllFrequencias());

        } else if (acao.equalsIgnoreCase("editar")) {

            encaminhar = VIEW_INSERT_EDIT;
            String codigoFrequencia = request.getParameter("idfrequencia");

            Frequencia frequencia = dao.getFrequenciaByCodigo(Integer.parseInt(codigoFrequencia));
            request.setAttribute("frequencia", frequencia);

        } else if (acao.equalsIgnoreCase("listar")) {

            encaminhar = VIEW_LISTAR;
            request.setAttribute("frequencias", dao.getAllFrequencias());

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
        // Se não tiver, deve-se inserir uma nova frequencia
        if (codigoFrequencia == null || codigoFrequencia.isEmpty()) {

            dao.insertFrequencia(frequencia);

        } else { // Se tiver código, significa que deve atualizar

            frequencia.setIdfrequencia(Integer.parseInt(codigoFrequencia));
            dao.updateFrequencia(frequencia);

        }

        // Dispatcher para onde será redirecionado
        RequestDispatcher view = request.getRequestDispatcher(VIEW_LISTAR);
        // Adicionar atributo
        request.setAttribute("frequencias", dao.getAllFrequencias());
        // Encaminhar para a view
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller para ações relacionadas a frequencia";
    }
}
