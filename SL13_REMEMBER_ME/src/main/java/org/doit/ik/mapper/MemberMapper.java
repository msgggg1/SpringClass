package org.doit.ik.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.doit.ik.domain.MemberVO;

public interface MemberMapper {

	// id에 해당하는 멤버 객체를 반환하는 메서드   
	//@Select("SELECT * FROM  member WHERE id= #{id} ")
	public MemberVO getMember(@Param("id") String id) throws ClassNotFoundException, SQLException;
	
	// 회원가입 메서드
	//@Insert()
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	
	//회원 정보O + 권한 정보O
	public MemberVO read(@Param("userid") String userid) throws ClassNotFoundException, SQLException;
	
}
