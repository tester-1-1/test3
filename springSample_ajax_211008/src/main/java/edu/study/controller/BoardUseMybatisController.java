package edu.study.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.study.service.BoardUseMybatisService;
import edu.study.vo.BoardUseMyBatisVO;

@RequestMapping(value="/Mybatis/board")
@Controller
public class BoardUseMybatisController {

		@Autowired
		BoardUseMybatisService boardMService;
	
		@RequestMapping(value="/list.do")
		public String list(Locale locale, Model model) throws Exception{
			
			List<BoardUseMyBatisVO> list = boardMService.list();
			
			model.addAttribute("list", list);
			
			return "mybatis/list";
		}
	
}
