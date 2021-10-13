package edu.study.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.study.vo.BoardUseMyBatisVO;

@Repository
public class BoardUseMybatisDAO {
	
	//ctrl+shift+o : �˾Ƽ� mybatis�� import ���ش�
	@Autowired
	private SqlSession sqlSession;
	//mybatis�� ��Ͽ� ���ؼ� �������̽��� ���� -> ArrayList �迭�� �ƴ� �׳� list�� �޴´�
	public List<BoardUseMyBatisVO> list() throws Exception{
		
		return sqlSession.selectList("edu.study.mapper.boardUseMybatisMapper.listBoard");
	}
}
