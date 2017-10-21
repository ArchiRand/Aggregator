<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://myaggregator.by/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список вакансий</title>
    <style>
        <%@include file="resources/forResult.css"%>
    </style>
</head>
<body>
<form>
    <table cellspacing='0' align="center" width="85%">
        <tr>
            <th align="left"><a href="${pageContext.request.contextPath}/">Вернуться на страницу поиска</a></th>
            <th align="right">Сортировать по
                <select name="sort">
                    <option value="date">дате</option>
                    <option value="salaryDown">убыванию ЗП</option>
                    <option value="salaryUp">возрастанию ЗП</option>
                </select>
                <input type="submit" value="Сортировать">
            </th>
        </tr>
    </table>
</form>

<br>
<table class="table" cellspacing='0' align="center">
    <thead>
    <tr>
        <th>Название</th>
        <th>Город</th>
        <th>Компания</th>
        <th>Зарплата</th>
        <th>Дата размещения</th>
        <th>Сайт</th>
    </tr>
    </thead>
    <jsp:useBean id="vacancy" class="by.myaggregator.jobs.dto.Vacancy">
        <c:forEach items="${vacancies}" var="vacancy">
            <tr>
                <td><a href="${vacancy.url}" target="_blank">${vacancy.title}</a></td>
                <td>${vacancy.city}</td>
                <td>${vacancy.companyName}</td>
                <td>${vacancy.salary}</td>
                <td>${fn:formatDate(vacancy.date)}</td>
                <td><a href="${vacancy.siteName}">${vacancy.siteName}</a></td>
            </tr>
        </c:forEach>
    </jsp:useBean>
</table>

<div align="center">
    <c:if test="${currentPage != 1}">
        <a href="${pageContext.request.contextPath}/?page=${currentPage - 1}">Prev</a>
    </c:if>
    <c:forEach begin="1" end="${lastPage}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                ${i}
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage != lastPage}">
        <a href="${pageContext.request.contextPath}?page=${currentPage + 1}">Next</a>
    </c:if>
</div>
</body>
</html>
