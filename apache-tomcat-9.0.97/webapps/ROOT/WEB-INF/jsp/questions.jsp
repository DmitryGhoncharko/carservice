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
  <title>Вопросы</title>
</head>
<body>
<div class="container-fluid flex">
  <div class="row">
    <div class="col-md-12">
      <jsp:include page="header.jsp"></jsp:include>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12 my-3">
      <a href="/controller?command=addQuestion&testId=${requestScope.testId}" class="btn btn-success">Добавить вопрос</a>
    </div>
  </div>
  <div class="row h100">
    <div class="col-md-8 offset-md-2 my-4">
      <h2 class="text-center">Список вопросов</h2>
      <c:if test="${not empty questions}">
        <ul class="list-group">
          <c:forEach var="question" items="${questions}">
            <li class="list-group-item d-flex justify-content-between align-items-center">
              <span>${question.question}</span>
              <div>
                <a href="/controller?command=editQuestion&id=${question.id}" class="btn btn-primary btn-sm">Редактировать</a>
                <a href="/controller?command=deleteQuestion&id=${question.id}" class="btn btn-danger btn-sm">Удалить</a>
              </div>
            </li>
          </c:forEach>
        </ul>
      </c:if>
      <c:if test="${empty questions}">
        <p class="text-center">Вопросов пока нет.</p>
      </c:if>
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
