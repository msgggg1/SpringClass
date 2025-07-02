package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.springframework.stereotype.Service;

public interface BoardService {
	
	List<BoardVO> getList();
	
	void register(BoardVO boardVO);
	
	BoardVO get(Long bno);
	
	boolean modify(BoardVO boardVO);
	
	boolean remove(Long bno);
}
