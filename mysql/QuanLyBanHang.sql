create database QuanLyBanHang;
use QuanLyBanHang;
create table Customer(
	cId int not null auto_increment primary key,
    cName varchar (30) not null,
    cAge int
);
create table Orders(
	oId int auto_increment primary key,
    oDate date,
    oTotalPrice double,
    cId int not null,
    foreign key (cId) references Customer (cId)
);
create table Product(
	pId int not null auto_increment primary key,
    pName varchar (30) not null,
    pPrice double not null default 0
);
create table OderDetail(
	oId int not null,
    pId int not null,
    odQTY int,
    foreign key (oId) references Orders(oId),
    foreign key (pId) references Product(pId)
);