package com.future.my.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.board.vo.BoardVO;
import com.future.my.board.vo.ReplyVO;

@Mapper
public interface IBoardDAO {
	
	public List<BoardVO> getBoardList();
	public int writeBoard(BoardVO board);
	public BoardVO getBoard(int boardNo);
	public int updateBoard(BoardVO board);
	public int deleteBoard(int boardNo);
	
	public int writeReply(ReplyVO reply);
	public ReplyVO getReply(String replyNo);
	public List<ReplyVO> getReplyList(int boardNo);
	public int delReply(String replyNo);
}
