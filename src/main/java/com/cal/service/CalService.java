package com.cal.service;

import com.cal.domain.CalVO;

public interface CalService {
		// �ۼ�
		public void insert(CalVO vo) throws Exception;
		 
		// ��ȸ
		public CalVO read(int calNum) throws Exception;
		 
		// ����
		public void update(CalVO vo) throws Exception;
		 
		// ����
		public void delete(int calNum) throws Exception;
}
