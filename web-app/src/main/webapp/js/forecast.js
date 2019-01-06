'use strict';

(function () {
  var successHandler = function (message) {
    var meetings = JSON.parse(message);
    var meetingTemplate = document.querySelector('#meetingTemplate').content;
    var fragment = document.createDocumentFragment();

    for (var i = 0; i < meetings.length; i++) {
      var meetingElement = meetingTemplate.cloneNode(true);
      meetingElement.querySelector('#lineFirst').innerHTML = meetings[i].name;
      meetingElement.querySelector('#lineSecond').innerHTML = meetings[i].date;
      meetingElement.querySelector('#lineThird').innerHTML = meetings[i].teacher_name;
      meetingElement.querySelector('#lineFourth').innerHTML = meetings[i].coast + ' &#8381;';
       meetingElement.querySelector('#lineFifth').innerHTML = meetings[i].description;
      meetingElement.querySelector('.forecast_image img').src = meetings[i].img_path;
      fragment.appendChild(meetingElement);
    }
    document.querySelector('#meetingList').appendChild(fragment);
  };

  var errorHandler = function () {
    document.querySelector('#error').innerHTML = 'Непредвиденная внутренняя ошибка. Обратитесь к разработчику :)';
  };

  let options = {};
  options.url = 'announcement';
  options.body = '';
  options.method  = 'GET';
  window.load(options, successHandler, errorHandler);
})();
