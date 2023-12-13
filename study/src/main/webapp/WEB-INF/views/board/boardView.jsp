<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>One Page Wonder - Start Bootstrap Template</title>
    </head>
    <body id="page-top" style="padding-top: 56px;">
	<%@include file="/WEB-INF/inc/top.jsp" %>
	
	<section class="page-section" id="contact">
		<div class="container" style="margin-top: 100px">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<!-- 데이터 수만큼 반복되는 부분 -->
					<c:forEach items="${boardList }" var="board">
						<tr>
							<td>${board.boardNo}</td>
							<td><a href="<c:url value="/boardDetailView?boardNo=${board.boardNo}"/>">${board.boardTitle}</td>
							<td>${board.memNm}</td>
							<td>${board.updateDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			   <a href="<c:url value="boardWriteView" />" >
                	<button type="button" class="btn btn-primary">글쓰기</button>
               </a>
			
		</div>
	</section>
	
	
	<%@include file="/WEB-INF/inc/footer.jsp" %>
	</body>
</html>