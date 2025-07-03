package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 스프링 컨테이너 만들어져야함(실행할 수 있는 환경)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper boardMapper;

	/*
	@Test
	public void testPaging() {
		Criteria criteria = new Criteria(2,3);
		List<BoardVO> list = this.boardMapper.getListWithPaging(criteria);
		
		list.forEach(boardVO -> System.out.println(boardVO));
	}
	*/
	
	@Test
	public void testPaging() {
		Criteria criteria = new Criteria();
		criteria.setType("TC");
		criteria.setKeyword("스프링-1");
		
		List<BoardVO> list = this.boardMapper.getListWithPaging(criteria);
		
		list.forEach(boardVO -> System.out.println(boardVO));
	}
	

}
