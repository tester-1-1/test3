package edu.study.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.study.vo.FreeBoardVO;

@RequestMapping(value = "/Free")
@Controller
public class FreeController {
	@RequestMapping(value = "/list.do")
	//변수때문에 locale을 선언하지만 잘 사용하지 않음.(?)
	public String fList(Locale locale, Model model) {	//무조건 return 타입은 String이어야 한다. -> 왜?
		//System.out.println("가상경로 Free/list.do 로 호출된 메소드 입니다.");
		
		//data 넘길때 model을 사용한다
		String testData = "첫번째 데이터입니다.";
		String url = "/Free/list.do";
		//testData를 화면으로 넘기기 위해서 model을 사용한다.
		model.addAttribute("test1", testData);
		model.addAttribute("url",url);
		
		//WEB-INF/views/free/list.jsp
		return "free/list";
	}
	
	@RequestMapping(value="/view.do")
	public String view(Locale locale, Model model) {
		String url = "/Free/view.do";
		
		model.addAttribute("url",url);
		
		return "free/view";
	}
	
	//forward 방식은 주로 get 방식을 사용한다.
	@RequestMapping(value="/modify.do", method = RequestMethod.GET)
	public String modify(Locale locale, Model model) {
		String url = "/Free/modify.do";
		
		model.addAttribute("url",url);
		
		return "free/modify";
	}
	
	//경로가 같아도 전송방식이 get과 post로 다르면 동일경로로 사용할 수 있다.
	//문자열을 리턴화면 화면을 출력한다
	@RequestMapping(value="/modify.do", method = RequestMethod.POST)
	public String modify(Locale locale, Model model, FreeBoardVO vo, String testField) {
		//로직처리
		System.out.println("t1>>>>>"+vo.getT1());
		System.out.println("t2>>>>>"+vo.getT2());
		System.out.println("testField>>>>>"+testField);
		
		//다시 controller로 요청하기 위해 redirect를 사용한다.
		//redirect:이동하고자 하는 가상경로
		return "redirect:/Free/list.do";
	}
	
}
