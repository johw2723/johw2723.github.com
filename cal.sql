create table cal(
   no     int not null,
   op1    int not null,
   op     varchar(10) not null,
   op2    int not null,
   result int 
);

alter table cal add primary key (no);

drop sequence seq_cal;

create sequence seq_cal
increment by 1
start with 0
minvalue 0;

----------------------------------
select * from cal ORDER BY no ASC;
---------------------------------- 
  
  