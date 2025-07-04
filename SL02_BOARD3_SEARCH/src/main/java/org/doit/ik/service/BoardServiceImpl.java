package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.mapper.BoardMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService{
	
	// DI(의존성 주입)
	//@Autowired
	//@Setter(onMethod=@__({@AnnotationsGoHere})) // 롬복에서 자동 주입
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getList(){
		log.info("> BoardServiceImpl.getList()");
		return this.boardMapper.getList();
	}

	@Override
	public void register(BoardVO boardVO) {
		log.info("> BoardServiceImpl.register()");
		//this.boardMapper.insert(boardVO);
		this.boardMapper.insertSelectKey(boardVO);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("> BoardServiceImpl.get()");
		return this.boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO boardVO) {
		log.info("> BoardServiceImpl.modify()");
		return this.boardMapper.update(boardVO) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("> BoardServiceImpl.remove()");
		return this.boardMapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria criteria) {
		log.info("> BoardServiceImpl.getListWithPaging()");
		return this.boardMapper.getListWithPaging(criteria);
	}

	@Override
	public int getTotal(Criteria criteria) {
		log.info("> BoardServiceImpl.getTotal()");
		return this.boardMapper.getTotalCount(criteria);
	}
	
}
