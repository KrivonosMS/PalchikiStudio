'use strict';

(function () {
  var COLORS = ['lightpink', 'lightblue', 'lightyellow', 'lightgreen', 'lightgrey', 'orange'];
  var COLORS_AFTER = ['white', 'white', 'white', 'white', 'white', 'white'];
  var TEXT_BEFORE = ['Аренда студии', 'Школа лепки', 'Творчество для детей', 'Творчество для взрослых', 'Семейное творчество', 'Праздники'];
  var TEXT_AFTER = ['Проведение встреч, семинаров, мастер-классов', 'Обучение искусству лепки цветов из полимерной глины (от 15 лет)', 'Проводим творческие мастер-классы для детей от 3х лет', 'Обучающие мастер-классы для взрослых по нескольким творческим направления', 'Организуем семейные творческие встречи и занятия "Мама+Я"', 'Организация детских и семейных праздников'];
  var LINKS = ['/PalchikiStudio/site/mock', '/PalchikiStudio/site/mock', '/PalchikiStudio/site/mock', '/PalchikiStudio/site/mock', '/PalchikiStudio/site/mock', '/PalchikiStudio/site/mock'];

  function cloneCircles() {
    var fragment = document.createDocumentFragment();

    for (var i = 0; i < 6; i++) {
      var circleElement = circleTemplate.cloneNode(true);
      circleElement.querySelector('.circleOut').id = i;
      circleElement.querySelector('.similar-circle').textContent = TEXT_BEFORE[i];
      circleElement.querySelector('.circleOut').style = 'background-color: ' + COLORS[i];
      fragment.appendChild(circleElement);
    }
    similarCircleElement.appendChild(fragment);
  }

  function onCircleMouse(evt) {
    var circleOut = evt.currentTarget;
    var id = circleOut.id;
    var circleIn = circleOut.querySelector('.circleIn');
    circleIn.textContent = TEXT_AFTER[id];
    circleOut.style = 'background-color: ' + COLORS_AFTER[id];
  }

  function outCircleMouse(evt) {
    var circleOut = evt.currentTarget;
    var id = circleOut.id;
    var circleIn = circleOut.querySelector('.circleIn');
    circleIn.textContent = TEXT_BEFORE[id];
    circleOut.style = 'background-color: ' + COLORS[id];
  }
  function clickCircleMouse(evt) {
    for (var i = 0; i < 7; i++) {
      var circleOut = evt.currentTarget;
      var id = circleOut.id;
      location.href = LINKS[id];
    }
  }
  var circleTemplate = document.querySelector('#similarCircleTemplate').content;
  var similarCircleElement = document.querySelector('#similar-circle-list');
  cloneCircles();

  var circles = document.querySelectorAll('.circleOut');
  for (var i = 0; i < circles.length; i++) {
    circles[i].addEventListener('mouseover', onCircleMouse);
    circles[i].addEventListener('mouseout', outCircleMouse);
    circles[i].addEventListener('click', clickCircleMouse);
  }


})();

