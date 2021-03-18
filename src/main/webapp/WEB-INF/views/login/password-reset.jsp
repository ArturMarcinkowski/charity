<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>



</header>

<section class="login-page" id="password-form">
    <h2>Ustaw nowe hasło</h2>
    <form method="post">
        <div class="form-group">
            <input type="password" name="password" placeholder="hasło" />
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="powtórz hasło" />
        </div>
        <div class="form-group">
            <input type="hidden" name="id" value="${id}" />
        </div>
        <div class="form-group">
            <h3>${message}</h3>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zmień hasło</button>
        </div>
    </form>
</section>

<%@include file="../footer.jsp" %>