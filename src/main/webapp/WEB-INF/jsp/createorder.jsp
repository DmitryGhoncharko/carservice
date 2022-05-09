<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказы</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="header.jsp"></jsp:include>
                </div>
            </div>
            <c:forEach var="service" items="${requestScope.services}">
                <form action="/controller?command=addorderToClient" method="post">
                    <input hidden value="${service.id}" name="serviceId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-6">
                                    <h4 class="btn btn-primary">Назавание услуги</h4>
                                    <h4 class="btn btn-primary">${service.name}</h4>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <h4 class="btn btn-primary">Стоимость услуги(в рублях)</h4>
                                    <h4 class="btn btn-primary">${service.cost}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-primary" type="submit">
                        Добавить заказ
                    </button>
                </form>
            </c:forEach>
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="footer.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
