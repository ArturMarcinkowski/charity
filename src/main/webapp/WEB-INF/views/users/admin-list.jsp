<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>
</header>

<section class="login-page" id="user-list">
    <h2>Administratorzy</h2>

    <table id="list-table">
        <tr>
            <th>Login</th>
            <th>Email</th>
            <th>Imię</th>
            <th>Nazwisko</th>
            <th>Ustawienia</th>
        </tr>
        <c:forEach items="${admins}" var="user" varStatus="status">
        <div class="col">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td><a href="/user/edit?id=${user.id}#edit-user"
                       class="btn btn--small float-right">Edytuj</a>
                    <c:if test="${!status.last||!status.first}">
                        <c:if test="${user.username != activeUser.username}">
                            <a href="/user/delete?id=${user.id}#delete-user"
                               class="btn btn--small  float-right">Usuń</a></c:if></c:if>
                </td>
            </tr>
            <div/>
            </c:forEach>
    </table>

</section>


<%@include file="../footer.jsp" %>