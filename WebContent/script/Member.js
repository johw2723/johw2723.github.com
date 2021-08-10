/* 회원 가입 - 아이디 중복체크 */
function idCheck(){
	$("#insertId").focusout(function(){
		$.ajax({
			url : "../MemberProcessing?command=idCheck",	
			type : "post",
			dataType: "JSON",
			data : {
				memId : $("input[name=memId]").val()			
			},
			success : function(data){
				if(data.result==true){
		        $("#msgData").html("ID 중복 체크 : 중복").css("color", "red"),
		        $("input[name=checkid]").val("idUncheck");
		        } else if(data.result==false){
		            $("#msgData").html("ID 중복 체크 : 사용 가능").css("color", "blue"),
		            $("input[name=checkid]").val("idCheck");
		        }
		    },
		    error : function(){
		        alert("통신실패");
		    }  
		})
	});
}

/* 회원 가입  */
function joinMember(){
	var memId = $('#insertId').val();
	var idCheck = $("input[name=checkid]").val();
	var PW = $('#memPW').val();
	var PWCheck = $('#memPWC').val();
	var email = $("input[name=email]").val();
	var phone = $("input[name=phone]").val();
	var address = $("input[name=address]").val();
	var payment = $("input[name=payment]").val();
	var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
	var isPW = /^[a-zA-Z0-9]{4,19}$/;
	
	if(memId == ""){
		alert("아이디를 입력하세요.");
	} else if (!isID.test(memId)){
		alert("아이디는 5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
	} else if (idCheck == "idUncheck"){
		alert("사용중인 아이디 입니다.");
	} else if (PW == ""){
		alert("비밀번호를 입력하세요.");
	} else if (PW!=PWCheck){
		alert("비밀번호가 일치하지 않습니다.");
	} else if (!isPW.test(PW)){
		alert("비밀번호는 5~20자 영문 대 소문자, 숫자만 사용 가능합니다.");
	} else if (email == ""){
		alert("이메일을 입력해주세요.");
	} else if (phone == ""){
		alert("전화번호를 입력해주세요.");
	} else {
		$.ajax({
		    url : "../MemberProcessing?command=insertMember",	
			type : "post",
			dataType: "JSON",
			data : {
				memId : $("input[name=memId]").val(),
				pw : $("input[name=pw]").val(),
				email : $("input[name=email]").val(),
				phone : $("input[name=phone]").val(),
				address : $("input[name=address]").val(),
				payment : $("input[name=payment]").val()
			},
			success : function(data){
				whenSuccess(data);
				$("input[name=checkid]").val("idUncheck");
	        },
	        error : function(){
	            alert("통신실패");
	        }  
		})
	}
}

/* 회원 가입  - JSON */
function whenSuccess(jsonData) {
	$("#resultMember").html("회원 가입이 완료되었습니다. <br>" + "ID : " + jsonData.memId + "<br>PW : " 
			+ jsonData.pw + "<br>Email : " + jsonData.email + "<br>Phone : " + jsonData.phone
			+ "<br>Address : " + jsonData.address + "<br>Payment : " + jsonData.payment);
}

/* 회원 수정 - DB확인 */
function updateCheck(){
	$('input[name=update_memId]').focusout(function(){
		$.ajax({
			url : "../MemberProcessing?command=idCheck",	
			type : "post",
			dataType: "JSON",
			data : {
				memId : $("input[name=update_memId]").val()			
			},
			success : function(data){
				if(data.result==true){
		        $("#msgUpdate").html("회원정보가 있습니다.").css("color", "blue"),
		        $("input[name=update_checkid]").val("idCheck");
		        } else if(data.result==false){
		            $("#msgUpdate").html("회원정보가 없습니다.").css("color", "red"),
		            $("input[name=update_checkid]").val("idUncheck");
		        }
		    },
		    error : function(){
		        alert("통신실패");
		    }  
		})
	});
}

/* 회원 수정  */
function updateMember(){
	var memId = $('input[name=update_memId]').val();
	var idCheck = $("input[name=update_checkid]").val();
	var PW = $('input[name=update_pw]').val();
	var PWCheck = $('input[name=update_pwc]').val();
	var email = $("input[name=update_email]").val();
	var phone = $("input[name=update_phone]").val();
	var address = $("input[name=update_address]").val();
	var payment = $("input[name=update_payment]").val();
	var isPW = /^[a-zA-Z0-9]{4,19}$/;
	
	if(memId == ""){
		alert("아이디를 입력하세요.");
	} else if (idCheck == "idUncheck"){
		alert("존재하지 않는 회원입니다.");
	} else if (PW == ""){
		alert("비밀번호를 입력하세요.");
	} else if (PW!=PWCheck){
		alert("비밀번호가 일치하지 않습니다.");
	} else if (!isPW.test(PW)){
		alert("비밀번호는 5~20자 영문 대 소문자, 숫자만 사용 가능합니다.");
	} else if (email == ""){
		alert("이메일을 입력해주세요.");
	} else if (phone == ""){
		alert("전화번호를 입력해주세요.");
	} else {
		$.ajax({
		    url : "../MemberProcessing?command=updateMember",	
			type : "post",
			dataType: "JSON",
			data : {
				memId : $("input[name=update_memId]").val(),
				pw : $("input[name=update_pw]").val(),
				email : $("input[name=update_email]").val(),
				phone : phone,
				address : address,
				payment : payment
			},
			success : function(data){
				updateSuccess(data);
	        },
	        error : function(){
	            alert("통신실패");
	        }  
		})
	}
}

/* 회원 수정 - JSON */
function updateSuccess(jsonData) {
	$("#resultUpdate").html(jsonData.memId + "님의 정보가 수정되었습니다. <br>PW : " 
			+ jsonData.pw + "<br>Email : " + jsonData.email + "<br>Phone : " + jsonData.phone
			+ "<br>Address : " + jsonData.address + "<br>Payment : " + jsonData.payment);
}


/* 회원 조회  */
function MemberInfo(){
	var memId = $('input[name=info_memId]').val();
	$.ajax({
		url : "../MemberProcessing?command=MemberInfo",	
		type : "post",
		dataType: "JSON",
		data : {
			memId : memId
		},
		success : function(data){
			infoSuccess(data);		
		},
	    error : function(){
	    	alert("통신실패");
	    }  
	})
}

/* 회원 조회 - JSON */
function infoSuccess(jsonData) {
	if(jsonData.memId==null){
		alert(jsonData.msg);
	} else {
		$("#resultInfo").html(jsonData.memId + "님의 정보 조회입니다. <br>PW : " 
		+ jsonData.pw + "<br>Email : " + jsonData.email + "<br>Phone : " + jsonData.phone
		+ "<br>Address : " + jsonData.address + "<br>Payment : " + jsonData.payment + "<br>가입일 : " + jsonData.date);
	}
}

/* 회원 목록  */
function MemberList(){
	$.ajax({
		url : "../MemberProcessing?command=MemberList",	
		type : "post",
		dataType: "JSON",
		data : {

		},
		success : function(data){
			ListSuccess(data.item);		
		},
	    error : function(){
	    	alert("통신실패");
	    }  
	})
}

/* 회원 목록 - JSON */
function ListSuccess(jsonData) {
	$("#resultList").html(""); // 아래에서 append 하기 때문에 기존 목록을 지운다.
	$.each(jsonData, function(){
		$("#resultList").append(this.memId + "님의 정보 조회입니다. <br>PW : " 
		+ this.pw + "<br>Email : " + this.email + "<br>Phone : " + this.phone
		+ "<br>Address : " + this.address + "<br>Payment : " + this.payment + "<br>가입일 : " + this.date + "<br><br>");
	});
}

/* 회원 삭제  */
function deleteMember(){
	var memId = $('input[name=delete_memId]').val();
	$.ajax({
		url : "../MemberProcessing?command=deleteMember",	
		type : "post",
		dataType: "JSON",
		data : {
			memId : memId
		},
		success : function(data){
			deleteSuccess(data);		
		},
	    error : function(){
	    	alert("통신실패");
	    }  
	})
}

/* 회원 삭제 - JSON */
function deleteSuccess(jsonData) {
	$("#resultdelete").html(jsonData.msg);
}

/* 회원 가입 - 양식 */
function checkId(obj) { 
	var memId = document.all.memId.value;
    var alert = document.getElementById('alert');
    var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
    
	if (obj.value == "") {		
		alert.style.display='block';
		alert.innerHTML="필수 입력정보입니다.";
		obj.focus();
    } else if (!isID.test(memId)){
		alert.style.display='block';
		alert.innerHTML="5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
    } else {
		alert.style.display='block';
		alert.innerHTML="완벽합니다!";
    }
}

function checkPw(obj) {
	var pw = document.all.pw.value;
    var alert = document.getElementById('alertPW');
    var isPW = /^[a-zA-Z0-9]{4,19}$/;
    
    if (obj.value == "") {		
		alert.style.display='block';
		alert.innerHTML="필수 입력정보입니다.";
    } else if (!isPW.test(pw)){
		alert.style.display='block';
		alert.innerHTML="5~20자 영문 대 소문자, 숫자를 사용하세요.";
    } else {
		alert.style.display='block';
		alert.innerHTML="완벽합니다!";
    }
}

function checkPwc(obj) {
	var PW = $('#memPW').val();
	var PWCheck = $('#memPWC').val();
    var alert = document.getElementById('alertPWC');
    
    if (obj.value == "") {		
		alert.style.display='block';
		alert.innerHTML="설정하려는 비밀번호가 맞는지 확인하기 위해 다시 입력 해주세요.";
    } else if (PW != PWCheck){
		alert.style.display='block';
		alert.innerHTML="비밀번호가 일치하지 않습니다.";
    } else {
		alert.style.display='block';
		alert.innerHTML="일치합니다!";
    }
}

function checkE(obj) {
	var email = document.formJoin.email.value;
    var alert = document.getElementById('alertE');
    
    if (obj.value == "") {		
		alert.style.display='block';
		alert.innerHTML="필수 입력정보입니다.";
    } else {
		alert.style.display='none';
    }
}

function checkP(obj) {
	var phone = document.formJoin.phone.value;
    var alert = document.getElementById('alertP');
    
    if (obj.value == "") {		
		alert.style.display='block';
		alert.innerHTML="필수 입력정보입니다.";
    } else {
		alert.style.display='none';
    }
}
