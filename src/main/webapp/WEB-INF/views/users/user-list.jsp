<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>
</header>

<section class="login-page" id="user-list">
    <h2>Użytkownicy</h2>

            <table>
                <c:forEach items="${users}" var="user">
                <div class="col">
                    <tr>
                        <th>
                            <div class="title">${user.username}
                                <div/>
                        </th>
                        <th>${user.name}</th>
                        <th><a href="/user/edit?id=${user.id}#edit-user"
                               class="btn btn--small btn--without-border float-right">Edytuj</a></th>
                        <th><a href="/user/delete?id=${user.id}#delete-user"
                               class="btn btn--small btn--without-border float-right">Usuń</a></th>
                        <c:if test="${user.enabled == 1}">
                            <th><a href="/user/block?id=${user.id}"
                                   class="btn btn--small btn--without-border float-right">Zablokuj</a></th>
                        </c:if>
                        <c:if test="${user.enabled == 0}">
                            <th><a href="/user/unblock?id=${user.id}"
                                   class="btn btn--small btn--without-border float-right">Odblokuj</a></th>
                        </c:if>


                    </tr>
                    <div/>
                    </c:forEach>
            </table>

</section>


<%@include file="../footer.jsp" %>