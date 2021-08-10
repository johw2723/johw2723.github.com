DROP TABLE tbl_Member;

create table tbl_member(
   memid      varchar(20) not null,
   pw         varchar(20) not null,
   email      varchar(30) not null,
   phone      varchar(13) not null,
   address    varchar(100),
   payment    varchar(50), 
   memberDate DATE
);

alter table tbl_member add primary key (memid);

---------------------------------
select * from tbl_member;
---------------------------------
