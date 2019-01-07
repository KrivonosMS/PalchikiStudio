<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=500, initial-scale=1">
  <link rel="stylesheet" href="resources/css/style.css" media="(min-width:481px)">
  <link rel="stylesheet" href="resources/css/style_small.css" media="(max-width:480px)">
    <link href="https://fonts.googleapis.com/css?family=Lobster&amp;subset=cyrillic,cyrillic-ext" rel="stylesheet">
    <title>Творческая Студия "Пальчики"</title>
    <script src="https://use.fontawesome.com/661a533fef.js"></script>
</head>
<body>
<div class="container">
  <div class="header">
    <div class="logo"><a href="/PalchikiStudio/"><img src="resources/images/logo.png" alt="Пальчики"></a></div>
    <ul class='menuInfo'>
      <li><a href="/PalchikiStudio/">Главная</a></li>
      <li><a href="/PalchikiStudio/gallery">Галерея</a></li>
      <li><a href="/PalchikiStudio/contacts">Контакты</a></li>
      <li>Анонс</li>
    </ul>
  </div>
  <div class="phoneCircles">
    <div id="error"></div>
    <div id="meetingList"></div> <!--Список клонов встреч-->
    <div class="phone"><img src="resources/images/phone_green.png"></div>
  </div>
  <%@ include file = "feedback.jsp" %>
</div>
<template id="meetingTemplate">
  <div class="mainCircles">
    <div class="forecast_block">
      <div class="forecast_image"><img src=""></div>
      <ul class="forecast_list">
        <li id="lineFirst"><h3></h3></li>
        <li id="lineSecond"><h4></h4></li>
        <li id="lineThird"><h4></h4></li>
        <li id="lineFourth"><p></p></li>
        <li id="lineFifth"><p></p></li>
      </ul>
    </div>
  </div>
</template>
<script src="js/callback.js"></script>
<script src="js/load.js"></script>
<script src="js/shortmenu.js"></script>
<script src="js/forecast.js"></script>
</body>
</html>
