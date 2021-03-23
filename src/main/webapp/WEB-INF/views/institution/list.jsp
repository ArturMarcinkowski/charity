<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>


</header>

<section class="help" id="institution-list">

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <h2>Fundacje</h2>
        <div style="text-align:center">
            <a href="/institution/add#edit-institution" class="btn btn">Dodaj fundację</a></div>
        <ul class="help--slides-items">
            <c:forEach items="${institutions}" varStatus="i" step="2">
                <li>
                    <div class="col">
                        <div class="title">Fundacja "${institutions[i.index].name}"
                            <a href="/institution/settings?id=${institutions[i.index].id}#edit-institution"
                               class="btn btn--small btn--without-border float-right">Edytuj</a></div>
                        <div class="subtitle">Cel i misja: ${institutions[i.index].description}</div>
                    </div>
                    <c:if test="${institutions[i.index+1] != null}">
                        <div class="col">
                            <div class="title">
                                <a href="/institution/settings?id=${institutions[i.index+1].id}#edit-institution"
                                   class="btn btn--small btn--without-border float-right">Edytuj</a>
                                Fundacja "${institutions[i.index+1].name}"
                            </div>
                            <div class="subtitle">Cel i misja: ${institutions[i.index+1].description} </div>
                        </div>
                    </c:if>
                    <c:if test="${institutions[i.index+1] == null}">
                        <div class="col" style="visibility: hidden"></div>

                    </c:if>

                </li>
            </c:forEach>
        </ul>
    </div>

</section>
<%@include file="../footer.jsp" %>
