package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Roteador;
import service.RoteadorService;

public class RoteadorController extends HttpServlet {

    private final RoteadorService service;
    private final Gson gson;

    public RoteadorController() {
        super();
        this.service = new RoteadorService();
        this.gson = new Gson();
    }

    private void sendJsonResponse(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(data));
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idpontoacesso = request.getParameter("id");
        if (idpontoacesso != null && !idpontoacesso.isEmpty()) {
            Roteador roteador = service.getById(Integer.parseInt(idpontoacesso));
            sendJsonResponse(response, roteador);
        } else {
            sendJsonResponse(response, service.listar());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        if ("reiniciar".equals(acao)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                try {
                    service.reiniciar(Integer.parseInt(idStr));
                    sendJsonResponse(response, "{ \"status\" : \"success\", \"message\": \"Roteador reiniciando.\" }");
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    sendJsonResponse(response, "{ \"error\" : \"" + e.getMessage() + "\" }");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                sendJsonResponse(response, "{ \"error\" : \"Missing ID\" }");
            }
            return;
        }

        // Lê o body da requisição
        String jsonBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Roteador roteador = gson.fromJson(jsonBody, Roteador.class);
        
        try {
            service.salvar(roteador);
            sendJsonResponse(response, "{ \"status\" : \"success\" }");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            sendJsonResponse(response, "{ \"error\" : \"" + e.getMessage() + "\" }");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idpontoacesso = request.getParameter("id");
        if (idpontoacesso != null && !idpontoacesso.isEmpty()) {
            service.excluir(Integer.parseInt(idpontoacesso));
            sendJsonResponse(response, "{ \"status\" : \"success\" }");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendJsonResponse(response, "{ \"error\" : \"Missing ID\" }");
        }
    }
}
