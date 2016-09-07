<%-- 
    Document   : home
    Created on : 20/06/2016, 23:12:43
    Author     : dijalma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="asideUser.jsp" %>
        <aside class="conteudo">
            <c:if test="${visitantes.isEmpty() == false}">
                <h3>Visitaram seu perfil hoje</h3>
                <div class="row">
                    <c:forEach items="${visitantes}" var="visit">
                        <div class="col-lg-2 col-sm-2 col-xs-2">
                            <a>
                                <img src="/user/image/${visit.id}" alt="${visit.nome}" class="img-thumbnail imgPerfil" align="up"/>
                                ${visit.nome}
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${visitantes.isEmpty() == true}">
                <h3>Ninguém visualizou seu perfil hoje.</h3>
            </c:if>
        </aside>
        <c:if test="${result != null}">
            <%@include file="notification.jsp" %>
        </c:if>
        <%@include file="modalImagem.jsp" %>
    </body>
</html>
