/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.UsuarioDAO;
import entidades.Usuario;
import util.Erro;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natan
 */
@WebServlet(name = "Index", urlPatterns = {"/index.jsp", "/logout.jsp"})
public class Index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erros = new Erro();
        if (request.getParameter("bOK") != null) {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            if (login == null || login.isEmpty()) {
                erros.add("Login não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }
            if (!erros.isExisteErros()) {
                UsuarioDAO dao = new UsuarioDAO();
                Usuario user = dao.getSingle(login);
                if (user != null) {
                    if (user.getSenha().equalsIgnoreCase(senha)) {
                        request.getSession().setAttribute("usuarioLogado", user);
                        response.sendRedirect("logado/menu.jsp");
                        return;
                    } else {
                        erros.add("Senha inválida!");
                    }
                } else {
                    erros.add("Usuário não encontrado!");
                }
            }

        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        String URL = "/WEB-INF/view/index.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
