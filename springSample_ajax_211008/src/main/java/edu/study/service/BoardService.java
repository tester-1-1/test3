package edu.study.service;

import java.util.ArrayList;

import edu.study.vo.BoardVO;

public interface BoardService {
	//���� �����Ͻ� ������ �ٲ� �� �ֵ��� �������̽��� ����Ѵ�.
	
	//�������̽� ���������� ���� -> �⺻ public ���� ����
	//�������̽��� �ܺο��� �����Ϸ��� ����ϴ°��̱� ������ public���� �����ȴ�.
	
	//DB ���ᶧ���� throws�� �Է��Ѵ�.
	ArrayList<BoardVO> list() throws Exception;
	BoardVO detail(int bidx) throws Exception;
	int modify(BoardVO vo) throws Exception;
	void delete(int bidx) throws Exception;
}
