package dao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO() {
		try {
			Context ic=new InitialContext();
			ds=(DataSource)ic.lookup("java:comp/env/jdbc/Oracle11g");                
        } catch(NamingException e ) {
        	e.printStackTrace();
        }		
	}
	
	public void insertMember(MemberVO vo) {
		try {
			con = ds.getConnection(); 
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into tbl_member");
			sql.append("(memId, pw, email, phone, address, payment, memberDate) ");
			sql.append("values(?, ?, ?, ?, ?, ?, sysdate)");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getMemId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getPayment());
			int i = pstmt.executeUpdate();	
			System.out.println(i+"행이 추가되었습니다.");			
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

	public boolean idCheck(String memId) {
		boolean x= false;
		try {
			con = ds.getConnection();	
		
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT memId");
			sql.append("	FROM tbl_MEMBER");
			sql.append("	WHERE MEMID=?");	
					
			pstmt = con.prepareStatement(sql.toString());		
			pstmt.setString(1, memId);		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x= true; //해당 아이디 존재			
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return x;	
	}

	public void updateMember(MemberVO vo) {
		try {
			con = ds.getConnection(); 
			System.out.println("DAO :: updateMember() 실행");		
			
			String getSql = "SELECT * FROM tbl_MEMBER WHERE memID=?";
			PreparedStatement getPstmt = con.prepareStatement(getSql);
			getPstmt.setString(1, vo.getMemId());
			rs = getPstmt.executeQuery(); 
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE tbl_MEMBER SET");
			sql.append("	PW=?, EMAIL=?, PHONE=?, ADDRESS=?, PAYMENT=?");
			sql.append("	WHERE MEMID=?");
					
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			
			// 주소와 결제방법 모두 null 값인 경우
			if(("".equals(vo.getAddress()) || vo.getAddress() == null) && ("".equals(vo.getPayment()) || vo.getPayment() == null)) {			
				if(rs.next()) {
					vo.setAddress(rs.getString("address"));
					pstmt.setString(4, vo.getAddress());
					vo.setPayment(rs.getString("payment"));
					pstmt.setString(5, vo.getPayment());
				}	
			// 주소만 null값인 경우	
			} else if(("".equals(vo.getAddress()) || vo.getAddress() == null) && !("".equals(vo.getPayment()) || vo.getPayment() == null)) {
				if(rs.next()) {
					vo.setAddress(rs.getString("address"));
					pstmt.setString(4, vo.getAddress());
				}				
				pstmt.setString(5, vo.getPayment());
			// 결제방법만 null값인 경우
			} else if(("".equals(vo.getPayment()) || vo.getPayment() == null) && !("".equals(vo.getAddress()) || vo.getAddress() == null)) {			
				pstmt.setString(4, vo.getAddress());
				if(rs.next()) {
					vo.setPayment(rs.getString("payment"));
					pstmt.setString(5, vo.getPayment());						
				}
			} else {
				pstmt.setString(4, vo.getAddress());
				pstmt.setString(5, vo.getPayment());
			}	

			pstmt.setString(6, vo.getMemId());
			
			int i = pstmt.executeUpdate();
			System.out.println(i+"행이 수정되었습니다.");
		
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();		
		} finally {
			try {
				rs.close();;
				pstmt.close();
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public MemberVO MemberInfo(String memId) {
		MemberVO vo = new MemberVO();
		try {
			con = ds.getConnection(); 
			StringBuffer sql = new StringBuffer();
			
			
			sql.append("SELECT * FROM tbl_MEMBER");
			sql.append("	WHERE memID=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				vo.setMemId(rs.getString("memid"));
				vo.setPw(rs.getString("pw"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setPayment(rs.getString("payment"));
				vo.setDate(rs.getString("memberDate"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	public ArrayList<MemberVO> MemberList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			con = ds.getConnection(); 
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT * FROM tbl_MEMBER");
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	
				MemberVO vo = new MemberVO();
				vo.setMemId(rs.getString("memid"));
				vo.setPw(rs.getString("pw"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setPayment(rs.getString("payment"));
				vo.setDate(rs.getString("memberDate"));
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void deleteMember(String memId) {
		try {
			con = ds.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("DELETE FROM tbl_MEMBER");
			sql.append("	WHERE MEMID=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, memId);
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "행이 삭제되었습니다.");
			
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
}
