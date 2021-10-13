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
	//���������� locale�� ���������� �� ������� ����.(?)
	public String fList(Locale locale, Model model) {	//������ return Ÿ���� String�̾�� �Ѵ�. -> ��?
		//System.out.println("������ Free/list.do �� ȣ��� �޼ҵ� �Դϴ�.");
		
		//data �ѱ涧 model�� ����Ѵ�
		String testData = "ù��° �������Դϴ�.";
		String url = "/Free/list.do";
		//testData�� ȭ������ �ѱ�� ���ؼ� model�� ����Ѵ�.
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
	
	//forward ����� �ַ� get ����� ����Ѵ�.
	@RequestMapping(value="/modify.do", method = RequestMethod.GET)
	public String modify(Locale locale, Model model) {
		String url = "/Free/modify.do";
		
		model.addAttribute("url",url);
		
		return "free/modify";
	}
	
	//��ΰ� ���Ƶ� ���۹���� get�� post�� �ٸ��� ���ϰ�η� ����� �� �ִ�.
	//���ڿ��� ����ȭ�� ȭ���� ����Ѵ�
	@RequestMapping(value="/modify.do", method = RequestMethod.POST)
	public String modify(Locale locale, Model model, FreeBoardVO vo, String testField) {
		//����ó��
		System.out.println("t1>>>>>"+vo.getT1());
		System.out.println("t2>>>>>"+vo.getT2());
		System.out.println("testField>>>>>"+testField);
		
		//�ٽ� controller�� ��û�ϱ� ���� redirect�� ����Ѵ�.
		//redirect:�̵��ϰ��� �ϴ� ������
		return "redirect:/Free/list.do";
	}
	
}
