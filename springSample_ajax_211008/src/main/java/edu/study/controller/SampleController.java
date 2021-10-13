package edu.study.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.study.service.SampleService;
import edu.study.vo.SampleVO;

@RequestMapping(value="/Sample")
@Controller
public class SampleController {

	@Autowired
	SampleService sampleService;
	
	@RequestMapping(value="/list.do")
	public String list(Locale locale, Model model) throws Exception{
		//springtest 테이블의 모든 데이터 목록을 가져와 화면으로 전달하기
		
		List<SampleVO> list = sampleService.list();
		
		model.addAttribute("list", list);
		
		return "sample/list";
	}
	
	@RequestMapping(value="/save.do", method=RequestMethod.GET)
	public String save(Locale locale, Model model) throws Exception{
		
		return "sample/save";
	}
	
	@RequestMapping(value="/save.do", method=RequestMethod.POST)
	public String save(Locale locale, Model model, SampleVO vo) throws Exception{
		
		sampleService.insert(vo);
		System.out.println("sidx>>>>>"+vo.getSidx());
		
		return"redirect:/Sample/view.do?sidx="+vo.getSidx();
	}
	
	@RequestMapping(value="view.do")
	public String view(Locale locale, Model model, int sidx) throws Exception{
		
		//넘어온 sidx와 일치하는 springtest 테이블의 데이터를 찾아 화면으로 전달
		SampleVO vo = sampleService.detail(sidx);
		
		model.addAttribute("vo", vo);
		
		return "sample/view";
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modify(Locale locale, Model model, int sidx) throws Exception{
		SampleVO vo = sampleService.detail(sidx);
		model.addAttribute("vo", vo);
		return "sample/modify";
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(Locale locale, Model model, SampleVO vo) throws Exception{
		sampleService.modify(vo);
		return "redirect:/Sample/view.do?sidx="+vo.getSidx();
	}
	
	@RequestMapping(value="/delete.do")
	public String delete(int sidx) throws Exception{
		sampleService.delete(sidx);
		return "redirect:/Sample/list.do";
	}
	
}
