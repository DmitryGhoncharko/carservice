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
  <title>Список тестов</title>
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
      <h2 class="text-center">Список тестов</h2>
      <table class="table table-striped table-bordered">
        <thead>
        <tr>
          <th>#</th>
          <th>Название</th>
          <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="test" items="${tests}">
          <tr>
            <td>${test.id}</td>
            <td>${test.name}</td>
            <td>
              <div class="btn-group" role="group">
                <form action="/controller?command=deleteTest" method="post" style="display: inline;">
                  <input type="hidden" name="id" value="${test.id}">
                  <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                </form>
                <a href="/controller?command=editTest?id=${test.id}" class="btn btn-warning btn-sm">Редактировать</a>
                <a href="/controller?command=viewQuestions?id=${test.id}" class="btn btn-info btn-sm">Просмотреть вопросы</a>
              </div>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <jsp:include page="footer.jsp"></jsp:include>
    </div>
  </div>
</div>
</body>
</html>
