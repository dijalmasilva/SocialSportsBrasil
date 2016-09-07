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
                <c:forEach items="${visitantes}" var="visit">
                    <div class="card">
                        <h4>${visit.nome}</h4>
                    </div>
                </c:forEach>
            </c:if>
        </aside>
        <c:if test="${result != null}">
            <%@include file="notification.jsp" %>
        </c:if>
        <%@include file="modalImagem.jsp" %>
    </body>
</html>
