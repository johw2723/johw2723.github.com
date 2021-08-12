package model;

import java.util.List;

import org.json.simple.JSONObject;

public class CalService {	
	private CalDAO cd;
	
	public CalService() {	
		cd = new CalDAO();
	}
	
	// 비즈니스 로직
    public CalVO cal(CalVO vo) {
		if(vo.getOp().equals("add")) {
			vo.setResult((vo.getOp1() + vo.getOp2()));
		} else if(vo.getOp().equals("sub")) {
			vo.setResult((vo.getOp1() - vo.getOp2()));
		} else if(vo.getOp().equals("mul")) {
			vo.setResult((vo.getOp1() * vo.getOp2()));
		} else if(vo.getOp().equals("div")) {
			vo.setResult((vo.getOp1() / vo.getOp2()));
		}
		return vo;
	}
    
    // 모듈화
    public void calInsert(CalVO vo) {    	
		cd.calInsert(cal(vo));
    }
    
    public List<CalVO> calList() {
		return cd.calList();
	}
    
    public CalVO calSelect(int no) {
    	return cd.calSelect(no);
    }
    
    public void calUpdate(CalVO vo) {    	
		cd.calUpdate(cal(vo));
    }

	public void calDelete(int no) {
		cd.calDelete(no);		
	}

	public void calDeleteAll() {
		cd.calDeleteAll();
		
	}
}
