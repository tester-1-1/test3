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
@Controller	//@Controller -> 서블릿에 해당하는 클래스 파일 
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/* value = "/"에서  /는 기본 도메인을 나타난다.
		즉, value가 가상경로 이다.
		method = RequestMethod.GET -> 기본경로로 들어왔을 때 화면을 get 방식으로 채울지 post 방식으로 채울지 정한다.
	*/
	@RequestMapping(value = "/", method = RequestMethod.GET)	//@RequestMapping으로 가상경로를 찾는다
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//dispatcher servlet이 리턴값을 받는다.
		return "home";	//ViwResolver가 WEB-INF/views/home.jsp 를 찾는다
	}
		
	
	//FreeController 생성하기
	/*
		1.list 관련 메소드가 존재한다
		  -가상경로는 /Free/list.do
		  -보여질 화면은 /WEB-INF/views/free/list.jsp
		2.view 관련 메소드가 존재한다.
		  -가상경로는 /Free/view.do
		  -보여질 화면은 /WEB-INF/view/free/view.jsp
		3.modify 관련 메소드가 존재한다.
		  -가상경로는 /Free/modify.do
		  -보여질 화면은 /WEb-INF/view/free/modify.jsp
		
		* list.jsp에서 링크로 view.do 이동 가능
		* view.jsp에서 링크로 modify.do 이동 가능
		* 각 jsp에서는 해당 가상경로에 대하여 controller에서 model 객체를 사용하여 데이터로 화면에 출력해준다.
	*/
	
	
	/*
		member 등록 화면 만들기
		
		1.home.jsp에 '회원목록으로 이동' 링크를 추가합니다..
		1-1.링크 클릭시 이동할 가상 경로는 '/Member/list.do'
		2./Member/list.do 가상경로의 컨트롤러 메소드에서는 5개의 회원 만들기
		  ex) name = 홍길동, age = 20, addr = 전주시 덕진구
		  ArrayList<MemberVO> alist = new ArrayList<MemberVO>(); 사용하기
		3.만든 회원 배열을 /WEB-INF/views/member/list.jsp 에서 출력합니다.
		4.'/Member/list.do'에서는 '/Member/view.do'로 이동할 수 있는 링크를 제공합니다.
		5.'/Member/view.do'는 /WEB-INF/views/member/view.jsp로 포워드합니다.
		6.view.do에서는 '/Member/modify.do'로 이동(method=get 방식으로 사용)
		7.'/Member/modify.do'에서는 /WEB-INF/views/member/modify.jsp로 포워드합니다.
		8.modify.jsp에서는 3개의 정보를 입력받을 수 있는 입력양식 제공
		9.modify.jsp에서 저장버튼 클릭시 '/Member/modify.do' 입력 데이터 전송합니다.(method=post)
		10.입력받은 값을 해당 컨트롤러에서 모두 출력 후 '/Member/view/do'로 리다이렉트합니다. 
	*/
}
