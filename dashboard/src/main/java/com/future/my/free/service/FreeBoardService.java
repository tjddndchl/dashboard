package com.future.my.free.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.my.free.dao.IFreeBoardDAO;
import com.future.my.free.vo.FreeBoardSearchVO;
import com.future.my.free.vo.FreeBoardVO;

@Service
public class FreeBoardService {
	@Autowired
	IFreeBoardDAO dao;
	
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO){
		int totalRowCount = dao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return dao.getBoardList(searchVO);
	}
	
	public void insertFreeBoard(FreeBoardVO freeBoardVO) throws Exception {
		int result = dao.insertFreeBoard(freeBoardVO);
		if (result == 0) {
			throw new Exception();
		}
	}
	
	public FreeBoardVO getBoard(int boNo) throws Exception {
		FreeBoardVO freeBoard = dao.getBoard(boNo);
		if (freeBoard == null) {
			throw new Exception();
		}
		return freeBoard;
	}
	public void increaseHit(int boNo) throws Exception {
		int result = dao.increaseHit(boNo);
		if (result == 0) {
			throw new Exception();
		}
	}
	
}
