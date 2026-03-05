<%-- Document : listarPaises Created on : Jun 5, 2018, 11:01:38 AM Author : natan --%>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
                <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
                <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
                <script>
                    function editarRegistroPais(idpais) {
                        window.location.href = "<%=request.getContextPath()%>/endereco/editarPais.jsp?cod=" + idpais;
                    }

                    function excluirRegistroPais(idpais, nome) {
                        if (confirm('Confirma exclusão do pais de código: ' + idpais + ' - Nome: ' + nome + '?')) {
                            window.location.href = "<%=request.getContextPath()%>/ExcluirPais?cod=" + idpais;
                        } else {
                            alert('Exclusão cancelada.');
                        }
                    }
                </script>
                <title>Cadastro de Países</title>
            </head>

            <body>
                <%@ include file="nav.jsp" %>
                    <div class="container">
                        <h2 class="card-title mt-3 text-center">Cadastro de Países</h2>

                        <form id="custom-search-form" class="form-search form-horizontal pull-right" name="frmLocalizar"
                            action="listarPaises.jsp" method="POST">
                            <div class="panel-body">
                                <div class="input-group">
                                    <input class="form-control" placeholder="Buscar pelo nome..." type="text"
                                        name="localizaPais" value="">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" type="submit" value="Pesquisar"
                                            name="btnLocaliza">
                                            <span class="glyphicon glyphicon-search"></span> Buscar
                                        </button>
                                    </div>
                                    <div class="input-group-btn">
                                        <a class="btn btn-default"
                                            href="<%=request.getContextPath()%>/endereco/incluirPais.jsp">
                                            <span class="glyphicon glyphicon-plus-sign"></span> Incluir
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <table class="table table-responsive table-striped">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nome</th>
                                    <th class="text-center">Editar</th>
                                    <th class="text-center">Deletar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% try { service.PaisService servico=new service.PaisService(); String
                                    localizarValor=request.getParameter("localizaPais"); java.util.List<model.Pais>
                                    lista;
                                    if (localizarValor != null && !localizarValor.isEmpty()) {
                                    lista = servico.listar();
                                    out.write("<tr>
                                        <td colspan='4'>
                                            <p class='text-info'>Filtro aplicado: " + localizarValor + " (Busca completa
                                                exibida)</p>
                                        </td>
                                    </tr>");
                                    } else {
                                    lista = servico.listar();
                                    }
                                    request.setAttribute("listaPaises", lista);
                                    } catch (Exception e) {
                                    out.write("<tr>
                                        <td colspan='4' class='text-danger'>Erro ao carregar países: " + e.getMessage()
                                            + "</td>
                                    </tr>");
                                    }
                                    %>
                                    <c:forEach items="${listaPaises}" var="p">
                                        <tr>
                                            <td>${p.idpais}</td>
                                            <td>${p.nome}</td>
                                            <td class="text-center">
                                                <a class='btn btn-info btn-xs'
                                                    onclick="editarRegistroPais('${p.idpais}');">
                                                    <span class="glyphicon glyphicon-edit"></span> Editar
                                                </a>
                                            </td>
                                            <td class="text-center">
                                                <a class='btn btn-danger btn-xs'
                                                    onclick="excluirRegistroPais('${p.idpais}', '${p.nome}');">
                                                    <span class="glyphicon glyphicon-remove"></span> Del
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
            </body>

            </html>