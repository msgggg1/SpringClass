package org.doit.ik.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.domain.NoticeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/root-context.xml")
public class memberDaoTest {
	
	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void getMemberTest() {
		
		try {
			MemberVO member = this.memberDao.getMember("MSg");
			System.out.println(member.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("INSERT END. ");
		
	}
	
	/*
	@Test
	public void insertTest() {
		
		MemberVO member = new MemberVO();
		member.setId("MSg");
		member.setPwd("1234");
		member.setName("홍길동");
		member.setGender("여성");
		member.setBirth("1998-06-06");
		member.setIs_lunar("Solar");
		member.setCphone("010-2222-3333");
		member.setEmail("MSg@name.com");
		member.setHabit("music,movie");
	
		int rowcount = 0;
		try {
			rowcount = memberDao.insert(member);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(rowcount);
		System.out.println("INSERT END. ");
		
	}
	*/

}
