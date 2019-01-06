function closeShortMenuClickHandler() {
  header.style = 'display: none';
  shortMenu.addEventListener('click', openShortMenuClickHandler);
  shortMenu.removeEventListener('click', closeShortMenuClickHandler);
}

function openShortMenuClickHandler() {
  header.style = 'display: block; position: absolute; background-color:lightgrey; border-radius:2%';
  shortMenu.addEventListener('click', closeShortMenuClickHandler);
  shortMenu.removeEventListener('click', openShortMenuClickHandler);
}
var shortMenu = document.querySelector('.shortMenu');
var header = document.querySelector('.header');
shortMenu.addEventListener('click', openShortMenuClickHandler);
