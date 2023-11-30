package com.future.my.free.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.free.vo.FreeBoardSearchVO;
import com.future.my.free.vo.FreeBoardVO;

@Mapper
public interface IFreeBoardDAO {
	
	public int getTotalRowCount(FreeBoardSearchVO searchVO); //전체카운트조회
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO); // 보드게시물조회
	public int insertFreeBoard(FreeBoardVO freeBoardVO);
	public int increaseHit(int boNo);
	public FreeBoardVO getBoard(int boNo);
}
