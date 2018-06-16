<%-- 
    Document   : nav
    Created on : Jun 6, 2018, 9:17:14 AM
    Author     : natan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      <nav class="navbar navbar-inverse">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>                        
                        </button>
                    </div>

                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-left">
                            <li class=""><a href="<%=request.getContextPath()%>/logado/menu.jsp">Home</a></li>                                               

                            <li><a href="<%=request.getContextPath()%>/cadastroPessoa/listarCadastroPessoa.jsp">Cadastro de Usuários</a></li>                                                                                                    

                            <li><a href="<%=request.getContextPath()%>/conexao/listarRoteador.jsp">Cadastro de Roteadores</a></li>                                

                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Cadastro de Endereços <span class="caret"></span></a>
                                <ul class="dropdown-menu">                                    
                                    <li><a href="<%=request.getContextPath()%>/endereco/listarEnderecos.jsp">Cadastro de Ruas</a></li>                                   
                                    <li><a href="<%=request.getContextPath()%>/endereco/listarBairros.jsp">Cadastro de Bairros</a></li>                                   
                                    <li><a href="<%=request.getContextPath()%>/endereco/listarCidades.jsp">Cadastro de Cidades</a></li>                                    
                                    <li><a href="<%=request.getContextPath()%>/endereco/listarEstados.jsp">Cadastro de Estados</a></li>                                                                                                              
                                    <li><a href="<%=request.getContextPath()%>/endereco/listarPaises.jsp">Cadastro de Países</a></li>
                                </ul>
                            </li>

                            <li><a href="${pageContext.request.contextPath}/logout.jsp">Logout</a></li>	      

                        </ul>         	         
                        </form>
                    </div>
                </div>
            </nav>    
    </body>
</html>
