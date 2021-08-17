<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
 <title>계산기</title>
</head>
<body>

<div id="root">
 <header>
  <h1>조회하기</h1>
 </header>

<hr/>

<form role="form" method="post" autocomplete="off">
	<table>
		<tr>
			<th>NO</th>
			<td>
				<input type="number" id="calNum" name="calNum" />
			</td>
		</tr>
	</table>
	<br>
	<button type="submit">조회</button>	  
</form>

 <footer>
  <a href="../">돌아가기</a>
 </footer>

</div>

</body>
</html>