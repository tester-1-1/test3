package edu.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.study.dao.BoardUseMybatisDAO;
import edu.study.vo.BoardUseMyBatisVO;

@Service
public class BoardUseMybatisServiceImpl implements BoardUseMybatisService{

	@Autowired
	BoardUseMybatisDAO boardMDao;
	
	@Override
	public List<BoardUseMyBatisVO> list() throws Exception{
		
		return boardMDao.list();
	}
	
}
