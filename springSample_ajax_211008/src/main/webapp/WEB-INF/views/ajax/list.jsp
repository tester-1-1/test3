<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.6.0.min.js"></script>
<script>
	function ajax1fn(){
		$.ajax({
			url:"/ajax/ajaxlist.do",
			type:"get",
			data:"sampleData=testData",
			success:function(data){
				$("#result").text(data);
			},
			error:function(){
				alert("실행오류");
			}
		});
	}
	
	function ajax2fn(){
		
		var value=$("#test").val();
		
		$.ajax({
			url:"/ajax/test.do",
			type:"get",
			data:"param="+value,
			success:function(data){
				$("#result").text(data);
			},
			error:function(){
				alert("실행오류");
			}
		});
	}
	
	function listFn(){
		$.ajax({
			url:"/ajax/alllist.do",
			type:"get",
			ContentType:"application/json",	//contentType : 받는 타입 / 보내는건 parameter
			success:function(data){
				var str = "";
				str += "<table border='1'>";
				str += "<tr>";
				str += "	<th>제목</th>";
				str += "	<th>작성자</th>";
				str += "	<th>등록일</th>";
				str += "</tr>";
				for(var i=0; i<data.length; i++){
					var item = data[i];
					str += "<tr>";
					str += "	<td><a href='javascript:view("+item.sidx+")'>"+item.stitle+"</a></td>";	//view() : 상세 데이터를 가져와서 밑에 뿌려준다
					str += "	<td>"+item.swriter+"</td>";
					str += "	<td>"+item.sdate+"</td>";
					str += "</tr>";
				}
				str += "</table>";
				
				str += "<button onclick='javascript:insert()'>글쓰기</button>"
				
				$("#list").html(str);
			},
			error:function(){
				
			}
		});
	}
	
	function view(idx){
		//테이블에서 제목을 클릭하면 각각의 행에 해당하는 게시글의 인자값으로 sidx가
		//매개변수 idx로 넘어옵니다.
		//이 idx를 사용하여 ajax 통신으로 springtest 테이블의 게시글 상세조회를 하고
		//데이터를 id="detail" 영역에 출력해주세요
		$.ajax({
			url:"/ajax/view.do",
			type:"get",
			data:"sidx="+idx,
			//contentType : 받는 타입 / 보내는건 parameter
			//라이브러리에 jackson이 데이터를 받아주기 때문에 contentType:"application/json"이 없어도 값을 받는데는 문제가 없다(?)
			//contentType:"application/json"가 있으면 좋다.
			contentType:"application/json",
			success:function(data){
				var item = data;
				var str = "";
				str += "<table border='1'>";
				str += "<tr>";
				str += "	<th>제목</th>";
				str += "	<th>작성자</th>";
				str += "	<th>내용</th>";
				str += "</tr>";
				str += "<tr>";
				str += "	<td>"+item.stitle+"</td>";
				str += "	<td>"+item.swriter+"</td>";
				str += "	<td>"+item.scontents+"</td>";
				str += "</tr>";
				
				str += "</table>";
				str += "<button onclick='javascript:modify("+item.sidx+")'>수정</button>";
				str += "<button onclick='javascript:deleteFn("+item.sidx+")'>삭제</button>";
				$("#detail").html(str);
			},
			error:function(){
				
			}
		});
	}
	
	function modify(idx){
		$.ajax({
			url:"/ajax/modify.do",
			type:"get",
			data:"sidx="+idx,
			contentType:"application/json",
			success:function(data){
				
				var item = data;
				var str = "";
				console.log(item);
				str += "<form name='save' accept-charset=='UTF-8'>";
				str += "<table border='1'>";
				str += "<tr>";
				str += "	<th>제목</th>";
				str += "	<th>작성자</th>";
				str += "	<th>내용</th>";
				str += "</tr>";
				str += "<tr>";
				str += "	<td><input type='text' name='stitle' value='"+item.stitle+"'></td>";
				str += "	<td><input type='text' name='swriter' value='"+item.swriter+"'></td>";
				str += "	<td><textarea name='scontents'>"+item.scontents+"</textarea></td>";
				str += "</tr>";
				str += "</table>";
				
				str += "<input type='hidden' name='sidx' value='"+item.sidx+"'>";
				str += "</form>";
				str += "<button onclick='javascript:modifyPost("+item.sidx+")'>수정완료</button>";
				$("#detail").html(str);
			},
			error:function(){
				
			}
		});
	}
	
	
	/*
		contentType은 공식문서에 따르면 앞글자 c를 소문자로 작성하는게 맞지만,
		알 수 없는 오류로 인해 아래의 modifyPost(idx) 함수에서만 ContentType으로 작성해야 실행이 된다.
		-> 구글에서도 검색되지 않아서 원인을 찾지 못 함.
	*/
	function modifyPost(idx){
		var save = $("form[name=save]").serialize();
		console.log(save);
		
		$.ajax({
			url:"/ajax/modify.do",
			type:"post",
			data:save,
			ContentType:"application/json",
			success:function(data){
				listFn();
				view(idx);
			},
			error:function(){
				alert("저장하기 에러!");
			}
		});
	}
	
	function deleteFn(idx){
		$.ajax({
			url:"/ajax/delete.do",
			type:"get",
			data:"sidx="+idx,
			contentType:"json",
			success:function(){
				listFn();
				$("#detail").html("");
			},
			error:function(){
				alert("에러 발생");
			}
		});
	}
	
	function insert(){
		console.log("insert 확인");
				var str = "";
				
				str += "<form name='insert' accept-charset=='UTF-8'>";
				str += "<table border='1'>";
				str += "<tr>";
				str += "	<th>제목</th>";
				str += "	<th>작성자</th>";
				str += "	<th>내용</th>";
				str += "</tr>";
				str += "<tr>";
				str += "	<td><input type='text' name='stitle'></td>";
				str += "	<td><input type='text' name='swriter'></td>";
				str += "	<td><textarea name='scontents'></textarea></td>";
				str += "</tr>";
				str += "</table>";
				
				//str += "<input type='hidden' name='sidx'>";
				str += "</form>";
				str += "<button onclick='javascript:insertOk()'>수정완료</button>";
				$("#detail").html(str);

	}
	
	/*
		post로 데이터를 넘길때는 data에 변수를 붙이면 안 된다.
		serialize() 메소드가 queryString 형태로 stitle=제목&scontents=내용&swriter=작성자 와 같이 바꿔주기 때문에
		data:"formData="+formD와 같이 앞에 변수를 선언하면 formDatastitle=제목 과 같이 돼서 인식하지 못하여 에러가 발생한다.
	*/
	function insertOk(){
		var formD = $("form[name=insert]").serialize();
		console.log(formD);
		$.ajax({
			url:"/ajax/insert.do",
			type:"post",
			data:formD,
			ContentType:"application/json",
			success:function(data){
				listFn();
				$("#detail").html("");
			},
			error:function(){
				alert("에러 발생");
			}
		});
	} 
	
</script>
</head>
<body>
	<h2>springtest 테이블 데이터 목록 ajax로 가져오기</h2>
	<button onclick="listFn()">call list</button>
	<div id="list">
	
	</div>
	<h3>상세 조회 영역</h3>
	<div id="detail">
		
	</div>
	
	
	<button onclick="ajax1fn()">ajax1</button><br>
	<input type="text" id="test">
	<button onclick="ajax2fn()">ajax2</button>
	<div id="result">
		ajax2 버튼 클릭시 id="test" 양식에 있는 값을 ajax를 사용하여
		/ajax/test.do 경로로 보낸 뒤 다시 응압 데이터로 얻어와 이 영역에 출력합니다.
	</div>
</body>
</html>