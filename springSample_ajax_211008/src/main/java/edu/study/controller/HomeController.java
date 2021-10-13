package edu.study.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller	//@Controller -> ������ �ش��ϴ� Ŭ���� ���� 
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/* value = "/"����  /�� �⺻ �������� ��Ÿ����.
		��, value�� ������ �̴�.
		method = RequestMethod.GET -> �⺻��η� ������ �� ȭ���� get ������� ä���� post ������� ä���� ���Ѵ�.
	*/
	@RequestMapping(value = "/", method = RequestMethod.GET)	//@RequestMapping���� �����θ� ã�´�
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//dispatcher servlet�� ���ϰ��� �޴´�.
		return "home";	//ViwResolver�� WEB-INF/views/home.jsp �� ã�´�
	}
		
	
	//FreeController �����ϱ�
	/*
		1.list ���� �޼ҵ尡 �����Ѵ�
		  -�����δ� /Free/list.do
		  -������ ȭ���� /WEB-INF/views/free/list.jsp
		2.view ���� �޼ҵ尡 �����Ѵ�.
		  -�����δ� /Free/view.do
		  -������ ȭ���� /WEB-INF/view/free/view.jsp
		3.modify ���� �޼ҵ尡 �����Ѵ�.
		  -�����δ� /Free/modify.do
		  -������ ȭ���� /WEb-INF/view/free/modify.jsp
		
		* list.jsp���� ��ũ�� view.do �̵� ����
		* view.jsp���� ��ũ�� modify.do �̵� ����
		* �� jsp������ �ش� �����ο� ���Ͽ� controller���� model ��ü�� ����Ͽ� �����ͷ� ȭ�鿡 ������ش�.
	*/
	
	
	/*
		member ��� ȭ�� �����
		
		1.home.jsp�� 'ȸ��������� �̵�' ��ũ�� �߰��մϴ�..
		1-1.��ũ Ŭ���� �̵��� ���� ��δ� '/Member/list.do'
		2./Member/list.do �������� ��Ʈ�ѷ� �޼ҵ忡���� 5���� ȸ�� �����
		  ex) name = ȫ�浿, age = 20, addr = ���ֽ� ������
		  ArrayList<MemberVO> alist = new ArrayList<MemberVO>(); ����ϱ�
		3.���� ȸ�� �迭�� /WEB-INF/views/member/list.jsp ���� ����մϴ�.
		4.'/Member/list.do'������ '/Member/view.do'�� �̵��� �� �ִ� ��ũ�� �����մϴ�.
		5.'/Member/view.do'�� /WEB-INF/views/member/view.jsp�� �������մϴ�.
		6.view.do������ '/Member/modify.do'�� �̵�(method=get ������� ���)
		7.'/Member/modify.do'������ /WEB-INF/views/member/modify.jsp�� �������մϴ�.
		8.modify.jsp������ 3���� ������ �Է¹��� �� �ִ� �Է¾�� ����
		9.modify.jsp���� �����ư Ŭ���� '/Member/modify.do' �Է� ������ �����մϴ�.(method=post)
		10.�Է¹��� ���� �ش� ��Ʈ�ѷ����� ��� ��� �� '/Member/view/do'�� �����̷�Ʈ�մϴ�. 
	*/
}
