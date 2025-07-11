package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDaoImpl implements NoticeDao{

	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	// 공지사항의 갯수 반환하는 메서드
	@Override
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = " SELECT COUNT(*) CNT "
				+ " FROM NOTICES "
				+ " WHERE "+field+" LIKE :query"; // 테이블명 + ? 은 ? 못 씀
		
		// MapSqlParameterSource
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("query", query);
									//			리턴값
		return this.npJdbcTemplate.queryForObject(sql, paramSource, Integer.class);
	}
	

	// 공지사항의 목록을 List컬렉션으로 반환하는 메서드
	@Override
	public List<NoticeVO> getNotices(
			int page          // 현재 페이지 번호
			, String field    // 검색조건
			, String query    // 검색어
			) throws ClassNotFoundException, SQLException
	{               

		int srow = 1 + (page-1)*15; 
		int erow = 15 + (page-1)*15;  

		String sql = " SELECT * "
				+ "  FROM ( "
				+ "                 SELECT ROWNUM NUM, N.* "
				+ "                 FROM ("
				+ "                          SELECT * "
				+ "                          FROM NOTICES "
				+ "                          WHERE "+field+" LIKE :query "
				+ "                   ORDER BY REGDATE DESC"
				+ "                ) N"
				+ "  ) "
				+  " WHERE NUM BETWEEN :srow AND :erow ";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("query", "%"+query+"%");
		paramMap.put("srow", srow);
		paramMap.put("erow", erow);
		
		
		return this.npJdbcTemplate.query(sql
								, paramMap
								, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class)); //rowmapper 자동적으로 타입의 매퍼를 만들어줌
	}
	
	

	// 공지사항 삭제하는 메서드
	@Override
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{

		String sql = "DELETE FROM notices "
				+ " WHERE seq = ?";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq", seq);
		return this.npJdbcTemplate.update(sql, paramSource);
	}
	

	// 공지사항 수정하는 메서드
	// ***** (기억)
	@Override
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException{

		String sql = "UPDATE NOTICES "
				+ " SET TITLE=:title, CONTENT=:content, FILESRC=:filesrc "
				+ " WHERE SEQ=:seq";
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
		return this.npJdbcTemplate.update(sql, paramSource);
		/*
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("title", notice.getTitle());
		paramSource.addValue("content", notice.getContent());
		paramSource.addValue("filesrc", notice.getFilesrc());
		paramSource.addValue("seq", notice.getSeq());

		return this.npJdbcTemplate.update(sql, paramSource);
		*/
	}
	

	// 공지사항 보기
	@Override
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				+ " FROM NOTICES "
				+ " WHERE SEQ =:seq" ; //pstmt 쓰므로
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq", seq);

		return this.npJdbcTemplate.queryForObject(sql, paramSource, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	}
	

	// 공지사항 추가하는 메서드
	@Override
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
		return this.npJdbcTemplate.update(sql, paramSource);
	}
	
}
