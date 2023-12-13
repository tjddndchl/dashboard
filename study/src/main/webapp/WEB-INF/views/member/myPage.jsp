<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Insert title here</title>
</head>
<body id="page-top" style="padding-top: 56px;">
	<%@include file="/WEB-INF/inc/top.jsp" %>
        <section class="page-section" id="contact">
            <div class="container">
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">마이페이지</h2>
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon">
                    <c:if test="${sessionScope.login.profileImg == null }">
                    	<img src="${pageContext.request.contextPath}/assets/img/portfolio/non.png" id="myImage" class="rounded-circle img-thumbnail shadow-sm"
                    		width="200" style="cursor:pointer;">
                    		
                   </c:if>
                     <c:if test="${sessionScope.login.profileImg != null }">
                    	<img src="${pageContext.request.contextPath}${sessionScope.login.profileImg}" id="myImage" class="rounded-circle img-thumbnail shadow-sm"
                    		width="200" style="cursor:pointer;">
                    </c:if>
                    
                    		<form id="profileForm" enctype="multipart/form-data">
                    			<input type="file" name="uploadImage" id="uploadImage"
                    				accept="image/*" style = "display:none;" />
                    		</form>
                    </div>
                    <div class="divider-custom-line"></div>
                </div>          
                
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                        <form method="post" action= "<c:url value ="/updateDo" />">
                        	<div class="form-floation mb-3">
                        		<input class="form-control" name="memId" type="text"
                        		placeholder="아이디를 입력해주세요" readonly value="${member.memId}">
                        		<label>아이디</label>
                        	</div>	
                        	<div class="form-floation mb-3">
                        		<input class="form-control" name="memPw" type="password"
                        		placeholder="비밀번호를 입력해주세요">
                        		<label>비밀번호</label>
                        	</div>	
                        	<div class="form-floation mb-3">
                        		<input class="form-control" name="memNm" type="text"
                        		placeholder="이름을 입력해주세요">
                        		<label>이름</label>
                        	</div>	
                        	<button class="btn btn-primary btn-xl" type="submit">수정하기</button>
                        </form>
                    </div>
                </div>
                <form method="post" action= "<c:url value ="/memberDelDo" />" id="delForm">
	                <input type="hidden" name="memId" value="${member.memId}">
	                <button class="btn btn-primary btn-xl" type="submit" onclick="f_memberDel()">탈퇴하기</button>
                </form>
            </div>
        </section>
	
	<%@include file="/WEB-INF/inc/footer.jsp" %>
		<script type="text/javascript">
		//해당 form태그 객체를 찾아 submit 시키기
		function f_memberDel(){
			if(confirm("정말로 삭제하시겠습니까?")){
				document.getElementById("delForm").submit();
			}
		}
		
    	$(document).ready(function(){
    		$("#myImage").click(function(){
    			$("#uploadImage").click();
    		});
    		
    		$("#uploadImage").on("change", function(){
    			let file = $(this)[0].files[0];
    			if(file){
    				let fileType = file['type'];
    				let valTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/jpg'];
    				if(!valTypes.includes(fileType)){
    					alert("유효한 이미지 타입이 아닙니다.!!!");
    					$(this).val(''); //선택파일 초기화
    					
    				}else{
    					let formData = new FormData($('#profileForm')[0]);
    					console.log(formData);
    					$.ajax({
    						url : '<c:url value="files/upload" />'
    					   ,type: 'POST'
    					   ,data:formData
    					   ,dataType:'json'
    					   ,processData:false
    					   ,contentType:false
    					   ,success:function(res){
    						   console.log(res);
    						   $("#myImage").attr("src", "${pageContext.request.contextPath}" + res.imagePath);
    					   },error: function(e){
    						   console.log(e);
    					   }
    					});
    				}
    			}
    		});
    	});
	</script>
</body>
</html>