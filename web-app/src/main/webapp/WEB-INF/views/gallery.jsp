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
<div class="bigPhoto" id="similarPhotoesList"></div> <!---сюда клонируем увеличенные фотки--->
<div class="closeBtn visibility"><i class="fa fa-times" aria-hidden="true"></i></div> <!---кнопка  close для увеличенной фотки-->
<div class="prevBtn visibility"><i class="fa fa-hand-o-left" aria-hidden="true"></i></div> <!---кнопки прокрутки фото влево и вправо--->
<div class="nextBtn visibility"><i class="fa fa-hand-o-right" aria-hidden="true"></i></i></div>
<div><img class="phone" src="resources/images/phone_green.png"></div>
<%@ include file = "feedback.jsp" %>
<div class="wrapper">
      <div class="container">
        <div class="shortMenu"><img src="resources/images/menu_.png"></div>
        <div class="header">
          <div class="logo"><a href="index.jsp"><img src="resources/images/logo.png" alt="Пальчики"></a></div>
          <ul class='menuInfo'>
            <li><a href="/PalchikiStudio/">Главная</a></li>
            <li>Галерея</li>
            <li><a href="/PalchikiStudio/contacts">Контакты</a></li>
            <li><a href="/PalchikiStudio/forecast">Анонс</a></li>
          </ul>
        </div>
        <br>
        <div class="gallery">
          <div class="galleryInner" id="setup-similar-list"><!--сюда клонируем фото-превьюшек--></div>
        </div>
      </div>
      </div>
<template id="similarPhotoTemplate">
  <div class="image"><img src="resources/images/photoes/1.jpg" style="width: 100%; margin: auto; border-radius: 2%;"></div> <!--клонируем превьюшки (шаблон)-->
</template>
<template id="photoesTemplate">
  <div class="bigPic"><img class="cloneImg fade" src="resources/images/photoes/1.jpg" style="width:60%; border-radius: 2%;"></div><!---клонируем увеличенные фотки (шаблон)--->
</template>
  <script src="js/shortmenu.js"></script>
  <script src="js/photoviewer.js"></script>
  <script src="js/callback.js"></script>
</body>
</html>
