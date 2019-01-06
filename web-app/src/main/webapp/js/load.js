'use strict';
(function () {
  window.load = function (options, successHandler, errorHandler) {
    var xhr = new XMLHttpRequest();

    xhr.addEventListener('load', function () {
      if (xhr.status === 200) {
        if (successHandler) {
            successHandler(xhr.response);
        }
      } else {
        if (errorHandler) {
            errorHandler('Неизвестный статус: ' + xhr.status + ' ' + xhr.statusText);
        }
      }
    });

    xhr.addEventListener('error', function () {
      if (errorHandler) {
        errorHandler('Произошла ошибка соединения');
      }
    });

    xhr.addEventListener('timeout', function () {
      if (errorHandler) {
        errorHandler('Запрос не успел выполниться за ' + xhr.timeout + 'мс');
      }
    });

    xhr.timeout = 10000;

    xhr.open(options.method, options.url);
    if (options.type) {
        xhr.setRequestHeader('Content-Type', options.type);
    }
    xhr.send(options.body);
  };
})();

