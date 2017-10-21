<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">
        body {
            height: 100%;
            font-size: 14px;
        }

        label {
            padding-right: 50px;
        }

        input {
            width: 150px;
        }

        select {
            width: 150px;
        }

        .field {
            clear: both;
            text-align: right;
            line-height: 25px;
        }

        .main {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="main">
    <form method="post">
        <div class="field">
            <p><label for="a">Хочу получить список вакансий с </label>
                <select name="strategy" id="a">
                    <option disabled>Выбери где искать</option>
                    <option value="jobs">jobs.tut.by</option>
                    <option value="belmeta">belmeta.com</option>
                    <option value="all">Отовсюду</option>
                </select>
            </p>
        </div>
        <div class="field">
            <p><label for="b">Ищу работу</label>
                <input type="search" name="vacancyName" placeholder="Название вакансии" id="b" required>
            </p>
        </div>
        <div class="field">
            <p><label for="c">В замечательном городе</label>
                <input type="search" name="cityName" placeholder="Введите город"
                       title="Работает только с областными центрами" id="c" required>
            </p>
        </div>
        <div class="field">
            <p><label for="d">Показать только с ЗП</label>
                <input type="checkbox" name="isSalary" id="d" width="500" >
            </p>
        </div>
        <p align="center"><input type="submit" value="Надеюсь мне повезет"></p>
    </form>
</div>
</body>
</html>