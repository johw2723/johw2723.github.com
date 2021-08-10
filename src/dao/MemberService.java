package dao;

import java.util.ArrayList;

public class MemberService {
	private MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public void insertMember(MemberVO vo) {
		dao.insertMember(vo);
	}

	public boolean idCheck(String memId) {
		return dao.idCheck(memId);
	}

	public void updateMember(MemberVO vo) {
		dao.updateMember(vo);
		
	}

	public MemberVO MemberInfo(String memId) {
		return dao.MemberInfo(memId);
	}

	public ArrayList<MemberVO> MemberList() {
		return dao.MemberList();
	}

	public void deleteMember(String memId) {
		dao.deleteMember(memId);
		
	}

}
