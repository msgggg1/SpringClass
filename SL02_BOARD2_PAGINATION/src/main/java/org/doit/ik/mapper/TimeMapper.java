package org.doit.ik.mapper;

import org.apache.ibatis.annotations.Select;

// BoardDAO 인터페이스 생각 -> Impl 안만들어도 됨
public interface TimeMapper {
	
	// DB서버시간 돌려주는 함수
	String getTime();
	
	@Select("SELECT sysdate + 2 FROM dual")
	String getNextTime();
	
}
