<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>



</header>

<section class="login-page" id="edit-institution">
    <c:if test="${inscription == 'EDIT'}"><h2>Edytuj fundację ${institution.name}</h2></c:if>
    <c:if test="${inscription == 'ADD'}"><h2>Dodaj fundację</h2></c:if>

    <form:form method="post" modelAttribute="institution">
        <div class="form-group">
            <form:hidden path="id"/>
            <form:input path="name" placeholder="nazwa"/>
        </div>
        <div>
            <form:errors path="name"/>
        </div>

        <div class="form-group">
            <form:textarea path="description" rows="5"/>
        </div>
        <div>
            <form:errors path="description"/>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit" >Zapisz</button>
            <c:if test="${inscription == 'EDIT'}">
                <a href="/institution/delete?id=${institution.id}#delete-institution" class="btn btn--without-border">Usuń fundację</a>
            </c:if>
        </div>
    </form:form>
</section>


<%@include file="../footer.jsp" %>