package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.CalVO;

public class CalDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private String query;
	private ResultSet rs;
	private CalVO vo;
	
	public CalDAO() {
		try {
			Context ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void calInsert(CalVO vo) {
		try {
			con = ds.getConnection(); 
			query = "insert into cal(no, op1, op, op2, result) values(seq_cal.nextval, ?, ?, ?, ?)";		
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, vo.getOp1());
			pstmt.setString(2, vo.getOp());
			pstmt.setInt(3, vo.getOp2());
			pstmt.setInt(4, vo.getResult());
			pstmt.executeUpdate();				
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				pstmt.close();			
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 
	}
	public List<CalVO> calList() {
		//목록 가져오기		
		List<CalVO> list = new ArrayList<CalVO>();
		try {
			con = ds.getConnection();
			query = "SELECT * FROM cal ORDER BY no ASC"; //테이블 no 열 순차정렬을 위한 명령어
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				vo = new CalVO();
				vo.setNo(rs.getInt("no"));
				vo.setOp1(rs.getInt("op1"));
				vo.setOp(rs.getString("op").trim());
				vo.setOp2(rs.getInt("op2"));
				vo.setResult(rs.getInt("result"));
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {	
				// Close 순서 지키기
				rs.close();
				pstmt.close();			
				con.close();	
			} catch(NullPointerException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			} 
		}
		return list;
	}
	
	public CalVO calSelect(int no) {
		//목록 중 선택한 일부 가져오기		
		try {
			con = ds.getConnection();
			query = "SELECT * FROM cal Where no=?"; 
			pstmt = con.prepareStatement(query);		
			pstmt.setInt(1,  no);
			rs = pstmt.executeQuery();		
			if(rs.next()) { //여기서 반복문을 사용하는게 아니라 if문 사용 
				vo = new CalVO();
				vo.setOp1(rs.getInt("op1"));
				vo.setOp(rs.getString("op").trim());
				vo.setOp2(rs.getInt("op2"));
				vo.setResult(rs.getInt("result"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {	
				// Close 순서 지키기
				rs.close();
				pstmt.close();			
				con.close();	
			} catch(Exception e) {
				e.printStackTrace();
			} 
		}
		return vo;
	}
	
	public void calUpdate(CalVO vo) {		
		try {
			con = ds.getConnection();
			query = "update cal set op1=?, op=?, op2=?, result=? where no="+vo.getNo()+"";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, vo.getOp1());
			pstmt.setString(2, vo.getOp());
			pstmt.setInt(3, vo.getOp2());
			pstmt.setInt(4, vo.getResult());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				pstmt.close();			
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 
	}

	public void calDelete(int no) {
		try {
			con = ds.getConnection();
			query = "delete from cal where no="+no+"";		
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				pstmt.close();			
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 	
	}

	public void calDeleteAll() {
		try {
			con = ds.getConnection();	
			query = "delete from cal";		
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				pstmt.close();			
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 		
	}
}
