package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberShipServiceImpl implements MemberShipService{

	@Autowired
	private NoticeMapper noticeDao;
	
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		this.noticeDao.insert(notice);
		//notice.setTitle(notice.getTitle() + " - two");
		//this.noticeDao.insert(notice);
	}
}
