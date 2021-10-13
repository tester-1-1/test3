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

@RequestMapping(value="/Board")	//�� controller�� ������ ���ؼ��� �ݵ�� ó���� /Board �� ����־�� �Ѵ�.
@Controller
public class BoardController {
	
	@Autowired	//BoardService Ÿ���� ��ü�� BoardController�� �����Ѵ�.
	BoardService boardService;
	
	//DispatcherServlet�� ��ü�� �����Ѵ�.
	//DispatcherServlet�� �޼ҵ带 ȣ���Ѵ�.
	@RequestMapping(value = "/list.do")
	public String bList(Locale locale, Model model) throws Exception {
		System.out.println("������ Board/list.do �� ȣ��� �޼ҵ� �Դϴ�.");
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("ù��° ������");
		list.add("�ι�° ������");
		list.add("����° ������");
		list.add("�׹�° ������");
		list.add("�ټ���° ������");
		
		//request ��ü ��� model ��ü�� ����ؼ� �����͸� ��´�.
		//model�� ����ִ� �����ʹ� dispatcher�� request�� ����ش�.
		model.addAttribute("list", list);
		
		//ArrayList Ÿ������, ���ҷ� BoardVO�� �޴´�.
		//������ ���� �׸� �����
		ArrayList<BoardVO> blist = boardService.list();	//�ʵ�.�޼ҵ�() -> �ʵ忡 ��ü�� ����ִ�. �� ��������� NullPointException �߻�
		
		model.addAttribute("blist", blist);
		
		return "board/list";	// WEB-INF/views/list.jsp�� ã�´�.
	}
	
	@RequestMapping(value="/view.do")
	public String view(Locale locale, Model model, int bidx) throws Exception {
		/*
			1. bidx ������ �� ���� : bidx�� ��ġ�ϴ� �Խñ� �� ���� DB���� �������� ����.
			2. view �޼ҵ��� ���� : view.do�� ��û�� ���������� view.jsp �������ϱ� ����.
			2-1. bidx�� �̿��Ͽ� DB���� ������ �Խñ� �� ���� view.jsp�� �����Ѵ�.
			
			1. service��  DB���� ������ �� ���� ���������� ��û(�Ű��� ���).
			2. service������ controller���� 1���� ���� ��û�� ����.
			3. service������ controller�� ��û�� ó���ϱ� ���Ͽ� dao�� �����͸� ��û�Ѵ�(�Ű����� ����Ͽ� bidx ����)
			4. doa������ service�κ��� ��û���� �����͸� DB�� �����Ͽ� ��ȸ��.
			5. DB���� ��ȸ�� �����͸� service�� ��ȯ�� �۾� ó��(resultset�� �ִ� �����͸� ��ȯ Ÿ������ ����)
			6. return Ű���带 ����Ͽ� ������ �����͸� service�� ��ȯ
			7. service������ dao�κ��� ���� �����͸� return�� ����Ͽ� controller�� ����
			8. controller������ service���� �޾ƿ� �����͸� ȭ������ ����(model ��ü ���)
		*/
		
		BoardVO vo = boardService.detail(bidx);
		model.addAttribute("vo", vo);
		
			return "board/view";
		}
	
	
	//�����ϱ� ȭ�� �����
	/*
		1.����ȭ�鿡 ���� �����δ� /Board/modify.do �Դϴ�.
		2.�����η� ȭ�鿡 ��µǴ� ȭ���� /WEB-INF/views/board/modify.jsp �Դϴ�.
		3.modify.jsp ȭ�鿡���� ������, �ۼ���, �۳����� �Է��� �� �ִ� �Է� ����� �����մϴ�.
		4.modify.jsp ȭ�鿡�� �����ư�� Ŭ���ϸ� /Board/modifyOk.do ���� ��η� �Էµ�
		  �����͸� �����մϴ�.
		5./Board/modifyOk.do�� ��Ʈ�ѷ������� �Է¹��� ������ ��� console�� ����մϴ�.
		6./Board/modifyOk.do������ /Board/view.do�� �����̷�Ʈ �˴ϴ�.
	*/
	
	@RequestMapping(value="/modify.do", method = RequestMethod.GET)
	public String modify(Locale locale, Model model, int bidx) throws Exception {
		/*
			ȭ�����κ��� �Ѿ�� bidx�� �̿��Ͽ� �� ���� �����͸� ã�� ȭ������ ����
		*/
		BoardVO vo = boardService.detail(bidx);
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	
	/*
		211004 ����
		1. ���� ȭ�鿡�� ������ �Է� �� ���� ��ư Ŭ���� ���� DB�� ������ update�� ��ȭ������ �̵��� �� �ֵ��� �����ϼ���
		2. ��ȭ�鿡�� ���� ��ư�� ���� �� Ŭ���� ������ ���� �� ��� ȭ������ �̵��� �� �ֵ��� �����ϼ���
	*/
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modifyOk(Locale locale, Model model, BoardVO vo) throws Exception {
		//����ó��
		System.out.println("����>>>>>"+vo.getTitle());
		System.out.println("�ۼ���>>>>>"+vo.getWriter());
		System.out.println("����>>>>>"+vo.getContent());
		//DB�� �Է¹��� ���� ������Ʈ
		int result = boardService.modify(vo);
		
		//�ٽ� controller�� ��û�ϱ� ���� redirect�� ����Ѵ�.
		//redirect:�̵��ϰ��� �ϴ� ������
		return "redirect:/Board/view.do?bidx="+vo.getBidx();
	}
	
	@RequestMapping(value="/delete.do")
	public String delete(Locale locale, Model model, int bidx) throws Exception {
		
		boardService.delete(bidx);
		
		return "redirect:/Board/list.do";
	}
	
}
