<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>



</header>

<section class="login-page" id="password-form">
    <h2>Zmiana hasła</h2>
    <form method="post">
        <div class="form-group">
            <input type="password" name="oldPassword" placeholder="stare hasło" />
        </div>
        <div class="form-group">
            <input type="password" name="newPassword1" placeholder="nowe hasło" />
        </div>
        <div class="form-group">
            <input type="password" name="newPassword2" placeholder="powtórz nowe hasło" />
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