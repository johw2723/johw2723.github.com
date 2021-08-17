<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
 <title>결과</title>
</head>
<body>

<div id="root">
 <header>
  <h1>수정결과</h1>
 </header>

<hr/>

${calNum}번 계산이 수정되었습니다. <br> 
${op1}  ${op}  ${op2}  =  ${result}

<hr/>

 <footer>
  <a href="../">돌아가기</a>
 </footer>

</div>

</body>
</html>