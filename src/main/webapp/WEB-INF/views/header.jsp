<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page" id="section1">
    <nav class="container container--70">
        <ul class="nav--actions">
            <sec:authorize access="!isAuthenticated()">
            <li><a href="/login#login-form" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register#register-form" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="/profile/" class="btn btn--small btn--without-border">Profil</a></li>
                <li><a href="/logout" class="btn btn--small btn--without-border">Wyloguj</a></li>
            </sec:authorize>
        </ul>

        <ul>
            <li><a href="/#section1" class="btn btn--without-border active">Start</a></li>
            <li><a href="/#section2" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/#section3" class="btn btn--without-border">O nas</a></li>
            <li><a href="/#section4" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/#section5" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>


