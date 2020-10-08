select INSTANCE_NAME from v$instance;
select * from v$version;
select * from all_tables WHERE owner='P3EDAADM';

-- oracle kill session
--01:活动的session
SELECT
       SQ.SQL_TEXT,
	   S.USERNAME,
       S.SID,
       S.SERIAL#,
       S.INST_ID,
       S.EVENT,
       S.WAIT_CLASS,
       S.SQL_EXEC_START,
       S.LOGON_TIME,
       S.ACTION
  FROM GV$SESSION S, GV$SQLAREA SQ
 WHERE S.STATUS = 'ACTIVE'
   AND S.USERNAME IS NOT NULL
   AND S.SQL_ID = SQ.SQL_ID;
--02:杀掉session
 Alter system kill session 'SID,SERIAL#'

--oracle最近执行的sql
select  last_load_time, sql_text from v$sql
where module not in ('jsenv.exe','w3wp.exe','SQL Developer') and rownum < 100
order by last_load_time desc ;

--查看对应关系
SELECT * FROM OBJECTATTRIBUTEMEANDEF_EQPTYPE  WHERE EQPTYPE ='CCSLI'  AND TYPENAME = 'CELLCOMPONENT_RUN'

--查看el下的坐标，根据MACHINENAME，PRODUCTSPECGROUPNAME分组
SELECT
	cp.MACHINENAME,
	cp.PRODUCTSPECGROUPNAME
FROM
	CELLCOMPONENT_RUN cp,
	CELLDEFECT cd
WHERE
	cp.CONDITIONID = cd.CELLCOMPONENTCONDITIONID
	AND cp.PROCESSOPERATIONNAME IN ('1200_SLI','UnknowProcess_ID_SLI')
	AND cp.LOGGEDOUTTIME > to_date( '2020-09-02 14:40:00', 'yyyy/mm/dd HH24:MI:SS' )
	AND cp.RESD1='OwnerP'
	GROUP BY cp.MACHINENAME,cp.PRODUCTSPECGROUPNAME;