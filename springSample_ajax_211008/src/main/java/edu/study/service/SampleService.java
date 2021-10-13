package edu.study.service;

import java.util.List;

import edu.study.vo.SampleVO;

public interface SampleService {

	//SampleDAO�� �߻� �޼ҵ�
	List<SampleVO> list() throws Exception;
	void insert(SampleVO vo) throws Exception;
	SampleVO detail(int sidx) throws Exception;
	void modify(SampleVO vo) throws Exception;
	void delete(int sidx) throws Exception;
}
