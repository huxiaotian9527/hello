
CREATE TABLE `te` (
  `name` char(20) DEFAULT NULL,
  `lesson` char(20) DEFAULT NULL,
  `mark` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('john', 'Math', 60);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('john', 'Eng', 50);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('john', 'HIstory', 56);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('Mike', 'Eng', 51);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('Mike', 'Math', 59);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('Mike', 'HIstory', 55);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('Mark', 'Eng', 71);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('Mark', 'Math', 89);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('Mark', 'HIstory', 95);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('mm', 'Eng', 61);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('mm', 'Math', 79);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('mm', 'HIstory', 85);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('f', 'Eng', 51);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('f', 'Math', 69);
INSERT INTO `te`(`name`, `lesson`, `mark`) VALUES ('f', 'HIstory', 95);

--查询每科最高成绩
select * from te as a where not exists(
select 1 from te as b where a.lesson=b.lesson and a.mark<b.mark
)

--查询每科成绩前三
select * from te t
where
(select count(1) from te where lesson=t.lesson and mark>t.mark)< 3
order by lesson,mark desc