'use strict';

(function () {
  var COLORS = ['lightpink', 'lightblue', 'lightyellow', 'lightgreen', 'lightgrey', 'orange']; // массив цветов кругов меню
  var COLORS_AFTER = ['white', 'white', 'white', 'white', 'white', 'white'];
  var TEXT_BEFORE = ['Аренда студии', 'Школа лепки', 'Творчество для детей', 'Творчество для взрослых', 'Семейное творчество', 'Праздники'];
  var TEXT_AFTER = ['Проведение встреч, семинаров, мастер-классов', 'Обучение искусству лепки цветов из полимерной глины (от 15 лет)', 'Проводим творческие мастер-классы для детей от 3х лет', 'Обучающие мастер-классы для взрослых по нескольким творческим направления', 'Организуем семейные творческие встречи и занятия "Мама+Я"', 'Организация детских и семейных праздников'];
  var LINKS = ['/PalchikiStudio/mock', '/PalchikiStudio/mock', '/PalchikiStudio/mock', '/PalchikiStudio/mock', '/PalchikiStudio/mock', '/PalchikiStudio/mock'];

  function cloneCircles() { // клонируем круги меню
    var fragment = document.createDocumentFragment();

    for (var i = 0; i < 6; i++) {
      var circleElement = circleTemplate.cloneNode(true);// клонируем шаблон
      circleElement.querySelector('.circleOut').id = i; // присваиваем id каждому кружку меню
      circleElement.querySelector('.similar-circle').textContent = TEXT_BEFORE[i]; // заполняем первоначальным текстом
      circleElement.querySelector('.circleOut').style = 'background-color: ' + COLORS[i]; // заполняем цвета кружков
      fragment.appendChild(circleElement);
    }
    similarCircleElement.appendChild(fragment);
  }

  function onCircleMouse(evt) { // замена текста при наведении мышки на круг меню
    var circleOut = evt.currentTarget;
    var id = circleOut.id;
    var circleIn = circleOut.querySelector('.circleIn');
    circleIn.textContent = TEXT_AFTER[id];
    circleOut.style = 'background-color: ' + COLORS_AFTER[id];
  }

  function outCircleMouse(evt) { // замена текста при отведении мыши с круга меню
    var circleOut = evt.currentTarget;
    var id = circleOut.id;
    var circleIn = circleOut.querySelector('.circleIn');
    circleIn.textContent = TEXT_BEFORE[id];
    circleOut.style = 'background-color: ' + COLORS[id];
  }
  function clickCircleMouse(evt) { // при клике перейти по ссылке соответсвующей кругу
    for (var i = 0; i < 7; i++) {
      var circleOut = evt.currentTarget;
      var id = circleOut.id;
      location.href = LINKS[id];
    }
  }
  var circleTemplate = document.querySelector('#similarCircleTemplate').content; // тег(шаблон), который мы хотим клонировать
  var similarCircleElement = document.querySelector('#similar-circle-list'); // обращаемся к элементу, в котором будут все склонированные копии
  cloneCircles(); // клонируем кружки меню

  var circles = document.querySelectorAll('.circleOut');
  for (var i = 0; i < circles.length; i++) {
    circles[i].addEventListener('mouseover', onCircleMouse);
    circles[i].addEventListener('mouseout', outCircleMouse);
    circles[i].addEventListener('click', clickCircleMouse);
  }


})();

