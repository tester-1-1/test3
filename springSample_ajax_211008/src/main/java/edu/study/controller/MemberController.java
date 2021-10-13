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
		//ArrayList 배열 하나하나의 원소는 MemebrVO 이다.
		ArrayList<MemberVO> alist = new ArrayList<MemberVO>();
		
		//회원정보 5개 등록하기 -> MemberVO 객체 5개 만들기
		MemberVO mv1 = new MemberVO();
		mv1.setName("홍길동1");
		mv1.setAge(21);
		mv1.setAddr("전주시 덕진구");
		alist.add(mv1);
		
		MemberVO mv2 = new MemberVO();
		mv2.setName("홍길동2");
		mv2.setAge(22);
		mv2.setAddr("전주시 완산구");
		alist.add(mv2);
		
		MemberVO mv3 = new MemberVO();
		mv3.setName("홍길동3");
		mv3.setAge(23);
		mv3.setAddr("전주시 금암동");
		alist.add(mv3);
		
		MemberVO mv4 = new MemberVO();
		mv4.setName("홍길동4");
		mv4.setAge(24);
		mv4.setAddr("전주시 서신동");
		alist.add(mv4);
		
		MemberVO mv5 = new MemberVO();
		mv5.setName("홍길동5");
		mv5.setAge(25);
		mv5.setAddr("전주시 인후동");
		alist.add(mv5);
		
		//화면으로 보내기 위해 model 객체 사용
		//addAttribute를 사용하면 web.xml의 Dispatcher Servlet이 알아서 setAttribute를 해준다.
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
		
		System.out.println("제목>>>>>"+mv.getName());
		System.out.println("작성자>>>>>"+mv.getAge());
		System.out.println("내용>>>>>"+mv.getAddr());
		
		return "redirect:/Member/view.do";
	}
	
}
