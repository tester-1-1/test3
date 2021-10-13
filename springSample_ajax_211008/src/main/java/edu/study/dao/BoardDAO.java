package edu.study.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.study.vo.BoardVO;

@Repository	//@Repository -> @Component 하위의 어노테이션 	//@Repository -> 외부자원연결시 사용하는 객체 생성
public class BoardDAO {

	@Autowired
	DataSource ds;	//import javax.sql.DataSource; 하기
	
	public ArrayList<BoardVO> list() throws Exception {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> blist = new ArrayList<BoardVO>();
		
		
		try {
			conn = ds.getConnection();
			
			String sql = "select * from openb_board";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			//rs은 쓰고 닫아야 하는 객체이다. 따라서 rs 자체를 리턴값으로 보낼 수 없다. 따라서 새로운 객체에 rs의 데이터를 담아서 리턴한다.
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setTitle(rs.getString("subject"));
				vo.setBidx(rs.getInt("bidx"));
				vo.setContent(rs.getString("contents"));
				vo.setWriter(rs.getString("writer"));
				blist.add(vo);
			}
			
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				conn.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		}
		
		return blist;
	}
	
	public BoardVO detail(int bidx) throws Exception {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BoardVO vo = null;	//리턴할 객체 미리 생성
		
		try {
			conn = ds.getConnection();
			
			//String sql = "select * from openb_board where bidx="+bidx; 도 동일한 결과를 도출한다.
			String sql = "select * from openb_board where bidx=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bidx);
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setTitle(rs.getString("subject"));
				vo.setBidx(rs.getInt("bidx"));
				vo.setContent(rs.getString("contents"));
				vo.setWriter(rs.getString("writer"));
				
			}
			
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				conn.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		}
		
		return vo;
	}
	
	public int modify(BoardVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement psmt = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			//bidx는 pk(primary key)이다.
			String sql = "update openb_board set subject=?, writer=?, contents=? where bidx=?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getWriter());
			psmt.setString(3, vo.getContent());
			psmt.setInt(4, vo.getBidx());
			result = psmt.executeUpdate();
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.close();
			}
			if(psmt != null) {
				psmt.close();
			}
		}
		
		return result;
	}
	
	public void delete(int bidx) throws Exception {
		Connection conn = null;
		PreparedStatement psmt = null;
				
		try {
			conn = ds.getConnection();
			
			String sql = "delete from openb_board where bidx=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bidx);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.close();
			}
			if(psmt != null) {
				psmt.close();
			}
		}
		
	}
}
