<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회원 가입</title>
<!-- 외부 CSS 연결 -->
<!-- <link href='css/myform.css' rel='stylesheet' type='text/css'>  -->

</head>

<body>
<form action="" method="">
<fieldset>
<legend>기본정보</legend>
<ol>
  <li>
    <label for="empno">사번</label>
    <input id="empno" type="text" required>
  </li>
  <li>
    <label for="ename">사원명</label>
    <input id="ename" type="text"  required>
  </li>
  <li>
    <label for="deptno">부서번호</label>
    <select id='deptno'></select>
  </li>  
 </ol>
</fieldset>

<fieldset>
<legend>추가정보</legend>
<ol>
  <li>
    <label for="job">업무</label> 
    <input id="job" type="text">
  </li>
  <li>
    <label for="sal">월급</label>
    <input id="sal" type="text">
  </li>
</ol>
</fieldset>
<fieldset>
  <button type="submit"> 제출 </button> 
</fieldset>
</form>
</body>
</html>
