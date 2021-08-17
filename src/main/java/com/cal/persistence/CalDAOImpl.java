package com.cal.persistence;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cal.domain.CalVO;

@Repository
public class CalDAOImpl implements CalDAO {

 // ���̹�Ƽ�� 
 @Inject
 private SqlSession sql;
 
 // ����
 private static String namespace = "com.cal.mappers.calMapper";
  
 // �ۼ�
 @Override
 public void insert(CalVO vo) throws Exception {
  sql.insert(namespace + ".insert", vo);
 }
 
 // ��ȸ
 @Override
 public CalVO read(int calNum) throws Exception {
  return sql.selectOne(namespace + ".read", calNum);
 }

 // ����
 @Override
 public void update(CalVO vo) throws Exception {
  sql.update(namespace + ".update", vo);
 }

 // ����
 @Override
 public void delete(int calNum) throws Exception {
  sql.delete(namespace + ".delete", calNum);
 }

}