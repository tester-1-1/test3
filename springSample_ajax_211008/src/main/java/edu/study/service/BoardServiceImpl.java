package edu.study.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.study.dao.BoardDAO;
import edu.study.vo.BoardVO;

//BoardServiceImpl 클래스는 BoardService 인터페이스의 구현 클래스이다.
@Service	//service 타입으로 bean 생성(객체 생성)
public class BoardServiceImpl implements BoardService{
	
	@Autowired	//BoardDAO 타입의 객체를 가지고 있어야 @Autowired로 객체가 주입된다.
	BoardDAO boardDao;
	
	@Override
	public ArrayList<BoardVO> list() throws Exception{
		
		ArrayList<BoardVO> list = boardDao.list();	//외부모듈 접근은 Dao에서 한다. -> Dao로 요청을 보낸다.
		return list;
	}
	
	@Override
	public BoardVO detail(int bidx) throws Exception{
		
		BoardVO vo = boardDao.detail(bidx);
		return vo;
	}
	
	@Override
	public int modify(BoardVO vo) throws Exception{
		int value = boardDao.modify(vo);
		return value;
	}
	
	@Override
	public void delete(int bidx) throws Exception {
		boardDao.delete(bidx);
	}
		
	
}
