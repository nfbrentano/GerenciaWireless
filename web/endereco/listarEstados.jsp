<%-- Document : listarEstados Created on : Jun 5, 2018, 10:28:30 AM Author : natan --%>
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
                    function editarRegistroEstado(idestado) {
                        window.location.href = "<%=request.getContextPath()%>/endereco/editarEstado.jsp?cod=" + idestado;
                    }

                    function excluirRegistroEstado(idestado, nome) {
                        if (confirm('Confirma exclusão do estado de código: ' + idestado + ' - Nome: ' + nome + '?')) {
                            window.location.href = "<%=request.getContextPath()%>/ExcluirEstado?cod=" + idestado;
                        } else {
                            alert('Exclusão cancelada.');
                        }
                    }
                </script>
                <title>Cadastro de Estados</title>
            </head>

            <body>
                <%@ include file="nav.jsp" %>
                    <div class="container">
                        <h2 class="card-title mt-3 text-center">Cadastro de Estados</h2>

                        <form id="custom-search-form" class="form-search form-horizontal pull-right" name="frmLocalizar"
                            action="listarEstados.jsp" method="POST">
                            <div class="panel-body">
                                <div class="input-group">
                                    <input class="form-control" placeholder="Buscar pelo nome..." type="text"
                                        name="localizaEstado" value="">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" type="submit" value="Pesquisar"
                                            name="btnLocaliza">
                                            <span class="glyphicon glyphicon-search"></span> Buscar
                                        </button>
                                    </div>
                                    <div class="input-group-btn">
                                        <a class="btn btn-default"
                                            href="<%=request.getContextPath()%>/endereco/incluirEstado.jsp">
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
                                    <th>Sigla</th>
                                    <th>Cód Pais</th>
                                    <th class="text-center">Editar</th>
                                    <th class="text-center">Deletar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% try { service.EstadoService servico=new service.EstadoService(); String
                                    localizarValor=request.getParameter("localizaEstado"); java.util.List<model.Estado>
                                    lista;
                                    if (localizarValor != null && !localizarValor.isEmpty()) {
                                    lista = servico.listar();
                                    out.write("<tr>
                                        <td colspan='6'>
                                            <p class='text-info'>Filtro aplicado: " + localizarValor + " (Busca completa
                                                exibida)</p>
                                        </td>
                                    </tr>");
                                    } else {
                                    lista = servico.listar();
                                    }
                                    request.setAttribute("listaEstados", lista);
                                    } catch (Exception e) {
                                    out.write("<tr>
                                        <td colspan='6' class='text-danger'>Erro ao carregar estados: " + e.getMessage()
                                            + "</td>
                                    </tr>");
                                    }
                                    %>
                                    <c:forEach items="${listaEstados}" var="est">
                                        <tr>
                                            <td>${est.idestado}</td>
                                            <td>${est.nome}</td>
                                            <td>${est.sigla}</td>
                                            <td>${est.pais_idpais}</td>
                                            <td class="text-center">
                                                <a class='btn btn-info btn-xs'
                                                    onclick="editarRegistroEstado('${est.idestado}');">
                                                    <span class="glyphicon glyphicon-edit"></span> Editar
                                                </a>
                                            </td>
                                            <td class="text-center">
                                                <a class='btn btn-danger btn-xs'
                                                    onclick="excluirRegistroEstado('${est.idestado}', '${est.nome}');">
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