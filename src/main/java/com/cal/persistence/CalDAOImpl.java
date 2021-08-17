package com.cal.persistence;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cal.domain.CalVO;

@Repository
public class CalDAOImpl implements CalDAO {

 // 마이바티스 
 @Inject
 private SqlSession sql;
 
 // 매퍼
 private static String namespace = "com.cal.mappers.calMapper";
  
 // 작성
 @Override
 public void insert(CalVO vo) throws Exception {
  sql.insert(namespace + ".insert", vo);
 }
 
 // 조회
 @Override
 public CalVO read(int calNum) throws Exception {
  return sql.selectOne(namespace + ".read", calNum);
 }

 // 수정
 @Override
 public void update(CalVO vo) throws Exception {
  sql.update(namespace + ".update", vo);
 }

 // 삭제
 @Override
 public void delete(int calNum) throws Exception {
  sql.delete(namespace + ".delete", calNum);
 }

}