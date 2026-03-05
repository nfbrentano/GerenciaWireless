package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pais;
import service.PaisService;

public class PaisController extends HttpServlet {

    private final PaisService service;
    private final Gson gson;

    public PaisController() {
        super();
        this.service = new PaisService();
        this.gson = new Gson();
    }

    private void sendJsonResponse(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if (data instanceof String) {
            out.print((String) data);
        } else {
            out.print(gson.toJson(data));
        }
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            Pais entidade = service.getById(Integer.parseInt(idStr));
            sendJsonResponse(response, entidade);
        } else {
            sendJsonResponse(response, service.listar());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jsonBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Pais entidade = gson.fromJson(jsonBody, Pais.class);

        try {
            service.salvar(entidade);
            sendJsonResponse(response, "{ \"status\" : \"success\" }");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            sendJsonResponse(response, "{ \"error\" : \"" + e.getMessage() + "\" }");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            service.excluir(Integer.parseInt(idStr));
            sendJsonResponse(response, "{ \"status\" : \"success\" }");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            sendJsonResponse(response, "{ \"error\" : \"Missing ID\" }");
        }
    }
}
