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

@Repository	//@Repository -> @Component ������ ������̼� 	//@Repository -> �ܺ��ڿ������ ����ϴ� ��ü ����
public class BoardDAO {

	@Autowired
	DataSource ds;	//import javax.sql.DataSource; �ϱ�
	
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
			//rs�� ���� �ݾƾ� �ϴ� ��ü�̴�. ���� rs ��ü�� ���ϰ����� ���� �� ����. ���� ���ο� ��ü�� rs�� �����͸� ��Ƽ� �����Ѵ�.
			
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
		BoardVO vo = null;	//������ ��ü �̸� ����
		
		try {
			conn = ds.getConnection();
			
			//String sql = "select * from openb_board where bidx="+bidx; �� ������ ����� �����Ѵ�.
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
			//bidx�� pk(primary key)�̴�.
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
