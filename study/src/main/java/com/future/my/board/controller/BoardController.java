package com.future.my.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.future.my.board.service.BoardService;
import com.future.my.board.vo.BoardVO;
import com.future.my.board.vo.ReplyVO;
import com.future.my.member.vo.MemberVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;

	@RequestMapping("/boardView")
	public String boardView(Model model,HttpSession session) {
		
		List<BoardVO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/boardView";
	}
	
	@RequestMapping("/boardWriteView")
	public String boardWriteView(HttpSession session) {
		
		if (session.getAttribute("login")==null ) {
			return "redirect:/loginView";
		}
		
		return "board/boardWriteView";
	}
	
	@RequestMapping("/boardWriteDo")
	public String boardWriteDo(BoardVO board, HttpSession session) throws Exception{
		
		//input 태그를 통해 회원이 아이디를 가져오지는 못하므로
		//세쎤 객체로부터 로그인 된 회원의 아이디를 꺼내서 board 객체에 담는다.
		MemberVO login = (MemberVO) session.getAttribute("login");
		board.setMemId(login.getMemId());
		
		boardService.writeBoard(board);
		
		return "redirect:/boardView";
	}
	
	@RequestMapping("/boardDetailView")
	public String boardDetailView(int boardNo, Model model) throws Exception{
		
		BoardVO board = boardService.getBoard(boardNo);
		List<ReplyVO> replyList = boardService.getReplyList(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("replyList", replyList);
		
		return "board/boardDetailView";
	}
	
	@RequestMapping(value="/boardEditView")
	public String boardEditView(int boardNo, Model model) throws Exception{
		
		BoardVO board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		
		return "board/boardEditView";
	}
	
	@PostMapping("/boardEditDo")
	public String boardEditDo(BoardVO board) throws Exception{
		
		boardService.updateBoard(board);
		
		return "redirect:/boardView";
	}
	
	@PostMapping("/boardDelDo")
	public String boardDelDo(int boardNo) throws Exception{
		
		boardService.deleteBoard(boardNo);
		
		return "redirect:/boardView";
	}
	
	
	@ResponseBody
	@PostMapping("/writeReplyDo")
	public ReplyVO writeReplyDo(@RequestBody ReplyVO reply)throws Exception{
		System.out.println(reply);
		//유니크 아이디 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		String uniqueId =sdf.format(date);
		for (int i = 0; i < 3; i++) {
			int randNum = (int)(Math.random() * 10); // 0~9
			uniqueId += randNum;
		}
		System.out.println(uniqueId);
		reply.setReplyNo(uniqueId);
		
		//댓글 작성후 해당 댓글 조회하여 리턴
		boardService.writeReply(reply);
		ReplyVO result = boardService.getReply(uniqueId);
		
		return result;
	}
	
	
	//댓글 삭제후 메세지 전달
	@ResponseBody
	@PostMapping("/delReplyDo")
	public String delReplyDo(@RequestBody ReplyVO reply)throws Exception{
		System.out.println(reply);
		String result = "fail";
		
		boardService.delReply(reply.getReplyNo());
		
		result = "success";
		
		return result;
	}
	

	
	
}
