delimiter $$
create procedure sp_insertStudent(
	IN sName varchar(50),
    IN sAddress varchar(50),
    IN sPhone varchar(50),
    IN sStatus boolean,
    IN sClassID int, 
    IN sAge int,
    OUT sCheck boolean,
    OUT message nvarchar(250)
)
begin
	# Kiem tra so dien thoai co ton tai hay chua
    set sCheck = true;
    set message = "";
    if(exists (SELECT * FROM db_qlysinhvien.student where phone = sPhone)) then
		set sCheck = false;
		set message = "Số điện thoại đã tồn tại";
    end if;
    # Kiêm tra tuổi >= 15
    if(sAge <= 15 ) then
		set sCheck = false;
		set message = CONCAT(message, "\r\n", "Chưa đủ tuổi");
    end if;
    if(sCheck = true) then
		INSERT INTO `db_qlysinhvien`.`student` (`StudentName`, `Address`, `Phone`, `Status`, `ClassId`, `age`) 
		VALUES (sName, sAddress, sPhone, sStatus, sClassID , sAge);
        set message = "Thêm thành công";
    end if;
end;
