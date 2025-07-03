package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.springframework.stereotype.Service;

public interface BoardService {
	
	// [1] 페이징 처리 X + 목록
	List<BoardVO> getList();
	// [2] 페이징 처리 O + 목록
	List<BoardVO> getListWithPaging(Criteria criteria);
	int getTotal(Criteria criteria);
	
	void register(BoardVO boardVO);
	
	BoardVO get(Long bno);
	
	boolean modify(BoardVO boardVO);
	
	boolean remove(Long bno);
}
