package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardMapper {
	
	// [1] 게시글 목록 + 페이징 처리X
	List<BoardVO> getList();
	// [1-2] 게시글 목록 + 페이징 처리 O
	List<BoardVO> getListWithPaging(Criteria criteria);
	int getTotalCount(Criteria criteria); // 총 레코드 수

	// [2] 게시글 쓰기
	void insert(BoardVO boardVO);
	// [2-2] 게시글 쓰기 + 글 번호 반환 기능 (부여)
	void insertSelectKey(BoardVO boardVO);

	// [3] 게시글 상세보기(조회)
	BoardVO read(Long bno);

	// [4] 게시글 수정
	int update(BoardVO boardVO);
	
	// [5] 게시글 삭제
	int delete(Long bno);
	
}
