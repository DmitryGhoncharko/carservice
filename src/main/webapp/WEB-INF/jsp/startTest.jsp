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

  .question-item {
    margin-bottom: 20px;
  }

  .answer-item {
    margin: 5px 0;
  }
</style>
<html>
<head>
  <title>Тестирование</title>
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
      <h2>Тест</h2>
      <form id="testForm" class="needs-validation" novalidate>
        <!-- Перебор вопросов и ответов -->
        <c:forEach var="entry" items="${data}">
          <div class="question-item">
            <h4>${entry.key}</h4> <!-- Текст вопроса -->
            <c:forEach var="answer" items="${entry.value}">
              <div class="form-check answer-item">
                <input class="form-check-input" type="radio" name="question_${entry.key}" value="${answer.id}" id="answer_${answer.id}" data-correct="${answer.correct}">
                <label class="form-check-label" for="answer_${answer.id}">
                    ${answer.answer}
                </label>
              </div>
            </c:forEach>
          </div>
        </c:forEach>
        <button type="button" class="btn btn-primary" onclick="checkAnswers()">Завершить тест</button>
      </form>
      <div id="result" class="mt-3" style="display:none;">
        <h4>Результат: <span id="score"></span> из <span id="total"></span></h4>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <jsp:include page="footer.jsp"></jsp:include>
    </div>
  </div>
</div>

<script>
  // Функция для проверки правильных ответов
  function checkAnswers() {
    let correctAnswers = 0;
    const formElements = document.getElementById('testForm').elements;
    const totalQuestions = document.querySelectorAll('.question-item').length;

    // Перебираем все вопросы и проверяем правильность ответов
    for (let i = 0; i < formElements.length; i++) {
      const questionElement = formElements[i];
      if (questionElement.type === "radio" && questionElement.checked) {
        const isCorrect = questionElement.getAttribute("data-correct") === "true";
        if (isCorrect) {
          correctAnswers++;
        }
      }
    }

    // Отображаем результат
    document.getElementById('score').innerText = correctAnswers;
    document.getElementById('total').innerText = totalQuestions;
    document.getElementById('result').style.display = "block";
  }
</script>
</body>
</html>
