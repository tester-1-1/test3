package edu.study.controller;


import java.util.List;
import java.util.Locale;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.study.service.BoardService;
import edu.study.vo.BoardVO;

@RequestMapping(value="/Ajax")	
@Controller
public class AjaxController_bro {

	
//	method = RequestMethod.GET
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/list.do" )
	public String list() throws Exception  {	
		
		return "ajax/list";
	}
	
	@RequestMapping(value="/allList.do" )
	@ResponseBody	// 변수를 받고 일을 한뒤에 리턴 값으로 다시 돌려준다
	public List<BoardVO> allList() throws Exception  {	
			return boardService.list();
	}
	
	@RequestMapping(value="/view.do" )
	@ResponseBody	// 변수를 받고 일을 한뒤에 리턴 값으로 다시 돌려준다
	public BoardVO view(int bidx) throws Exception  {	
			return boardService.detail(bidx);
	}
	
	@RequestMapping(value="/modify.do", method = RequestMethod.GET)
	@ResponseBody	// 다시 돌려주는 값이다.
	public BoardVO modify(int bidx) throws Exception{
		return boardService.detail(bidx);
	}
	
	@RequestMapping(value="/modify.do", method = RequestMethod.POST, produces = "applcation/test;charset=UTF-8")
	@ResponseBody	// 다시 돌려주는 값이다.
	public String modify(BoardVO vo) throws Exception{
		boardService.modify(vo);
		return "save";
	}

	
	@RequestMapping(value="/delete.do")
	@ResponseBody	// 다시 돌려주는 값이다.
	public String delete(int bidx) throws Exception{
		boardService.delete(bidx);
		return "save";
	}
	
	
}
