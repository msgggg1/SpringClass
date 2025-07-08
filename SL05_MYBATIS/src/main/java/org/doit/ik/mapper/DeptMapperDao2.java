package org.doit.ik.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.doit.ik.domain.DeptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository
public class DeptMapperDao2 implements DeptMapper { //Impl까지 만들고 코딩 x
	
	@Autowired
	private SqlSession sqlSession; // 프록시라 template이랑 사실 똑같음

	@Override
	public ArrayList<DeptDTO> selectDept() {
		// sqlSessionTemplate로 구현 그러나 Impl은 만들어야 함. 
		List<DeptDTO> list = this.sqlSession.selectList("org.doit.ik.mapper.DeptMapper.selectDept"); // 제공되는 메서드 사용
		return new ArrayList<>(list);
	}

	@Override
	public int insertDept(DeptDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDept(int deptno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
