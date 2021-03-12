<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
</header>

<section class="login-page" id="register-form">
    <h2>Ustawienia konta</h2>
    <form:form method="post" modelAttribute="user">

        <div class="form-group">
            <form:input path="email" placeholder="email" type="email"/>
        </div>
        <div>
            <form:errors path="email"/>
        </div>

        <div class="form-group">
            <form:hidden path="id"/>
            <form:input path="username" placeholder="nick"/>
        </div>
        <div>
            <form:errors path="username"/>
        </div>

        <div class="form-group">
            <form:input path="name" placeholder="imię"/>
        </div>
        <div>
            <form:errors path="name"/>
        </div>

        <div class="form-group">
            <form:input path="surname" placeholder="nazwisko"/>
<%--            <form:hidden path="password" type="password"/>--%>
        </div>
        <div>
            <form:errors path="surname"/>
        </div>



        <div class="form-group form-group--buttons">
<%--            <a href="/login" class="btn btn--without-border">Zapisz ustawienia</a>--%>
            <button class="btn" type="submit" >Zapisz ustawienia</button>
        </div>
    </form:form>
</section>


<%@include file="footer.jsp" %>