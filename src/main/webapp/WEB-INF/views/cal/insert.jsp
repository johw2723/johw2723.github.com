<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
 <title>계산기</title>
</head>
<body>

<div id="root">
 <header>
  <h1>계산하기</h1>
 </header>

<hr/>

<section id="container">
 
	<form role="form" method="post" autocomplete="off">
		<table>
			<tr>
				<th>OP1</th>
				<td>
					<input type="number" id="op1" name="op1" />
				</td>
			</tr>
			<tr>
            	<th>OP</th>
            	<td>
            		<select name="op">
						<option value="add">+</option>
						<option value="sub">-</option>
						<option value="mul">*</option>
						<option value="div">/</option>
					</select>
            	</td>
			</tr>
			<tr>
            	<th>OP2</th>
            	<td>
            		<input type="number" id="op2" name="op2" />
            	</td>
			</tr>
		</table>
		<br>
		<button type="submit">계산</button>  
	</form>

 </section>

<hr/>

 <footer>
  <a href="../">돌아가기</a>
 </footer>

</div>

</body>
</html>