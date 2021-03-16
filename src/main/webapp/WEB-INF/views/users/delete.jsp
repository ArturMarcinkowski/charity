<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>



</header>


<section class="login-page" id="delete-institution">
        <h2>
            Czy jesteś pewień, że chcesz usunąć użytkownika ${user.username}?
        </h2>

        <div class="form-group form-group--buttons">
            <a href="/user/delete/confirm?id=${user.id}" methods="post" class="btn btn--without-border">Usuń</a>
            <a href="/user/list" class="btn btn--without-border">Cofnij</a>
        </div>

</section>



<%@include file="../footer.jsp" %>