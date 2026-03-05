<%-- Document : listarRoteador Created on : Jun 8, 2018, 10:30:10 AM Author : natan --%>

    <%@page import="java.sql.Statement" %>
        <%@page import="java.sql.DriverManager" %>
            <%@page import="java.sql.Connection" %>
                <%@page import="java.sql.ResultSet" %>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                        <!DOCTYPE html>
                        <html>

                        <head>
                            <!-- Required meta tags -->
                            <meta charset="utf-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

                            <!-- Bootstrap CSS -->
                            <!-- Latest compiled and minified CSS -->
                            <link rel="stylesheet"
                                href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

                            <!-- Optional theme -->
                            <link rel="stylesheet"
                                href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

                            <!-- Latest compiled and minified JavaScript -->
                            <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
                            <!-- Outros -->
                            <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.css">
                            <script type="text/javascript">
                                // Função javascript para chamar edição de registro
                                function editarRegistro(idcadastroRoteador) {
                                    window.location.href = "<%=request.getContextPath()%>/conexao/editarCadastroRoteador.jsp?cod=" +
                                        idcadastroRoteador;
                                }

                                // Função javascript para chamar Servlet de exclusão de registro
                                function excluirRegistro(idpontoacesso, ssid) {
                                    if (confirm('Confirma exclusão do Roteador de código: ' + idpontoacesso + ' - SSID: ' + ssid
                                        + '?')) {
                                        window.location.href = "<%=request.getContextPath()%>/ExcluirRoteador?cod=" + idpontoacesso;
                                    } else {
                                        alert('Exclusão cancelada.');
                                    }
                                }

                                function reiniciarRoteador(id) {
                                    if (confirm('Deseja realmente reiniciar este roteador?')) {
                                        window.location.href = "<%=request.getContextPath()%>/ReiniciarRoteador?cod=" + id;
                                    }
                                }
                            </script>

                            <title>Cadastro de Roteadores</title>

                        </head>

                        <body>

                            <%@ include file="nav.jsp" %>


                                <form id="custom-search-form" class="form-search form-horizontal pull-right"
                                    name="frmLocalizar" action="listarCadastroPessoa.jsp" method="POST">
                                    <div class="panel-body">
                                        <div class="input-group">

                                            <input class="form-control" placeholder="Buscar pelo nome..." type="text"
                                                name="localizaRoteador" value="">
                                            <div class="input-group-btn">
                                                <button class="btn btn-default" type="submit" value="Pesquisar"
                                                    name="btnLocaliza"><span class="glyphicon glyphicon-search"></span>
                                                    Buscar </button>
                                            </div>
                                            <div class="input-group-btn">
                                                <a class="btn btn-default" type="submit"
                                                    href="<%=request.getContextPath()%>/conexao/incluirCadastroRoteador.jsp"
                                                    name="incluirRoteador"><span
                                                        class="glyphicon glyphicon-plus-sign"></span> Incluir </a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <!-- Tabela -->


                                <table class="table table-responsive">

                                    <thead>
                                        <tr>
                                            <th>Código</th>
                                            <th>SSID</th>
                                            <th>Modelo</th>
                                            <th>Largura de Banda Mhz</th>
                                            <th>Frequência</th>
                                            <th>IP Roteador</th>
                                            <th>Usuário</th>
                                            <th>Senha</th>
                                            <th>Reboot</th>
                                            <th>Editar</th>
                                            <th>Deletar</th>

                                        </tr>
                                    </thead>

                                    <tbody>

                                        <!-- Buscar registros no banco de dados
                     Cada linha da tabela apresentará um registro da tabela "clientes" -->
                                        <% try { service.RoteadorService servico=new service.RoteadorService(); String
                                            localizarValor=request.getParameter("localizaRoteador");
                                            java.util.List<model.Roteador> lista;

                                            if (localizarValor != null && !localizarValor.isEmpty()) {
                                            // Por simplicidade, exibimos todos, o filtro pode ser implementado no
                                            DAO/Service depois
                                            lista = servico.listar();
                                            out.write("<p class='text-info'>Filtro aplicado: " + localizarValor + "
                                                (Busca
                                                completa exibida)</p>");
                                            } else {
                                            lista = servico.listar();
                                            }
                                            request.setAttribute("listaRoteadores", lista);
                                            } catch (Exception e) {
                                            out.write("<p class='text-danger'>Erro ao carregar roteadores: " +
                                                e.getMessage() + "</p>");
                                            }
                                            %>

                                            <c:forEach items="${listaRoteadores}" var="rot">
                                                <tr>
                                                    <td>${rot.idpontoacesso}</td>
                                                    <td>${rot.ssid}</td>
                                                    <td>${rot.modelo}</td>
                                                    <td>${rot.largurabanda}</td>
                                                    <td>${rot.frequencia}</td>
                                                    <td>${rot.iproteador}</td>
                                                    <td>${rot.usuario}</td>
                                                    <td>${rot.pass}</td>
                                                    <td class="text-center">
                                                        <a class='btn btn-info btn-xs'
                                                            onclick="reiniciarRoteador(${rot.idpontoacesso});">
                                                            <span class="glyphicon glyphicon-refresh"></span> Reboot
                                                        </a>
                                                    </td>
                                                    <td class="text-center">
                                                        <a class='btn btn-info btn-xs'
                                                            onclick="editarRegistro(${rot.idpontoacesso});">
                                                            <span class="glyphicon glyphicon-edit"></span> Editar
                                                        </a>
                                                    </td>
                                                    <td class="text-center">
                                                        <a class='btn btn-danger btn-xs'
                                                            onclick="excluirRegistro(${rot.idpontoacesso}, '${rot.ssid}');">
                                                            <span class="glyphicon glyphicon-remove"></span> Del
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>


                                    </tbody>
                                </table>

                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                                <script
                                    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                        </body>

                        </html>