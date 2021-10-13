package edu.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.study.service.BoardService;
import edu.study.vo.BoardVO;

@RequestMapping(value="/Board")	//이 controller에 들어오기 위해서는 반드시 처음에 /Board 가 들어있어야 한다.
@Controller
public class BoardController {
	
	@Autowired	//BoardService 타입의 객체를 BoardController에 주입한다.
	BoardService boardService;
	
	//DispatcherServlet이 객체를 관리한다.
	//DispatcherServlet이 메소드를 호출한다.
	@RequestMapping(value = "/list.do")
	public String bList(Locale locale, Model model) throws Exception {
		System.out.println("가상경로 Board/list.do 로 호출된 메소드 입니다.");
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("첫번째 데이터");
		list.add("두번째 데이터");
		list.add("세번째 데이터");
		list.add("네번째 데이터");
		list.add("다섯번째 데이터");
		
		//request 객체 대신 model 객체를 사용해서 데이터를 담는다.
		//model에 담아있는 데이터는 dispatcher가 request에 담아준다.
		model.addAttribute("list", list);
		
		//ArrayList 타입으로, 원소로 BoardVO를 받는다.
		//데이터 받을 그릇 만들기
		ArrayList<BoardVO> blist = boardService.list();	//필드.메소드() -> 필드에 객체가 들어있다. 안 들어있으면 NullPointException 발생
		
		model.addAttribute("blist", blist);
		
		return "board/list";	// WEB-INF/views/list.jsp를 찾는다.
	}
	
	@RequestMapping(value="/view.do")
	public String view(Locale locale, Model model, int bidx) throws Exception {
		/*
			1. bidx 가지고 온 이유 : bidx와 일치하는 게시글 한 건을 DB에서 꺼내오기 위함.
			2. view 메소드의 목적 : view.do를 요청한 브라우저에게 view.jsp 포워드하기 위함.
			2-1. bidx를 이용하여 DB에서 가져온 게시글 한 건을 view.jsp로 전달한다.
			
			1. service로  DB에서 데이터 한 건을 가져오도록 요청(매개값 사용).
			2. service에서는 controller에서 1번에 대한 요청을 받음.
			3. service에서는 controller의 요청을 처리하기 위하여 dao로 데이터를 요청한다(매개값을 사용하여 bidx 전달)
			4. doa에서는 service로부터 요청받은 데이터를 DB에 연결하여 조회함.
			5. DB에서 조회한 데이터를 service로 반환할 작업 처리(resultset에 있는 데이터를 반환 타입으로 가공)
			6. return 키워드를 사용하여 가공한 데이터를 service로 반환
			7. service에서는 dao로부터 받은 데이터를 return을 사용하여 controller로 전달
			8. controller에서는 service에서 받아온 데이터를 화면으로 전달(model 객체 사용)
		*/
		
		BoardVO vo = boardService.detail(bidx);
		model.addAttribute("vo", vo);
		
			return "board/view";
		}
	
	
	//수정하기 화면 만들기
	/*
		1.수정화면에 대한 가상경로는 /Board/modify.do 입니다.
		2.가상경로로 화면에 출력되는 화면은 /WEB-INF/views/board/modify.jsp 입니다.
		3.modify.jsp 화면에서는 글제목, 작성자, 글내용을 입력할 수 있는 입력 양식이 존재합니다.
		4.modify.jsp 화면에서 저장버튼을 클릭하면 /Board/modifyOk.do 가상 경로로 입력된
		  데이터를 전송합니다.
		5./Board/modifyOk.do의 컨트롤러에서는 입력받은 정보를 모두 console로 출력합니다.
		6./Board/modifyOk.do에서는 /Board/view.do로 리다이렉트 됩니다.
	*/
	
	@RequestMapping(value="/modify.do", method = RequestMethod.GET)
	public String modify(Locale locale, Model model, int bidx) throws Exception {
		/*
			화면으로부터 넘어온 bidx를 이용하여 한 건의 데이터를 찾아 화면으로 전달
		*/
		BoardVO vo = boardService.detail(bidx);
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	
	/*
		211004 과제
		1. 수정 화면에서 데이터 입력 후 저장 버튼 클릭시 실제 DB에 데이터 update후 상세화면으로 이동할 수 있도록 구현하세요
		2. 상세화면에서 삭제 버튼을 만든 후 클릭시 데이터 삭제 후 목록 화면으로 이동할 수 있도록 구현하세요
	*/
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modifyOk(Locale locale, Model model, BoardVO vo) throws Exception {
		//로직처리
		System.out.println("제목>>>>>"+vo.getTitle());
		System.out.println("작성자>>>>>"+vo.getWriter());
		System.out.println("내용>>>>>"+vo.getContent());
		//DB에 입력받은 정보 업데이트
		int result = boardService.modify(vo);
		
		//다시 controller로 요청하기 위해 redirect를 사용한다.
		//redirect:이동하고자 하는 가상경로
		return "redirect:/Board/view.do?bidx="+vo.getBidx();
	}
	
	@RequestMapping(value="/delete.do")
	public String delete(Locale locale, Model model, int bidx) throws Exception {
		
		boardService.delete(bidx);
		
		return "redirect:/Board/list.do";
	}
	
}
