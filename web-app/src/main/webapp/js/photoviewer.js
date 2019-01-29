'use strict';

(function () {
  var ESC_KEY = 27;
  var LINKS = [
                '/PalchikiStudio/resources/images/photoes/1.jpg',
                '/PalchikiStudio/resources/images/photoes/2.jpg',
                '/PalchikiStudio/resources/images/photoes/3.jpg',
                '/PalchikiStudio/resources/images/photoes/10.jpg',
                '/PalchikiStudio/resources/images/photoes/11.jpg',
                '/PalchikiStudio/resources/images/photoes/12.jpg'
                ];

  function renderPhotoes() {
    var similarListElement = document.querySelector('#setup-similar-list'); // обращаемся к элементу превьюшек, в котором будут все склонированные копии
    var photoShowTemplate = document.querySelector('#similarPhotoTemplate').content; // тег(шаблон) превьюшек, который мы хотим клонировать
    var fragment = document.createDocumentFragment();
    for (var i = 0; i < LINKS.length; i++) {
      var photoElement = photoShowTemplate.cloneNode(true);// клонируем шаблон превьюшек
      photoElement.querySelector('img').src = LINKS[i]; // находим элемент с тегом img и вставляем ему ссылки из массива фотографий
      photoElement.querySelector('div').id = i + 1;// присваиваем id с 1
      fragment.appendChild(photoElement);
    }
    similarListElement.appendChild(fragment);// закончили клонировать фотки
  }

  function photoClickHandler(evt) {
    wrapperElement.style.opacity = 0.3;
    closeBtn.classList.remove('visibility');
    prevBtn.classList.remove('visibility');
    nextBtn.classList.remove('visibility');
    var fragmentBig = document.createDocumentFragment();

    var photoTemplateElement = document.querySelector('#photoesTemplate').content.querySelector('.bigPic'); // обращаемся к образцу, который хотим клонировать
    var photoBigElement = photoTemplateElement.cloneNode(true);// клонируем шаблон
    photoBigElement.querySelector('.cloneImg').src = evt.currentTarget.querySelector('img').src; // меняем src
    photoBigElement.setAttribute('currentid', evt.currentTarget.id);
    fragmentBig.appendChild(photoBigElement);
    similarPhotoElement.appendChild(fragmentBig);

    document.addEventListener('keydown', escPressHandler);
    nextBtn.addEventListener('click', nextBtnClickHandler); // обработчик на кнопку след слайд
    prevBtn.addEventListener('click', prevBtnClickHandler); // обработчик на кнопку предыдущий слайд
    closeBtn.addEventListener('click', closeBtnClickHandler);
  }

  function escPressHandler(evt) {
    if (evt.keyCode === ESC_KEY) {
      similarPhotoElement.querySelector('.bigPic').remove();
      wrapperElement.style.opacity = 1;
      closeBtn.classList.add('visibility');
      prevBtn.classList.add('visibility');
      nextBtn.classList.add('visibility');
      document.removeEventListener('keydown', escPressHandler);
    }
  }
  // клонируем фотки
  renderPhotoes();

  var wrapperElement = document.querySelector('.wrapper'); // обращаемся к wrapper, на котором создаем прозрачность
  var closeBtn = document.querySelector('.closeBtn');// обращаемся к кнопке закрывания на большой фотке
  var prevBtn = document.querySelector('.prevBtn');
  var nextBtn = document.querySelector('.nextBtn');

  var similarPhotoElement = document.querySelector('#similarPhotoesList'); // сюда все будет склонировано
  var bigImage = document.querySelectorAll('.image');
  for (var j = 0; j < LINKS.length; j++) {
    bigImage[j].addEventListener('click', photoClickHandler);
  }

  function nextBtnClickHandler() { // прокрутка фотографий вправо
    var bigPic = similarPhotoElement.querySelector('.bigPic');
    var currentID = bigPic.getAttribute('currentid');
    if (currentID == LINKS.length) {
      currentID = 1;
    } else {
      currentID++;
    }
    bigPic.querySelector('.cloneImg').src = LINKS[currentID - 1];
    bigPic.setAttribute('currentid', currentID);
  }

  function prevBtnClickHandler() { // прокрутка фотографий влево
    var bigPic = similarPhotoElement.querySelector('.bigPic');
    var currentID = bigPic.getAttribute('currentid');
    if (currentID == 1) {
      currentID = LINKS.length;
    } else {
      currentID--;
    }
    bigPic.querySelector('.cloneImg').src = LINKS[currentID - 1];
    bigPic.setAttribute('currentid', currentID);
  }

  function closeBtnClickHandler() {
    similarPhotoElement.querySelector('.bigPic').remove();
    wrapperElement.style.opacity = 1;
    closeBtn.classList.add('visibility');
    prevBtn.classList.add('visibility');
    nextBtn.classList.add('visibility');
  }
})();
