<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:hidden path="id"/>
            <form:input path="username" placeholder="username"/>
        </div>
        <div>
            <form:errors path="username"/>
        </div>
        <div class="form-group">
            <form:input path="password" placeholder="password"/>
        </div>
        <div>
            <form:errors path="password"/>
        </div>
        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit" value="Register">Załóż konto</button>
        </div>
    </form:form>
</section>


<%@include file="footer.jsp" %>