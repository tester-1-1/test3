package edu.study.service;

import java.util.ArrayList;

import edu.study.vo.BoardVO;

public interface BoardService {
	//쉽게 비지니스 로직을 바꿀 수 있도록 인터페이스를 사용한다.
	
	//인터페이스 접근제한자 생략 -> 기본 public 으로 설정
	//인터페이스는 외부에서 접근하려고 사용하는것이기 때문에 public으로 설정된다.
	
	//DB 연결때문에 throws를 입력한다.
	ArrayList<BoardVO> list() throws Exception;
	BoardVO detail(int bidx) throws Exception;
	int modify(BoardVO vo) throws Exception;
	void delete(int bidx) throws Exception;
}
