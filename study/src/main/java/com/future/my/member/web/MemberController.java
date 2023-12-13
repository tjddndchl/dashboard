package com.future.my.member.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.future.my.board.vo.BoardVO;
import com.future.my.member.service.MemberService;
import com.future.my.member.vo.MemberVO;

@Controller
public class MemberController {
	
	
	@Autowired
	MemberService memberService;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	


	@RequestMapping("/registView")
	public String registView() {
		
		return "member/registView";
	}
	
	
	@RequestMapping("/registDo")
	public String registDo(HttpServletRequest request,HttpSession session) {
		
		String id = request.getParameter("id");
		String pw = passwordEncoder.encode(request.getParameter("pw"));
		String name = request.getParameter("nm");
		
		System.out.println(id);
		System.out.println(pw);
		System.out.println(name);
		
		MemberVO memberVO = new MemberVO(id, pw, name);
		try {
			memberService.registMember(memberVO);
			session.setAttribute("registrationSuccess", true);
		} catch (Exception e) {
			e.printStackTrace();
			return "errorView";
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/loginView")
	public String loginView(HttpServletRequest request, Model model, String msg) {
		
		//현재 URL 요청
		String requestUrl = request.getHeader("Referer");
		
		model.addAttribute("fromUrl", requestUrl);
		model.addAttribute("msg", msg);
		System.out.println(msg + "1" );
		return "member/loginView";
	}
	
	@RequestMapping("/loginDo")
	public String loginDo(MemberVO member, HttpSession session, boolean remember, String fromUrl
						,HttpServletResponse response) {
		System.out.println(remember);
		MemberVO login = memberService.loginMember(member);
		//앞:평문 비밀번호 , 뒤 암호화 값
		/*
		 *  DB에서 가져온 암호화된 비밀번호(member.getMemPw()에서 솔트 값을 추출
		 *  사용자가 입력한 평문 비빌먼호(login.getMemPw()와 추출된 솔트 값을 사용하여 비밀번호를 다시 암호화 한다.
		 *  2단계에서 암호화된 값과 DB에서 가져온 암호화된 비밀번호를 비교
		 *  두 값이 일치하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
		 * */

		if (login == null) {
			System.out.println("1");
			
			return "redirect:/loginView?msg=N";
		}
		boolean match = passwordEncoder.matches(member.getMemPw(),login.getMemPw());
		if (!match) {
			return "redirect:/loginView?msg=passwordIncorrect";
		}
		
		session.setAttribute("login", login);
		
		if (remember) {
			//쿠키 생성
			Cookie cookie = new Cookie("rememberId", member.getMemId());
			// 응답하는 객체에 붙여준다
			response.addCookie(cookie);
		} else {
			// 쿠키 삭제 (동일한 key값을 가지는 쿠키를 생성 후 유효기간을 0으로 만든다.
			Cookie cookie = new Cookie("rememberId", "");
			cookie.setMaxAge(0);
			
			//유효기간이 0짜리인 쿠키를 응답하는 객체에 붙여준다
			response.addCookie(cookie);
		}
	
		//로그인시 해당페이지 돌아가기
		
		return "redirect:" + fromUrl;
	}
	
	@RequestMapping("/logoutDo")
	public String logoutDo(HttpSession session,HttpServletRequest request) {
		//세션 종료
		session.invalidate();
		//현재 요청이 어느 URL을 바라보는지
		String requestToURL = request.getRequestURI().toString();
		System.out.println(requestToURL);
		String requestUrl = request.getHeader("Referer");
		System.out.println(requestUrl);
		
		
		
		return "redirect:" + requestUrl;
	}
	
	
	@RequestMapping("/myPage")
	public String mypage(HttpSession session, Model model) {
		if (session.getAttribute("login")==null) {
			return "redirect:/loginView";
		}
		MemberVO login = (MemberVO)session.getAttribute("login");
		model.addAttribute("member", login);
		return "member/myPage";
	}
	
	@PostMapping("/updateDo")
	public String updateDo(MemberVO member,HttpServletRequest request) throws Exception{
		String id = request.getParameter("memId");
		String pw = passwordEncoder.encode(request.getParameter("memPw"));
		String name = request.getParameter("memNm");
		
		MemberVO memberVO = new MemberVO(id, pw, name);
		try {
			memberService.updateMember(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
			return "errorView";
		}

		return "redirect:/loginView";
	}
	
	@PostMapping("/memberDelDo")
	public String memberDelDo(MemberVO member, HttpSession session) throws Exception {
	    // 현재 로그인한 사용자 정보 가져오기
	    MemberVO loggedInUser = (MemberVO) session.getAttribute("login");

	    // 현재 로그인한 사용자와 삭제 대상 사용자가 동일한지 확인
	    if (loggedInUser != null && loggedInUser.getMemId().equals(member.getMemId())) {
	        // 현재 로그인한 사용자와 삭제 대상 사용자가 동일하면 삭제 진행
	        memberService.deleteMember(member);

	        // 세션 종료
	        session.invalidate();

	        return "redirect:/logoutDo";
	    } else {
	        // 삭제 권한이 없는 경우 에러 페이지 또는 다른 처리 수행
	        return "errorPage";
	    }
	}
	
	@PostMapping("/files/upload")
	public ResponseEntity<?> uploadFiles(@RequestParam("uploadImage") MultipartFile uploadImage
			,HttpServletRequest req, @RequestParam Map<String, Object> map, HttpSession session) throws Exception{
		// 경로 작성
		// 1) 웹접근
		//resources/memberProfile/
		String webPath = "/resources/memberProfile/";
		//2)서버 저장 폴더
		// D;\dev\workspace-spring\study\src\main\webapp\resources\memberProfile
		String folderPath = session.getServletContext().getRealPath(webPath);
		System.out.println(folderPath);
		MemberVO login = (MemberVO) session.getAttribute("login");
		
		String imgPath = memberService.updateProfilePicture(login, folderPath, webPath, uploadImage);
		
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Success");
		response.put("imagePath", imgPath);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
}
