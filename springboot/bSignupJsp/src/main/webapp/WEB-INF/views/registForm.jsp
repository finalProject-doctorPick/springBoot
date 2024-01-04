<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원가입 화면</title>
  </head>
  <body>
    <h2>회원가입</h2>

    <form action="/signup/register" method="post">
      <label for="id">ID:</label>
      <input type="text" id="id" name="id" required /><br />

      <label for="pass">Password:</label>
      <input type="password" id="pass" name="pass" required /><br />

      <label for="name">Name:</label>
      <input type="text" id="name" name="name" required /><br />

      <label for="name">seq:</label>
      <input type="text" id="seq" name="seq" required /><br />

      <label for="name">seq:</label>
      <input type="text" id="sibal" name="sibal" required /><br />

      <button type="submit">가입</button>
    </form>
  </body>
</html>
