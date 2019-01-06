<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=500, initial-scale=1">
  <link rel="stylesheet" href="resources/css/style.css" media="(min-width:481px)">
  <link rel="stylesheet" href="resources/css/style_small.css" media="(max-width:480px)">
  <link href="https://fonts.googleapis.com/css?family=Lobster&amp;subset=cyrillic,cyrillic-ext" rel="stylesheet">
    <title>Творческая Студия "Пальчики"</title>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <script src="js/phonemask.js"></script>
  <script src="https://use.fontawesome.com/661a533fef.js"></script>
<body>
  <div class="container">
    <div class="shortMenu"><img src="resources/images/menu_.png"></div>
    <div class="header">
      <div class="logo"><a href="/PalchikiStudio/"><img src="resources/images/logo.png" alt="Пальчики"></a></div>
      <ul class='menuInfo'>
        <li><a href="/PalchikiStudio/">Главная</a></li>
        <li><a href="/PalchikiStudio/parties">Галерея</a></li>
        <li>Контакты</li>
        <li><a href="/PalchikiStudio/forecast">Анонс</a></li>
      </ul>
    </div>

    <div class="map">
      <iframe style="width:100%" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2819.319360508437!2d39.11062051545015!3d45.03874077909825!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40f05afc8d5dc3a1%3A0xc02bc33bb9e13e5b!2z0YPQuy4g0KHQvtGA0LzQvtCy0YHQutCw0Y8sIDIwOCwg0JrRgNCw0YHQvdC-0LTQsNGALCDQmtGA0LDRgdC90L7QtNCw0YDRgdC60LjQuSDQutGA0LDQuSwgMzUwMDg4!5e0!3m2!1sru!2sru!4v1512636866176"></iframe> <br>
    </div>

    <footer>
      <ul class="contactInfo">
        <li class="contacts">Краснодар<br>ул. Сормовская, 208 </li>
        <li class="contacts">Контактный телефон:<br> 8-905-494-34-44<br> 8-989-810-00-32 </li>
        <li class="contacts">Мы в Инстаграме<br><a href="https://www.instagram.com/palchikistudio/" class="social-btn social-btn-ig"><img src="resources/images/insta.png" width="45" height="35" alt="Инстаграм"></a></li>
      </ul>
    </footer>

    <div class="phone"><img src="resources/images/phone_green.png"></div>

    <%@ include file = "feedback.jsp" %>
  </div>
  <script src="js/callback.js"></script>
  <script src="js/shortmenu.js"></script>
</body>




















