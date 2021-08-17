package com.cal.persistence;

import com.cal.domain.CalVO;

public interface CalDAO {
	// 작성
	public void insert(CalVO vo) throws Exception;
	 
	// 조회
	public CalVO read(int calNum) throws Exception;
	 
	// 수정
	public void update(CalVO vo) throws Exception;
	 
	// 삭제
	public void delete(int calNum) throws Exception;
}