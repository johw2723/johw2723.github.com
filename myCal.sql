DROP TABLE myCal ;
create table myCal (
	calNum number   not null,
	op1	number		not null,
	op	varchar(3)	not null,
	op2 number		not null,
	result number, 
	primary key(calNum)
);

DROP sequence myCal_seq;
create sequence myCal_seq;

-- 계산 기록
insert into myCal(calNum, op1, op, op2, result) values (myCal_seq.nextval, 3, 'add', 4, 7);

-- 계산 조회
select calnum, op1, op, op2, result from myCal where calNum = 1;

-- 계산 수정
update myCal
set
	op1 = 7,
	op = 'add',
	op2 = 3,
	result = 10
where calnum = 1;

-- 계산 삭제
delete from myCal
where calnum = 1;

-- 전체 조회
select * from MYCAL order by calNum asc;












