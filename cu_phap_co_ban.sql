/* Cú pháp thêm 1 cột mới */
alter table donhang
add new_cot int

/* Cú pháp để chỉnh sửa mô tả của một cột */;
alter table donhang 
modify title char(20);

/* Cú pháp để thay đổi tên cột */;
alter table donhang
change column title producName varchar(30)

/* Cú pháp để xoá một cột trong bảng */;
alter table donhang 
drop column title

/* cú pháp để đổi ten bang */;
alter table donhang
rename oder
