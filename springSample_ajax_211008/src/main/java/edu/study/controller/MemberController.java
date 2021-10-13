package edu.study.controller;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.study.vo.MemberVO;

@RequestMapping(value="/Member")
@Controller
public class MemberController {

	@RequestMapping(value="/list.do")
	public String mList(Locale locale, Model model) {
		//ArrayList �迭 �ϳ��ϳ��� ���Ҵ� MemebrVO �̴�.
		ArrayList<MemberVO> alist = new ArrayList<MemberVO>();
		
		//ȸ������ 5�� ����ϱ� -> MemberVO ��ü 5�� �����
		MemberVO mv1 = new MemberVO();
		mv1.setName("ȫ�浿1");
		mv1.setAge(21);
		mv1.setAddr("���ֽ� ������");
		alist.add(mv1);
		
		MemberVO mv2 = new MemberVO();
		mv2.setName("ȫ�浿2");
		mv2.setAge(22);
		mv2.setAddr("���ֽ� �ϻ걸");
		alist.add(mv2);
		
		MemberVO mv3 = new MemberVO();
		mv3.setName("ȫ�浿3");
		mv3.setAge(23);
		mv3.setAddr("���ֽ� �ݾϵ�");
		alist.add(mv3);
		
		MemberVO mv4 = new MemberVO();
		mv4.setName("ȫ�浿4");
		mv4.setAge(24);
		mv4.setAddr("���ֽ� ���ŵ�");
		alist.add(mv4);
		
		MemberVO mv5 = new MemberVO();
		mv5.setName("ȫ�浿5");
		mv5.setAge(25);
		mv5.setAddr("���ֽ� ���ĵ�");
		alist.add(mv5);
		
		//ȭ������ ������ ���� model ��ü ���
		//addAttribute�� ����ϸ� web.xml�� Dispatcher Servlet�� �˾Ƽ� setAttribute�� ���ش�.
		model.addAttribute("alist", alist);
		
		return "member/list";
	}
	
	@RequestMapping(value="/view.do")
	public String view(Locale locale, Model model) {
		
		return "member/view";
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modify(Locale locale, Model model) {
		
		return "member/modify";
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(Locale locale, Model model, MemberVO mv) {
		
		System.out.println("����>>>>>"+mv.getName());
		System.out.println("�ۼ���>>>>>"+mv.getAge());
		System.out.println("����>>>>>"+mv.getAddr());
		
		return "redirect:/Member/view.do";
	}
	
}
