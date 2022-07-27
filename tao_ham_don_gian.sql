SET GLOBAL log_bin_trust_function_creators = 1;
DELIMITER $$
create function My_sum(a Integer, b Integer)
returns Integer
begin
	declare num Integer;
    declare sum Integer;
    set num = 2;
    if(a > 10 || b > 10) then
		set sum = a + b;
        set sum = sum*num;
	else
		set sum = a + b;
	end if;
    return sum;
end $$