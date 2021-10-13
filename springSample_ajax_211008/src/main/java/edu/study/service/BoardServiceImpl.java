package edu.study.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.study.dao.BoardDAO;
import edu.study.vo.BoardVO;

//BoardServiceImpl Ŭ������ BoardService �������̽��� ���� Ŭ�����̴�.
@Service	//service Ÿ������ bean ����(��ü ����)
public class BoardServiceImpl implements BoardService{
	
	@Autowired	//BoardDAO Ÿ���� ��ü�� ������ �־�� @Autowired�� ��ü�� ���Եȴ�.
	BoardDAO boardDao;
	
	@Override
	public ArrayList<BoardVO> list() throws Exception{
		
		ArrayList<BoardVO> list = boardDao.list();	//�ܺθ�� ������ Dao���� �Ѵ�. -> Dao�� ��û�� ������.
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
