package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
public interface NoticeDao {

	// 공지사항의 갯수 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항의 목록을 List컬렉션으로 반환하는 메서드
	public List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항 삭제하는 메서드
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	
	// 공지사항 수정하는 메서드
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
	// 공지사항 보기
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;

	// 공지사항 추가하는 메서드
	@Transactional
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException; 
	
	
	//트랜잭션 테스트를 위한 추가메서드
	// MemberShipService 인터페이스로 이동 d/t 트랜잭션
	//public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException;
	
	public void hitUp(String seq) throws ClassNotFoundException, SQLException;
	public int getHit(String seq) throws ClassNotFoundException, SQLException;
	
}
