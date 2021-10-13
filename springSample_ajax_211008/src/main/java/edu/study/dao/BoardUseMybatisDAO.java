package edu.study.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.study.vo.BoardUseMyBatisVO;

@Repository
public class BoardUseMybatisDAO {
	
	//ctrl+shift+o : 알아서 mybatis를 import 해준다
	@Autowired
	private SqlSession sqlSession;
	//mybatis는 목록에 대해서 인터페이스로 제공 -> ArrayList 배열이 아닌 그냥 list로 받는다
	public List<BoardUseMyBatisVO> list() throws Exception{
		
		return sqlSession.selectList("edu.study.mapper.boardUseMybatisMapper.listBoard");
	}
}
