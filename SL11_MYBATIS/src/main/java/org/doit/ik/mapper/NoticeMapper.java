package org.doit.ik.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.NoticeVO;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
public interface NoticeMapper {

	// 공지사항의 갯수 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항의 목록을 List컬렉션으로 반환하는 메서드
	public List<NoticeVO> getNotices(@Param("page") int page, @Param("field") String field, @Param("query")String query) throws ClassNotFoundException, SQLException;
	
	// 파라미터 1개일때는 이름 유추 가능 @Param 안써도 됨. 그 외 -> 다 붙이는 것 권장
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
	
	public void hitUp(@Param("seq") String seq) throws ClassNotFoundException, SQLException;
	public int getHit(@Param("seq") String seq) throws ClassNotFoundException, SQLException;
	
}
