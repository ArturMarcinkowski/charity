<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>



</header>


<section class="login-page" id="delete-institution">
        <h2>
            Czy jesteś pewień, że chcesz usunąć fundację ${institution.name}?
        </h2>

        <div class="form-group form-group--buttons">
            <a href="/institution/delete/confirm?id=${institution.id}" methods="post" class="btn btn--without-border">Usuń fundację</a>
            <a href="/institution/settings?id=${institution.id}#edit-institution" class="btn btn--without-border">Cofnij</a>
        </div>

</section>



<%@include file="../footer.jsp" %>