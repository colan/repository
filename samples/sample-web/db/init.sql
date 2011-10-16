--http://dishell.javaeye.com/blog/298391
delimiter |
drop procedure if exists student_proc;
create PROCEDURE  student_proc(
    in username varchar(255), in password_md5 varchar(255)  
)
begin  
     select username,password_md5 from student;  
end;