USE QuanLySinhVien;
INSERT INTO Class
VALUES(1,"A1",1);
INSERT INTO Class
VALUES(2, "A2", 1);
INSERT INTO Class
VALUES(3, "B3", 0);
INSERT INTO Student (StudentName, Address, Phone, Statuss, ClassId)
VALUES (1, "PHU", "HA TINH", "0989989789", 1,1);
INSERT INTO Student (StudentName, Address, Phone, Statuss, ClassId)
VALUES (2, "THIEN", "HUE", "0989989678", 1,1);
INSERT INTO Student (StudentName, Address, Phone, Statuss, ClassId)
VALUES (3, "HUY DO", "HUE", "0989989345", 0,2);
INSERT INTO Subjects
VALUES(1, 'CF', 5, 1),
       (2, 'C', 6, 1),
       (3, 'HDJ', 5, 1),
       (4, 'RDBMS', 10, 1);
INSERT INTO Mark (SubId, StudentId, Mark, ExamTimes)
VALUES (1, 1, 8, 1),
       (1, 2, 10, 2),
       (2, 1, 12, 1);