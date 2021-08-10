<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Join Member</title>
	<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../script/Member.js"></script>
	<script type="text/javascript" src="../script/ajax.js"> </script>
</head>
<body onload="idCheck()">

	<!-- 회원가입 -->
	<div class="join">
	<h3>회원가입</h3>
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memId" id="insertId" maxlength="20" size="30" onblur="checkId(this)"/>
					<input type="hidden" name="checkid" value="idUncheck">
					<span id="msgData"></span><br>
					<span id="alert" style="display:none" aria-live="assertive"></span>
				</td>
			</tr>
			<tr>
            	<th>비밀번호</th>
            	<td>
            		<input type="password" name="pw" id="memPW" maxlength="20" size="30" onblur="checkPw(this)"/><br>     
					<span id="alertPW" style="display:none" aria-live="assertive"></span>
            	</td>
			</tr>
			<tr>
            	<th>비밀번호 확인</th>
            	<td>
            		<input type="password" name="pwc" id="memPWC" maxlength="20" size="30" onblur="checkPwc(this)"/><br>
					<span id="alertPWC" style="display:none" aria-live="assertive"></span>
            	</td>
			</tr>        
			<tr>
            	<th>Email</th>
            	<td>
            		<input type="text" name="email" maxlength="30" size="30" onblur="checkE(this)"/><br>
					<span id="alertE" style="display:none" aria-live="assertive"></span>
            	</td>
			</tr>
			<tr>
            	<th>전화번호</th>
            	<td>
            		<input type="text" name="phone" maxlength="13" size="30" onblur="checkP(this)"/><br>
					<span id="alertP" style="display:none" aria-live="assertive"></span>
            	</td>
			</tr>
			<tr>
            	<th>주소</th>
            	<td>
            		<input type="text" name="address" maxlength="100" size="30"/><br>
            	</td>
			</tr>            
			<tr>
            	<th>결제수단</th>
            	<td>
            		<input type="text" name="payment" maxlength="50" size="30"/><br>
            	</td>
			</tr>
		</table>	   
		<br>
		<input type="button" id="join" value="회원가입" onclick="joinMember()">      
		<br>
		<div id="resultMember"></div>
		<br>
	</div> 
   
   <br><hr><br>
   
	<!-- 회원수정 -->
	<div>
	<h3>회원수정</h3>
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="update_memId" size="30" onfocus="updateCheck()" />
					<input type="hidden" name="update_checkid" value="idUncheck">
					<span id="msgUpdate"></span>
				</td>
			</tr>
			<tr>
            	<th>비밀번호</th>
            	<td><input type="password" name="update_pw"  size="30" /></td>
			</tr>  
			<tr>
            	<th>비밀번호 확인</th>
            	<td><input type="password" name="update_pwc"  size="30" /></td>
			</tr>      
			<tr>
            	<th>Email</th>
            	<td><input type="text" name="update_email"  size="30" /></td>
			</tr>
			<tr>
            	<th>전화번호</th>
            	<td><input type="text" name="update_phone"  size="30" /></td>
			</tr>
			<tr>
            	<th>주소</th>
            	<td><input type="text" name="update_address"  size="30" /></td>
			</tr>            
			<tr>
            	<th>결제수단</th>
            	<td><input type="text" name="update_payment"  size="30" /></td>
			</tr>
		</table>
		<br>
		<input type="button" id="update" value="회원수정" onclick="updateMember()">      
		<br>
		<div id="resultUpdate"></div>
		<br>	   
	</div>
	<br><hr><br>
   
	<!-- 회원조회 -->
	<div>
	<h3>회원조회</h3>
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="info_memId"  size="30" /></td>
			</tr>                      
		</table>
		<br>
		<input type="button" id="info" value="회원상세조회" onclick="MemberInfo()">
		<input type="button" id="list" value="회원목록조회" onclick="MemberList()">      
		<br>
		<div id="resultInfo"></div>
		<br>
		<div id="resultList"></div>
		<br>
	</div> 
	<br><hr><br>
   
	<!-- 회원삭제 -->  
	<div>
	<h3>회원삭제</h3> 
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="delete_memId"  size="30" /></td>
			</tr>                      
		</table>
		<br>
		<input type="button" id="delete" value="회원삭제" onclick="deleteMember()">      
		<br>
		<div id="resultdelete"></div>
		<br>
	</div>
</body>
</html>