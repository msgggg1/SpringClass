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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class NoticeDaoImpl implements NoticeDao{

	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	/*
	@Autowired
	private DataSourceTransactionManager transactionManager; // root-context 있는 것 주입 받음
	 */

	/*
	@Autowired
	private TransactionTemplate transactionTemplate;
	 */

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
				+ " WHERE seq = :seq";

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
	/*
	@Override
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
		return this.npJdbcTemplate.update(sql, paramSource);
	}
	 */

	// 공지사항 INSERT + 작성자 포인트 + 1
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
		this.npJdbcTemplate.update(sql, paramSource);
		// 2. 포인트 1증가
		sql = "UPDATE MEMBER "
				+ " SET point = point+1"
				+ " WHERE ID = :id";
		MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
		paramSource2.addValue("id", notice.getWriter());
		return this.npJdbcTemplate.update(sql, paramSource2);
	}


	@Override
	@Transactional(isolation= Isolation.DEFAULT)
	public void hitUp(String seq) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE notices "
				+ " SET hit = hit + 1"
				+ " WHERE seq = :seq ";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq", seq);
		this.npJdbcTemplate.update(sql, paramSource);
		
	}


	@Override
	public int getHit(String seq) throws ClassNotFoundException, SQLException {
		String sql = "SELECT hit"
				+ " FROM notices"
				+ " WHERE seq = :seq ";	
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("seq", seq);		
		return this.npJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}


	// [1] 트랜잭션 처리가 되지 않은 메서드 (문제 발생 확인용)
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
		this.npJdbcTemplate.update(sql, paramSource);
		// 2. 포인트 1증가
		sql = "UPDATE MEMBER "
				+ " SET point = point+1"
				+ " WHERE ID = :id";
		MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
		paramSource2.addValue("id", id);
		this.npJdbcTemplate.update(sql, paramSource2);

	}
	 */

	/*
	//@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		insert(notice);
		notice.setTitle(notice.getTitle() + " - two");
		insert(notice);
	}
	*/
	
	/*
	// [5] 선언적 트랜잭션 처리 1개 @Transactional 어노테이션 사용
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 1. 공지사항 쓰기
				String sql = "INSERT INTO NOTICES"
						+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
						+ " VALUES( "
						+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
				this.npJdbcTemplate.update(sql, paramSource);
				// 2. 포인트 1증가
				sql = "UPDATE MEMBER "
						+ " SET point = point+1"
						+ " WHERE ID = :id";
				MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
				paramSource2.addValue("id", id);
				this.npJdbcTemplate.update(sql, paramSource2);

	}
	*/

	/*
	// [4] 선언적 트랜잭션 처리 1개 <tx:advice> XML 파일 설정.
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
		this.npJdbcTemplate.update(sql, paramSource);
		// 2. 포인트 1증가
		sql = "UPDATE MEMBER "
				+ " SET point = point+1"
				+ " WHERE ID = :id";
		MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
		paramSource2.addValue("id", id);
		this.npJdbcTemplate.update(sql, paramSource2);

	}
	 */

	// [3] transactionTemplate 사용해서 트랜잭션 처리
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		String sql1 = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		String sql2 = "UPDATE MEMBER "
				+ " SET point = point+1"
				+ " WHERE ID = :id";

		// p515 예제 코딩 참고
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// 트랜잭션이 필요한 작업만 넣어주면 됨
				// 1. 공지사항
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
				npJdbcTemplate.update(sql1, paramSource);
				// 2. 포인트 증가
				MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
				paramSource2.addValue("id", id);
				npJdbcTemplate.update(sql2, paramSource2);

			}
		});


	}
	 */

	// [2] transactionManager를 사용해서 트랜잭션 처리
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		String sql1 = "INSERT INTO NOTICES"
				+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC)"
				+ " VALUES( "
				+ "(SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
		String sql2 = "UPDATE MEMBER "
				+ " SET point = point+1"
				+ " WHERE ID = :id";

		// 1. 트랜잭션 설정 정보를 담는 인터페이스 TransactionDefinition : 기본설정 미리된 DefaultTransactionDefinition 클래스 사용
		// 			(전파 방식, 격리 레벨 등)
		TransactionDefinition definition = new DefaultTransactionDefinition();
		//							트랜잭션 관리자.getTransaction(설정정보 객체)
		TransactionStatus status = this.transactionManager.getTransaction(definition );

		try {
			// 1. 공지사항
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice); // :title 필드명하고 꼭 맞춰야함
			this.npJdbcTemplate.update(sql1, paramSource);
			// 2. 포인트 증가
			MapSqlParameterSource paramSource2 = new MapSqlParameterSource();
			paramSource2.addValue("id", id);
			this.npJdbcTemplate.update(sql2, paramSource2);

			this.transactionManager.commit(status);
		} catch (Exception e) {
			this.transactionManager.rollback(status);
		}

	}
	 */

} // class
