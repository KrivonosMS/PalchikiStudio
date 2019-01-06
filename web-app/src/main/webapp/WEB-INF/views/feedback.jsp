<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="callback hidden">
  <i class="fa fa-window-close"></i>
  <div id="alarm"></div>
  <h4 style="color:darkolivegreen">Остались вопросы? <br> Мы перезвоним Вам!</h4>

  <form id="feedback-form" action="" method="post">
    <p>
      <label class="label">Ваше имя</label>
      <input class="i.nput" type="text" name="name" placeholder="Введите ваше имя"/>
    </p>
    <p>
      <label class="label">Номер телефона</label><br><label class="label"></label>
      <span>+7</span><input type="tel" name="tel"  id="phone" class="input telplace" placeholder="Введите номер без +7" />
    </p>
    <p>
      <label class="label">Ваш email</label>
      <input class="input" type="text" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" name="email" placeholder="Введите ваш email" />
    </p>
    <p>
      <label class="label">Текст сообщения:</label>
      <textarea class="input textarea" name="message" rows="10"></textarea>
    </p>
    <input name="bezspama" type="text" style="display:none" value="" />
    <input id="btn" type="submit" value="Отправить">
  </form>
</div>