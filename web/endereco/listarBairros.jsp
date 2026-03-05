<%-- Document : listarBairros Created on : Jun 5, 2018, 9:21:17 AM Author : natan --%>
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
                    function editarRegistroBairro(idbairro) {
                        window.location.href = "<%=request.getContextPath()%>/endereco/editarBairro.jsp?cod=" + idbairro;
                    }

                    function excluirRegistroBairro(idbairro, nome) {
                        if (confirm('Confirma exclusão do bairro de código: ' + idbairro + ' - Nome: ' + nome + '?')) {
                            window.location.href = "<%=request.getContextPath()%>/ExcluirBairro?cod=" + idbairro;
                        } else {
                            alert('Exclusão cancelada.');
                        }
                    }
                </script>
                <title>Cadastro de Bairros</title>
            </head>

            <body>
                <%@ include file="nav.jsp" %>
                    <div class="container">
                        <h2 class="card-title mt-3 text-center">Cadastro de Bairros</h2>

                        <form id="custom-search-form" class="form-search form-horizontal pull-right" name="frmLocalizar"
                            action="listarBairros.jsp" method="POST">
                            <div class="panel-body">
                                <div class="input-group">
                                    <input class="form-control" placeholder="Buscar pelo nome..." type="text"
                                        name="localizaBairro" value="">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" type="submit" value="Pesquisar"
                                            name="btnLocaliza">
                                            <span class="glyphicon glyphicon-search"></span> Buscar
                                        </button>
                                    </div>
                                    <div class="input-group-btn">
                                        <a class="btn btn-default"
                                            href="<%=request.getContextPath()%>/endereco/incluirBairro.jsp">
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
                                    <th>Cod Cidade</th>
                                    <th class="text-center">Editar</th>
                                    <th class="text-center">Deletar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% try { service.BairroService servico=new service.BairroService(); String
                                    localizarValor=request.getParameter("localizaBairro"); java.util.List<model.Bairro>
                                    lista;
                                    if (localizarValor != null && !localizarValor.isEmpty()) {
                                    lista = servico.listar();
                                    out.write("<tr>
                                        <td colspan='5 text-info'>
                                            <p>Filtro aplicado: " + localizarValor + " (Busca completa exibida)</p>
                                        </td>
                                    </tr>");
                                    } else {
                                    lista = servico.listar();
                                    }
                                    request.setAttribute("listaBairros", lista);
                                    } catch (Exception e) {
                                    out.write("<tr>
                                        <td colspan='5'>Erro ao carregar bairros: " + e.getMessage() + "</td>
                                    </tr>");
                                    }
                                    %>
                                    <c:forEach items="${listaBairros}" var="b">
                                        <tr>
                                            <td>${b.idbairro}</td>
                                            <td>${b.nome}</td>
                                            <td>${b.cidade_idcidade}</td>
                                            <td class="text-center">
                                                <a class='btn btn-info btn-xs'
                                                    onclick="editarRegistroBairro(${b.idbairro});">
                                                    <span class="glyphicon glyphicon-edit"></span> Editar
                                                </a>
                                            </td>
                                            <td class="text-center">
                                                <a class='btn btn-danger btn-xs'
                                                    onclick="excluirRegistroBairro(${b.idbairro}, '${b.nome}');">
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