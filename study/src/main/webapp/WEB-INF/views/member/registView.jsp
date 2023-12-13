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
	<!-- 모든 페이지 상단에 들어가는 부분 -->
	<%@include file="/WEB-INF/inc/top.jsp" %>



   <!-- 회원가입 폼 -->
    <section class="py-5 mt-5">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <h2 class="section-heading text-center">회원가입</h2>
                    <form id="signupForm" method="post" action= "<c:url value ="/registDo" />">
                        <div class="mb-3">
                            <label for="username" class="form-label">사용자명</label>
                            <input type="text" class="form-control" id="name" name="nm" required>
                        </div>
                        <div class="mb-3">
                            <label for="id" class="form-label">아이디</label>
                            <input type="id" class="form-control" id="id" name="id" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="pw" required>
                        </div>
                        <button type="submit" class="btn btn-primary">회원가입</button>
                    </form>
                </div>
            </div>
        </div>
    </section>

	<%@include file="/WEB-INF/inc/footer.jsp" %>
	<script>

	</script>
	
    </body>
</html>
