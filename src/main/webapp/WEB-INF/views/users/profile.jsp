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



<section class="login-page" id="donation-list">
    <h2>Lista darów</h2>
    <table>
        <tr>
            <th>ilość worków</th>
            <th>kategorie</th>
            <th>fundacja</th>
            <th>Szczegóły</th>
        </tr>
        <c:forEach items="${donations}" var="donation">
        <tr>
            <td>${donation.quantity}</td>
            <td><c:forEach items="${donation.categories}" var="category"> ${category.name},  </c:forEach></td>
            <td>${donation.institution.name}</td>
            <td><a href="/donation/details?id=${donation.id}" class="btn btn--small">Szczegóły</a></td>
        </tr>
        </c:forEach>



    </table>



</section>

<section class="login-page" id="user-account">
    <h2>Dane konta</h2>
    <ul>
        <li>Email: ${user.email}</li>
        <li>Login: ${user.username}</li>
        <li>Imię: ${user.name}</li>
        <li>Nazwisko: ${user.surname}</li>

    </ul>
    <a href="/profile/edit#edit-form" class="btn btn--small">Zmień dane</a>


</section>


<%@include file="../footer.jsp" %>