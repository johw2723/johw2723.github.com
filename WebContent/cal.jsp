<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Cal Ajax</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<!--  ����(insert) -->

<h3>���</h3>
OP1 : <input type="text" name="op1"/><br>
OP : <input type="text" name="op"/><br>
OP2 : <input type="text" name="op2"/><br>
<input id="insert" type="button" value="insert">

<br>
<div id="resultInsert"></div>
<br>
<hr>

<!--  ��ȸ(Select * From cal Where no=?) -->
<h3>��ȸ</h3>
NO : <input type="text" name="select_no"/><br>
<input id="select" type="button" value="select">

<br>
<div id="resultSelect"></div>
<br>

<!--  ��ü��ȸ(Select All cal) -->
<input id="selectAll" type="button" value="selectAll">

<br>
<div id="resultSelectAll"></div>
<br>
<hr>

<!--  ����(Update cal Set no=?) -->

<h3>����</h3>
NO : <input type="text" name="update_no"/><br>
OP1 : <input type="text" name="update_op1"/><br>
OP : <input type="text" name="update_op"/><br>
OP2 : <input type="text" name="update_op2"/><br>
<input id="update" type="button" value="update">

<br>
<div id="resultUpdate"></div>
<br>
<hr>

<!--  ����(Delete cal Whwere no=?) -->

<h3>����</h3>
NO : <input type="text" name="delete_no"/><br>
<input id="delete" type="button" value="delete">

<br>
<div id="resultDelete"></div>
<br>
<!--  ��ü����(Delete All cal) -->
<input id="deleteAll" type="button" value="deleteAll">


<div id="resultDeleteAll"></div>
<br>

<script type="text/javascript">
$("#insert").click(function(){	
    $.ajax({
	    url : "CalProcessing?command=insert",	
		type : "get",
		dataType: "JSON",
		data : {
			op1 : $("input[name=op1]").val(),
			op : $("input[name=op]").val(),
			op2 : $("input[name=op2]").val(),			
		},
		success : function(data){
            $("#resultInsert").html(data.op1 + " " + data.op + " " +data.op2 + " = " + data.result);
        },
        error : function(){
            alert("��Ž���");
        }  
	})
});

$("#select").click(function(){	
    $.ajax({
	    url : "CalProcessing?command=select",	
		type : "get",
		dataType: "JSON",
		data : {
			no : $("input[name=select_no]").val(),			
		},
		success : function(data){		
			$("#resultSelect").html(data.op1 + " " + data.op + " " +data.op2 + " = " + data.result);
        },
        error : function(){
            alert("��Ž���");
        }  
	})
});

$("#selectAll").click(function(){	
    $.ajax({
	    url : "CalProcessing?command=selectAll",	
		type : "get",
		dataType: "JSON",
		data : {
					
		},
		success : function(data){	
			selectAllSuccess(data.item);		
        },
        error : function(){
            alert("��Ž���");
        }  
	})
});

function selectAllSuccess(jsonData) {
	$("#resultSelectAll").html(""); // �Ʒ����� append �ϱ� ������ ���� ����� �����.
	$.each(jsonData, function(){		
		$("#resultSelectAll").append(this.getNo + "��° ��� " + this.getOp1 + " " + this.getOp + " " + this.getOp2 + " = " + this.getResult + "<br>");
	});
}

$("#update").click(function(){	
    $.ajax({
	    url : "CalProcessing?command=update",	
		type : "get",
		dataType: "JSON",
		data : {
			no : $("input[name=update_no]").val(),
			op1 : $("input[name=update_op1]").val(),
			op : $("input[name=update_op]").val(),
			op2 : $("input[name=update_op2]").val()
		},
		success : function(data){
			$("#resultUpdate").html(data.no + "��° ����� �����Ǿ����ϴ�. <br>" + data.op1 + " " + data.op + " " +data.op2 + " = " + data.result);
        },
        error : function(){
            alert("��Ž���");
        }  
	})
});

$("#delete").click(function(){	
    $.ajax({
	    url : "CalProcessing?command=delete",	
		type : "get",
		dataType: "JSON",
		data : {
			no : $("input[name=delete_no]").val(),			
		},
		success : function(data){		
			$("#resultDelete").html(data.no + "��° ����� �����Ǿ����ϴ�.");
        },
        error : function(){
            alert("��Ž���");
        }  
	})
});

$("#deleteAll").click(function(){	
    $.ajax({
	    url : "CalProcessing?command=deleteAll",	
		type : "get",
		dataType: "JSON",
		data : {
					
		},
		success : function(data){		
			$("#resultDeleteAll").html(data.str);
        },
        error : function(){
            alert("��Ž���");
        }  
	})
});


</script>   
</body>
</html>