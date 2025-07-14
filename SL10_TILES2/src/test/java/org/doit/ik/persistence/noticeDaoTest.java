package org.doit.ik.persistence;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class noticeDaoTest {

	//@Autowired -> 안됨
	//private NoticeDao noticeDao;
	
	@Test
	public void insertTest() {
		
		NoticeDao noticeDao = new NoticeDao();
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle("두 번째 게시글");
		noticeVO.setContent("두 번째 게시글");
		int rowcount = 0;
		try {
			rowcount = noticeDao.insert(noticeVO);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(rowcount);
		System.out.println("INSERT END. ");
		
	}

}
