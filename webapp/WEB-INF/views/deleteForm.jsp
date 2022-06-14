<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/guestbook3/delete/${mainVo.no}" method="get">
		<input type="hidden" name="password" value="${no} }">
		비밀번호 <input type="password" name="password" value="">
		<button type="submit">확인</button>
		
		<br><br> <a href="/guestbook3/addList">메인으로 돌아가기</a>
	</form>

</body>
</html>