<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원 목록</title>
  </head>
  <body>
    <h2>회원리스트</h2>
    가입완료!
    <!-- <table border="1">
      <tr>
        <th bgcolor="orange" width="100">ID</th>
        <th bgcolor="orange" width="200">비밀번호</th>
        <th bgcolor="orange" width="150">이름</th>
      </tr>
      <c:forEach items="${memberList}" var="member">
        <tr>
          <td>${member.id}</td>
          <td>${member.pass }</td>
          <td>${member.name }</td>
        </tr>
      </c:forEach>
    </table> -->
  </body>
</html>
