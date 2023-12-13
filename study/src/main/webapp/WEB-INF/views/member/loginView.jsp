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


   <!-- 회원가입 폼 -->
    <section class="py-5 mt-5">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <h2 class="section-heading text-center">로그인</h2>
                    <form id="signupForm" method="post" action= "<c:url value ="/loginDo" />">
                        <div class="mb-3">
                            <label for="id" class="form-label">아이디</label>
                            <input type="id" class="form-control" id="id" name="memId" required  value="${cookie.rememberId.value}">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="memPw" required>
                        </div>
						<!-- 아이디 기억     -->
						<div class="form-floating mb-3">
							아이디 기억하기 <input class="form-check-input" name="remember" type="checkbox"
										${cookie.rememberId.value==null ? "":"checked" }/>
							<input name="fromUrl" type="hidden" value="${fromUrl }"/>
						</div>
                        <button type="submit" class="btn btn-primary">로그인</button>
                            <c:if test="${param.msg == 'N'}">
						        <div class="alert alert-danger" role="alert">
						           	 아이디 또는 비밀번호가 맞지 않습니다.
						        </div>
						    </c:if>
                    </form>
                </div>
            </div>
        </div>
    </section>

	
    </body>
</html>
