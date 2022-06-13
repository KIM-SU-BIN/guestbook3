<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<!-- 글쓰기 테이블 -->
	<form action="write" method="get">
		<table border="1" width="500">
			<tr>
				<td>이름</td>
				<td>//값이 없으면 안 써도 됨! <input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="10" cols="80" name="content"
						cols=60 rows=5> </textarea></td>
			</tr>
			<tr>
				<td colspan="4" align=right>
					<button type="submit">확인</button>
				</td>
			</tr>
		</table>
	</form>
	<br>
	<br>


	<!-- 글 출력 -->
	<c:forEach items="${requestScope.mainList}" var="mainVo">
		<table width=510 border=1>
			<tr>
				<td width="50px">${mainVo.mainNo}</td>
				<td width="150px">${mainVo.mainName}</td>
				<td width="300px">${mainVo.mainDate}</td>
				<td>
				<a href="deleteForm/${mainVo.no }">삭제</a>
				</td>
			</tr>
			<tr>
				<td colspan="4" width="590px">
					<p>${mainVo.mainContent()}</p>
				</td>
			</tr>
		</table>
		<br>
	</c:forEach>






</body>
</html>