package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;

public interface MemberDao {

	// id에 해당하는 멤버 객체를 반환하는 메서드   
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException;
	
	// 회원가입 메서드
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	
}
