<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=500, initial-scale=1">
  <link rel="stylesheet" href="resources/css/style.css" media="(min-width:481px)">
  <link rel="stylesheet" href="resources/css/style_small.css" media="(max-width:480px)">
  <link href="https://fonts.googleapis.com/css?family=Lobster&amp;subset=cyrillic,cyrillic-ext" rel="stylesheet">
  <title>Творческая Студия "Пальчики"</title>
  <script src="js/phonemask.js"></script>
  <script src="https://use.fontawesome.com/661a533fef.js"></script>
</head>
<body>
  <div class="container">
    <div class="header">
      <div class="logo"><img src="/PalchikiStudio/resources/images/logo.png" alt="Пальчики"></div>
      <ul class='menuInfo'>
        <li>Главная</li>
        <li><a href="/PalchikiStudio/site/gallery">Галерея</a></li>
        <li><a href="/PalchikiStudio/site/contacts">Контакты</a></li>
        <li><a href="/PalchikiStudio/site/forecast">Анонс</a></li>
      </ul>
    </div>
    <div class="phoneCircles">
      <div class="mainCircles">
        <div id="similar-circle-list"><!--Список похожих кругов меню--></div>
      </div>
      <div><img class="phone" src="/PalchikiStudio/resources/images/phone_green.png"></div>
    </div>
    <%@ include file = "/WEB-INF/views/feedback.jsp" %>
  </div>
  <template id="similarCircleTemplate"> <!---шаблон для клонирования--->
    <div class="circleOut changeText">
      <div class="circleIn similar-circle"></div>
    </div>
  </template>
  <script src="/PalchikiStudio/js/circles.js"></script>
  <script src="/PalchikiStudio/js/load.js"></script>
  <script src="/PalchikiStudio/js/callback.js"></script>
</body>
</html>
