package edu.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.study.dao.SampleDAO;
import edu.study.vo.SampleVO;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	SampleDAO sampleDao;
	
	@Override
	public List<SampleVO> list() throws Exception{
		
		return sampleDao.list();
	}
	
	@Override
	public void insert(SampleVO vo) throws Exception{
		sampleDao.insert(vo);
	}
	
	@Override
	public SampleVO detail(int sidx) throws Exception{
		
		return sampleDao.detail(sidx);
	}

	@Override
	public void modify(SampleVO vo) throws Exception {
		sampleDao.modify(vo);
		
	}

	@Override
	public void delete(int sidx) throws Exception {
		sampleDao.delete(sidx);
		
	}
}
