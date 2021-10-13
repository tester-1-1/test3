package edu.study.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.study.service.SampleService;
import edu.study.vo.SampleVO;

@RequestMapping(value="/ajax")
@Controller
public class AjaxController {

	@Autowired
	SampleService sampleService;
	
	@RequestMapping(value="/list.do")
	public String list(Locale locale, Model model) {
		return "ajax/list";
	}
	//�ѱ۱��� ���� -> produces = "aplication/test;charset=utf8"
	@RequestMapping(value="/ajaxlist.do", produces="application/test;charset=utf8")
	@ResponseBody	//���䵥���Ͱ� ���ϰ��� �ȴ�.
	public String ajaxlist(Locale locale, Model model, String sampleData) {
		System.out.println("sampleData>>>>>"+sampleData);
		return "testtest";
	}
	
	@RequestMapping(value="/test.do", produces="application/test;charset=utf8")
	@ResponseBody	//���䵥���Ͱ� ���ϰ��� �ȴ�.
	public String ajaxtest(String param) {
		System.out.println("testData>>>>>"+param);
		return param;
	}
	
	@RequestMapping(value="/alllist.do")
	@ResponseBody	//���䵥���Ͱ� ���ϰ��� �ȴ�.
	public List<SampleVO> alllist() throws Exception{
		
		List<SampleVO> list = sampleService.list();
		
		return list;
	}
	
	@RequestMapping(value="/view.do")
	@ResponseBody	//���䵥���Ͱ� ���ϰ��� �ȴ�.
	public SampleVO view(int sidx) throws Exception{
		
		SampleVO vo = sampleService.detail(sidx);
		
		return vo;
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	@ResponseBody	//���䵥���Ͱ� ���ϰ��� �ȴ�.
	public SampleVO modify(int sidx) throws Exception{
		
		SampleVO vo = sampleService.detail(sidx);
		
		return vo;
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.POST, produces = "application/test;charset=UTF-8")
	@ResponseBody	//���䵥���Ͱ� ���ϰ��� �ȴ�.
	public void modify(SampleVO vo) throws Exception{
		//System.out.println("���� ����");
		System.out.println("������ stitle : "+vo.getStitle());
		System.out.println("������ scontents : "+vo.getScontents());
		sampleService.modify(vo);
		//System.out.println("���� �� stitle : "+vo.getStitle());
		
		
	}
	
	@RequestMapping(value="/delete.do")
	@ResponseBody
	public void delete(int sidx) throws Exception{
		sampleService.delete(sidx);
	}
	
	
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	@ResponseBody
	public void insert(SampleVO vo) throws Exception{
		System.out.println("insert post");
		sampleService.insert(vo);
		System.out.println("�۾��� stitle : "+vo.getStitle());
	}
	
}
