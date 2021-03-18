<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>



</header>

<section class="login-page" id="password-form">
    <h2>Przywróć hasło</h2>
    <form method="post">
        <div class="form-group">
            <input type="text" name="email" placeholder="email" />
        </div>
        <div class="form-group form-group--buttons">
            <a href="/login/password#password-form" class="btn btn--without-border">Powrót</a>
            <button class="btn" type="submit" >Przywróć hasło</button>
        </div>
    </form>
</section>

<%@include file="../footer.jsp" %>