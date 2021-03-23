<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>
<div class="slogan container container--90">
    <div class="slogan--item">
        <h1>
            Twój profil
        </h1>
        <div class="stats--item">
            <em>${bagsCount}</em>
            <h3>Oddanych worków</h3>
        </div>
        <div class="stats--item">
            <em>${donationsCount}</em>
            <h3>Przekazanych darów</h3>
        </div>
    </div>
</div>
</header>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <section class="login-page" id="admin-panel">
        <h2>Panel Administratora</h2>
        <h3>Strona posiada ${institutionCount} fundacji</h3>
        <a href="/institution/list#institution-list" class="btn btn--small">Szczegóły</a>
        <h3>W bazie danych jest ${userCount} użytkowników</h3>
        <a href="/user/list#user-list" class="btn btn--small">Szczegóły</a>
        <h3>Z czego ${adminCount} administratorów</h3>
        <a href="/admin/list#user-list" class="btn btn--small">Szczegóły</a>
    </section>
</sec:authorize>

<section class="help" id="user-account">
    <h2>Dane konta</h2>
    <ul>
        <li>Email: ${user.email}</li>
        <li>Login: ${user.username}</li>
        <li>Imię: ${user.name}</li>
        <li>Nazwisko: ${user.surname}</li>
    </ul>
    <a href="/profile/edit#edit-form" class="btn btn--small">Edytuj dane</a>
    <a href="/profile/password-change#password-form" class="btn btn--small">Zmień hasło</a>
</section>

<section class="help" id="donation-list">
    <h2>Lista darów</h2>
    <table id="list-table">
        <tr>
            <th>Fundacja</th>
            <th>Kategorie</th>
            <th>Adres</th>
            <th>Ilość Worków</th>
            <th>Szczegóły</th>
        </tr>
        <c:forEach items="${donations}" var="donation">
        <tr>
            <td>${donation.institution.name}</td>
            <td><c:forEach items="${donation.categories}" var="category"> ${category.name},  </c:forEach></td>
            <td>${donation.city} ${donation.street} ${donation.zipCode}</td>
            <td>${donation.quantity}</td>
            <td><a href="/donation/details?id=${donation.id}" class="btn btn--small">Szczegóły</a></td>
        </tr>
        </c:forEach>
    </table>
</section>

<%@include file="../footer.jsp" %>