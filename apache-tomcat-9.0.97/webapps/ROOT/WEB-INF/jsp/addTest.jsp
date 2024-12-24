<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<style>
    .flex {
        display: flex;
        flex-direction: column;
        height: 100vh;
    }

    body {
        margin: 0;
        padding: 0;
    }

    .h100 {
        flex-grow: 3;
    }
</style>
<html>
<head>
    <title>Добавить тест</title>
</head>
<body>
<div class="container-fluid flex">
    <div class="row">
        <div class="col-md-12">
            <jsp:include page="header.jsp"></jsp:include>
        </div>
    </div>
    <div class="row h100">
        <div class="col-md-8 offset-md-2 my-4">
            <h2 class="text-center">Добавить тест</h2>
            <form action="/controller?command=addTest" method="post" class="needs-validation" novalidate>
                <div class="mb-3">
                    <label for="testName" class="form-label">Название теста</label>
                    <input type="text" class="form-control" id="testName" name="testName" required>
                    <div class="invalid-feedback">
                        Пожалуйста, введите название теста.
                    </div>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </div>
</div>
<script>
    // Bootstrap validation
    (function () {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>
</body>
</html>
