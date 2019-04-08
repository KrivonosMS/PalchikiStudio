'use strict';
// $(document).ready(function(){
//   jQuery(function($){
//     $("#phone").mask("(999) 999-99-99");
//   });
// });


(function () {
  var URL = 'site/feedback';

  function closeIconHandler() {
    callbackFormElement.classList.add('hidden');
    iconPhoneElement.classList.remove('hidden');
  }

  function phoneIconHandler() {
    iconPhoneElement.classList.add('hidden');
    callbackFormElement.classList.remove('hidden');
  }

  function sendFormHandler(evt) {
    evt.preventDefault();

    var formElement = document.querySelector('.callback form');
    var formData = new FormData(formElement);
    var data = formData.entries();
    var obj = data.next();
    var retrieved = {};
    while(undefined !== obj.value) {
        retrieved[obj.value[0]] = obj.value[1];
        obj = data.next();
    }
    var urlEncodedData = "";
    var urlEncodedDataPairs = [];
    for(var name in retrieved) {
        urlEncodedDataPairs.push(encodeURIComponent(name) + '=' + encodeURIComponent(retrieved[name]));
    }
    urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
    let options = {};
    options.body = urlEncodedData;
    options.url = URL;
    options.method = 'POST';
    options.type = 'application/x-www-form-urlencoded';
    window.load(options, successHandler, errorHandler);
  }
  var errorHandler = function (message) {
    alarmElement.innerHTML = message;
  };

  var successHandler = function (message) {
    alarmElement.innerHTML = message;
  };

  var iconPhoneElement = document.querySelector('.phone'); // значок телефонной трубки
  var callbackFormElement = document.querySelector('.callback');
  iconPhoneElement.addEventListener('click', phoneIconHandler); // кликаем по значку телтрубки и вызываем колбэк

  var closeIconElement = document.querySelector('.fa-window-close');
  closeIconElement.addEventListener('click', closeIconHandler);

  var alarmElement = document.querySelector('#alarm');

  var sendButtonElement = document.querySelector('#feedback-form');

  sendButtonElement.addEventListener('submit', sendFormHandler);
})();

