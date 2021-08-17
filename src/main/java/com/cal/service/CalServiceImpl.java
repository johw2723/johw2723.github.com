package com.cal.service;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;

import com.cal.domain.CalVO;
import com.cal.persistence.CalDAO;

@Repository
public class CalServiceImpl implements CalService {
	
	// 마이바티스 
	@Inject
 	private CalDAO dao;
	
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
		//System.out.println("Service :: vo.getResult = " + vo.getResult());
		return vo;	
	}
   
 	// 작성
 	@Override
 	public void insert(CalVO vo) throws Exception {
	 	dao.insert(cal(vo));
 	}
 	// 조회

 	@Override
 	public CalVO read(int calNum) throws Exception {
	 	return dao.read(calNum);
 	}

 	// 수정
 	@Override
 	public void update(CalVO vo) throws Exception {
	 	dao.update(cal(vo));
 	}

 	// 삭제
 	@Override
 	public void delete(int calNum) throws Exception {
	 	dao.delete(calNum);
 	}

}