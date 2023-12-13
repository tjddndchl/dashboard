
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<script src="${pageContext.request.contextPath}/resources/smarteditor2-2.8.2.3/js/HuskyEZCreator.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<section class="page-section" id="contact">
		<div class="container" style="margin-top: 100px;">
            <form action='<c:url value="/free/freeView" />' method="post" onsubmit="return fn_check()">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>제목</th>
                        <td><input class="form-control input-sm" type="text" name="boTitle" value="${freeBoard.boTitle}"></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><input class="form-control input-sm" type="text" name="boWriter" value="${freeBoard.boWriter}"></td>
                    </tr>

                    <tr>
                        <th>분류</th>
						<td><input class="form-control input-sm" type="text" name="boCategoryNm" value="${freeBoard.boCategory}"></td>
                    </tr>                    
                    <tr>
                        <th>내용</th>
                        <td>${freeBoard.boContent}</td>
                    </tr>               
                </table>
            </form>
		</div>
	</section>
</body>
<script type="text/javascript">

</script>

</html>






