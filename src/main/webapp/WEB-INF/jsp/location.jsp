
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Наше местоположение</title>
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
            <div class="row">
                <div class="col-md-12">
                    <iframe src="https://yandex.ru/map-widget/v1/?um=constructor%3Adfc5b3f8aff32c38281ac3f21af45ce1c1bac871a2fa31be22cbd339a8838c12&amp;source=constructor" width="100%" height="720" frameborder="0"></iframe>
                </div>
            </div>
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
