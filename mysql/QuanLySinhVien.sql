create database QuanLySinhVien;
use QuanLySinhVien;
create table Class(
	ClassId int not null auto_increment primary key,
	ClassName varchar(50) not null,
	Statuss bit
);
create table Student(
	StudentId int not null auto_increment primary key,
    StudentName varchar(50) not null,
    Address varchar(20),
    Statuss bit,
    ClassId int not null,
    foreign key (ClassId) references Class (ClassId)
);
create table Subjects(
	SubId int not null auto_increment primary key,
    SubName varchar(30) not null,
    Credit tinyint not null default 1 check (Credit >= 1),
    Statuss bit default 1
);
create table Mark(
	MarkId int not null auto_increment primary key,
    SubId int not null,
    StudentId int not null,
    Mark float default 0 check (mark between 0 and 100),
    ExamTimes tinyint default 1,
    unique (SubId, StudentId),
    foreign key (SubId) references Subjects (SubId),
    foreign key (StudentId) references Student (StudentId)
);