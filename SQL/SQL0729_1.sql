USE SCOTT;

-- 20번 부서 사원들 중에서, 급여가 3000이상인 사원 전체 정보 보여줘(집합 연산자 사용하지 않기)
SELECT *
FROM   EMP
WHERE  DEPTNO = 20
AND    SAL >= 3000;

-- 급여가 2000이상 2500이하인 사원들 누구인지 이름이랑 부서 알려줘
SELECT ENAME, DEPTNO
FROM   EMP
WHERE  SAL BETWEEN 2000 AND 2500;

-- 사원 이름에 두번째 글자가 I인 사원 전체정보 보여줘
SELECT *
FROM   EMP
WHERE  ENAME LIKE "_I%";

-- 급여가 2000이상 3000이하 범위 이외의 값을 가진 사원들의 전체 정보 보여줘(not between a and b 사용하지 않기)
SELECT *
FROM   EMP
WHERE  SAL < 2000
OR     SAL > 3000;

-- 부서번호가 10,20인 사원들의 사원번호, 사원명, 부서번호를 중복없이 보여줘(집합연산자 사용하기)
SELECT EMPNO, ENAME, DEPTNO
FROM   EMP
WHERE  DEPTNO = 10
UNION
SELECT EMPNO, ENAME, DEPTNO
FROM   EMP
WHERE  DEPTNO = 20;



-- 128P 오라클 함수
-- UPPER LOWER / INITCAP <-- ORCLE (앞글자만 대문자)
SELECT ENAME, UPPER(ENAME), LOWER(ENAME)
FROM   EMP;

SELECT *
FROM   EMP
WHERE  UPPER(ENAME) LIKE UPPER('%scott%'); -- 입력한 문자가 소문자여도 검색 가능

-- LENGTH LENGTHB <-- ORCLE 
-- CHARACTER_LENGTH LENGTH
SELECT ENAME, LENGTH(ENAME), CHARACTER_LENGTH(ENAME)
FROM   EMP;

SELECT LENGTH('한글'), CHARACTER_LENGTH('한글'); -- LENGTH : 바이트 수 / CHARACTER_LENGTH : 글자 수

SELECT ENAME, CHARACTER_LENGTH(ENAME)
FROM   EMP
WHERE  CHARACTER_LENGTH(ENAME) >= 5;

-- SUBSTR(문자열, 시작 위치, 추출길이) : 문자 추출
SELECT JOB, SUBSTR(JOB,1,2), SUBSTR(JOB,3,2), SUBSTR(JOB,5) 
FROM   EMP;

-- INSTR(대상문자열, 찾을 문자) : 문자 찾기
SELECT INSTR('HELLO, ORACLE!', 'L') AS INSTR1;      -- HELLO, ORACLE! 에 L이 몇번째에 있는지
   -- ORACLE
	  -- INSTR('HELLO, ORACLE!', 'L', 5) AS INSTR2   -- HELLO, ORACLE! 의 5번째 글자 이후로 L이 몇번째에 있는지
	  -- INSTR('HELLO, ORACLE!', 'L', 2, 2) AS INSTR3 -- HELLO, ORACLE! 의 2번째 글자 이후로 2번째 L이 몇번째에 있는지

-- REPLACE(문자열, 찾는문자, 교체문자) : 문자 대체
SELECT '010-1234-5678' AS REPLACE_BEFORE,
	   REPLACE('010-1234-5678', '-', ' ') AS REPLACE_1;
       -- REPLACE('010-1234-5678', '-') AS REPLACE_2;  # ORACLE에선 가능
       
-- LPAD/RPAD (문자열, 데이터의 자릿수, 빈공간에 채울 문자)
SELECT 'ORACLE',
	   LPAD('ORACLE',10,'#') AS LPAD_1, -- ####ORACLE
       RPAD('ORACLE',10,'*') AS RPAD_1; -- ORACLE****
       -- LPAD('ORACLE',10) AS LPAD_2
	   -- RPAD('ORACLE',10) AS RPAD_2  # ORACLE에선 가능
       
-- CONCAT : 문자열 합치기
SELECT CONCAT(EMPNO,ENAME),            -- 7788SCOTT 
	   CONCAT(EMPNO,CONCAT(':',ENAME)) -- 7788:SCOTT
FROM   EMP
WHERE  ENAME = 'SCOTT';

/* ORACLE 
|| 연산자 : 열이나 문자열 연결
SELECT EMPNO || ENAME,
	   EMPNO || ':' || ENAME
FROM   EMP
WHERE  ENAME = 'SCOTT';
*/

-- TRIM(삭제옵션 삭제문자 FROM 원본)
 -- 삭제할 문자가 없을 때 : 공백 제거
SELECT CONCAT(CONCAT('[',TRIM(              ' _ORACLE_ ')),']')  AS TRIM,          -- [_ORACLE_]
	   CONCAT(CONCAT('[',TRIM(LEADING  FROM ' _ORACLE_ ')),']')  AS TRIM_LEADING,  -- [_ORACLE_ ]
       CONCAT(CONCAT('[',TRIM(TRAILING FROM ' _ORACLE_ ')),']')  AS TRIM_TRAILING, -- [ _ORACLE_]
       CONCAT(CONCAT('[',TRIM(BOTH     FROM ' _ORACLE_ ')),']')  AS TRIM_BOTH;     -- [_ORACLE_]

SELECT CONCAT(CONCAT('[',TRIM(         '_' FROM '_ _ORACLE_ _')),']')  AS TRIM,          -- [ _ORACLE_ ]
	   CONCAT(CONCAT('[',TRIM(LEADING  '_' FROM '_ _ORACLE_ _')),']')  AS TRIM_LEADING,  -- [ _ORACLE_ _]
       CONCAT(CONCAT('[',TRIM(TRAILING '_' FROM '_ _ORACLE_ _')),']')  AS TRIM_TRAILING, -- [_ _ORACLE_ ]
       CONCAT(CONCAT('[',TRIM(BOTH     '_' FROM '_ _ORACLE_ _')),']')  AS TRIM_BOTH;     -- [ _ORACLE_ ]

-- LTRIM/RTRIM (원본 문자열, 삭제할 문자)
SELECT CONCAT(CONCAT('[',      ' _ORACLE_ '        ),']') AS ORACLE,     -- [ _ORACLE_ ]
	   CONCAT(CONCAT('[',LTRIM(' _ORACLE_ '       )),']') AS LTRIM,      -- [_ORACLE_ ] 왼쪽 공백 삭제
       CONCAT(CONCAT('[',RTRIM(' _ORACLE_ '       )),']') AS RTRIM;      -- [ _ORACLE_] 오른쪽 공백 삭제
       -- CONCAT(CONCAT('[',LTRIM('<_ORACLE_>', '_<' )),']') AS LTRIM2,      # ORACLE
	   -- CONCAT(CONCAT('[',RTRIM('<_ORACLE_>', '>_' )),']') AS RTRIM2;      # ORACLE
       
-- ROUND(숫자, 반올림위치)
SELECT ROUND(1234.5678)     AS ROUND_,       -- 1235
	   ROUND(1234.5678,  0) AS ROUND_0,      -- 1235  
       ROUND(1234.5678,  1) AS ROUND_1,      -- 1234.6
       ROUND(1234.5678,  2) AS ROUND_2,      -- 1234.57
       ROUND(1234.5678, -1) AS ROUND_MINUS1, -- 1230
       ROUND(1234.5678, -2) AS ROUND_MINUS2; -- 1200
       
-- TRUNCATE(숫자, 버릴위치) *ORACLE: TRUNC
SELECT TRUNCATE(1234.5678,  0) AS TRUNCATE_0,      -- 1234
       TRUNCATE(1234.5678,  1) AS TRUNCATE_1,      -- 1234.5
       TRUNCATE(1234.5678,  2) AS TRUNCATE_2,      -- 1234.56
       TRUNCATE(1234.5678, -1) AS TRUNCATE_MINUS1, -- 1230
       TRUNCATE(1234.5678, -2) AS TRUNCATE_MINUS2; -- 1200
       
-- CEIL(숫자)  : 가장 가까운 큰 정수 출력
-- FLOOR(숫자) : 가장 가까운 작은 정수 출력
SELECT CEIL(3.14),   --  4
	   FLOOR(3.14),  --  3
       CEIL(-3.14),  -- -3
       FLOOR(-3.14); -- -4

-- MOD(나눠질 숫자, 나눌 숫자) : 나머지 출력
SELECT MOD(15,6), -- 3
	   MOD(10,2), -- 0
       MOD(11,2); -- 1
       
-- SYSDATE : 날짜 함수
/* ORACLE
SELECT SYSDATE AS NOW,
	   SYSDATE-1 AS YESTERDAY,
       SYSDATE+1 AS TOMORROW
FROM   DUAL;
*/
 -- MYSQL 
SELECT SYSDATE() AS NOW,
       DATE_SUB(SYSDATE(), INTERVAL 1 DAY) AS YESTERDAY,
       DATE_ADD(SYSDATE(), INTERVAL 1 DAY) AS TOMORROW;

-- DATE_ADD(날짜, 더할개월) : N개월 이후 날짜를 구하는 함수
/* ORACLE
SELECT SYSDATE(),
	   ADD_MONTHS(SYSDATE(),3);
*/

SELECT SYSDATE() AS now_date,
       DATE_ADD(SYSDATE(), INTERVAL 3 MONTH) AS plus_3_months;

SELECT EMPNO, ENAME, HIREDATE,
	   DATE_ADD(HIREDATE, INTERVAL 120 MONTH) AS WORK10YEAR
FROM   EMP;

SELECT EMPNO, ENAME, HIREDATE, SYSDATE()
FROM   EMP
WHERE  DATE_ADD(HIREDATE, INTERVAL 500 MONTH) > SYSDATE();

-- 두 날짜간 개월 수 차이
/* ORACLE
-- MONTHS_BETWEEN(날짜1, 날짜2) 
SELECT EMPNO, ENAME, HIREDATE, SYSDATE(),
       MONTHS_BETWEEN(HIREDATE,SYSDATE)
FROM   EMP;
*/
-- TIMESTAMPDIFF(MONTH, date1, date2)
SELECT EMPNO, ENAME, HIREDATE, SYSDATE(),
       TIMESTAMPDIFF(MONTH,HIREDATE,SYSDATE()) AS MONTHS1
FROM   EMP;

-- NEXT_DAY(날짜,요일) : ORACLE 해당요일의 가장 최근 일자
SELECT 
  SYSDATE() AS today,
  DATE_ADD(SYSDATE(), INTERVAL (8 - DAYOFWEEK(SYSDATE())) % 7 DAY) AS next_monday; -- 수식을 통해 계산
  -- DAYOFWEEK()는 MySQL 내장 함수 [일요일: 1, 월요일: 2, ..., 토요일: 7]

-- LAST_DAY(날짜) : 해당 달의 마지막 날짜
SELECT LAST_DAY(SYSDATE()) AS last_day_of_month;

/* ORACLE
-- ROUND 함수로 날짜 반올림
SELECT SYSDATE,
	   ROUND(SYSDATE, 'CC') AS FORMAT_CC,   -- CC  : 네자리 연도의 끝 두자리 기준으로
       ROUND(SYSDATE, 'YYY') AS FORMAT_YYY, -- YYY : 연 월 일 기준 
       ROUND(SYSDATE, 'Q') AS FORMAT_Q,     -- Q   : 각 분기 두번째 달의 16일 기준
       ROUND(SYSDATE, 'DDD') AS FORMAT_DDD, -- DDD : 해당 일 정오 기준
       ROUND(SYSDATE, 'HH') AS FORMAT_HH    -- HH  : 해당일 시간 기준
FROM   DUAL;

-- TRUNC 함수로 버림
*/

-- 자료형을 변환
-- DATE_FORMAT(날짜,문자형태) : 날짜 -> 문자  (ORACLE : TO_CHAR)
SELECT DATE_FORMAT(SYSDATE(), '%Y/%m/%d %H:%i:%s') AS '현재날짜시간';

-- FORMAT(숫자,소수점) : 숫자 -> 문자  
SELECT SAL,
       FORMAT(SAL, 0) AS SAL_$
FROM EMP;

-- NULL 처리함수
-- IFNULL(EX1, EX2) EX1가 NULL 이면 EX2 반환 (ORACLE은 NVL)
SELECT EMPNO, ENAME, SAL, COMM, SAL+COMM,
	   IFNULL(COMM,0), SAL+IFNULL(COMM,0)
FROM   EMP;

-- NVL2(EX1, EX2, EX3) EX1이 NULL이 아니면 EX2 반환 / NULL이면 EX3 반환
SELECT EMPNO, ENAME, COMM,
       IF(COMM IS NOT NULL, 'O', 'X'),
       IF(COMM IS NOT NULL, SAL*12+COMM, SAL*12) AS ANNSAL
FROM   EMP;

-- DECODE (EX1,
--         조건1, 반환할 결과1 ... ) # ORACLE

-- CASE EX1
--      WHEN [조건1] THEN [반환될 결과1]... 
SELECT EMPNO, ENAME, JOB, SAL,
	   CASE JOB
       WHEN 'MANAGER'  THEN SAL*1.1
       WHEN 'SALESMAN' THEN SAL*1.05
       WHEN 'ANALYST'  THEN SAL
       ELSE SAL*1.03   END  AS UPSAL
FROM   EMP;

SELECT EMPNO, ENAME, COMM,
	   CASE 
       WHEN COMM IS NULL THEN '해당사항없음'
       WHEN COMM = 0     THEN '수당 없음'
       WHEN COMM > 0     THEN CONCAT('수당 : ',COMM)
       END  AS COMM_TEXT
FROM   EMP;

-- 174P Q1
SELECT EMPNO,
	   RPAD(SUBSTR(EMPNO,1,2),4,"*") AS MASKING_EMPNO,
       ENAME,
	   RPAD(SUBSTR(ENAME,1,1),CHARACTER_LENGTH(ENAME),"*") AS MASKING_ENAME
FROM   EMP
WHERE  CHARACTER_LENGTH(ENAME) >= 5
AND    CHARACTER_LENGTH(ENAME) < 6;
-- Q2
SELECT EMPNO, ENAME, SAL,
	   TRUNCATE(SAL / 21.5, 2) AS DAY_PAY,
       ROUND(SAL/21.5/8, 1) AS TIME_PAY
FROM   EMP;

-- Q3
SELECT EMPNO, ENAME, HIREDATE,
	   DATE_ADD(HIREDATE, INTERVAL 3 MONTH) AS 'R_JOB',
       IFNULL(COMM,'N/A')
FROM   EMP;

SELECT EMPNO, ENAME, HIREDATE,
       DATE_FORMAT(
           DATE_ADD(
               ADDDATE(HIREDATE, INTERVAL 3 MONTH),
               INTERVAL (9 - DAYOFWEEK(ADDDATE(HIREDATE, INTERVAL 3 MONTH))) % 7 DAY
           ),
           '%Y-%m-%d'
       ) AS R_JOB,
       IFNULL(CAST(COMM AS CHAR), 'N/A') AS COMM
FROM EMP;

-- Q4
SELECT EMPNO, ENAME, MGR,
	   CASE 
          WHEN MGR IS NULL THEN '0000'
          WHEN SUBSTR(MGR, 1, 2) = '78' THEN '8888'
          WHEN SUBSTR(MGR, 1, 2) = '77' THEN '7777'
          WHEN SUBSTR(MGR, 1, 2) = '76' THEN '6666'
          WHEN SUBSTR(MGR, 1, 2) = '75' THEN '5555'
          ELSE cast(MGR as char(4)) 
	   END AS CHG_MGR
FROM   EMP;

--  ------------177P CHAPTER 7--------------
-- SUM : 합계
SELECT SUM(SAL)
FROM   EMP;

SELECT SUM(COMM) -- NULL은 제외
FROM   EMP; 

SELECT SUM(DISTINCT SAL), -- 중복 제외
	   SUM(ALL SAL),
       SUM(SAL)
FROM   EMP;

-- COUNT : 개수 
SELECT COUNT(*)  -- 사원 수(행 수) 출력
FROM   EMP;  

SELECT COUNT(*)
FROM   EMP
WHERE  DEPTNO = 30;

SELECT COUNT(DISTINCT SAL),
	   COUNT(ALL SAL),
       COUNT(SAL)
FROM   EMP;

SELECT COUNT(COMM)  -- NULL은 제외
FROM   EMP;

-- MAX / MIN : 최대 / 최소
SELECT MAX(SAL)
FROM   EMP
WHERE  DEPTNO = 10;

SELECT MIN(SAL)
FROM   EMP
WHERE  DEPTNO = 10;

SELECT MAX(HIREDATE)  -- 가장 최근
FROM   EMP
WHERE  DEPTNO = 20;

SELECT MIN(HIREDATE)  -- 가장 오래된
FROM   EMP
WHERE  DEPTNO = 20;

-- AVG : 평균
SELECT AVG(SAL)
FROM   EMP
WHERE  DEPTNO = 30;

SELECT AVG(DISTINCT SAL) -- 중복 제거
FROM   EMP
WHERE  DEPTNO = 30;

-- GROUP BY
SELECT   AVG(SAL), DEPTNO
FROM     EMP
GROUP BY DEPTNO;

SELECT   DEPTNO, JOB, AVG(SAL)
FROM     EMP
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO, JOB;

-- HAVING <-- GROUP BY 절에 조건 부여
SELECT   DEPTNO, JOB, AVG(SAL)
FROM     EMP
GROUP BY DEPTNO, JOB
HAVING   AVG(SAL) >= 2000  -- 출력할 조건
ORDER BY DEPTNO, JOB;

SELECT   DEPTNO, JOB, AVG(SAL)
FROM     EMP
WHERE    SAL <= 3000
GROUP BY DEPTNO, JOB
HAVING   AVG(SAL) >= 2000  -- 출력할 조건
ORDER BY DEPTNO, JOB;

SELECT   DEPTNO, JOB, AVG(SAL)
FROM     EMP
GROUP BY DEPTNO, JOB
HAVING   AVG(SAL) >= 500
ORDER BY DEPTNO, JOB;

-- ROLLUP
SELECT   DEPTNO, JOB, COUNT(*), MAX(SAL), SUM(SAL), AVG(SAL)
FROM     EMP
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO, JOB;

SELECT   DEPTNO, JOB, COUNT(*), MAX(SAL), SUM(SAL), AVG(SAL)
FROM     EMP
GROUP BY DEPTNO, JOB WITH ROLLUP;
# GROUP BY ROLLUP(DEPTNO, JOB); -- ORACLE 

-- 212P Q1
SELECT   DEPTNO,
         TRUNCATE(AVG(SAL),0) AS AVG_SAL,
         TRUNCATE(MAX(SAL),0) AS MAX_SAL,
         TRUNCATE(MIN(SAL),0) AS MIN_SAL,
         COUNT(SAL)           AS CNT
FROM     EMP
GROUP BY DEPTNO
ORDER BY DEPTNO DESC;

-- Q2
SELECT   JOB, COUNT(*)
FROM     EMP
GROUP BY JOB
HAVING   COUNT(*) >= 3;

-- Q3
SELECT   SUBSTR(HIREDATE,1,4) AS HIRE_YEAR,
		 DEPTNO,
		 COUNT(*) AS CNT
FROM     EMP
GROUP BY SUBSTR(HIREDATE,1,4), DEPTNO
ORDER BY SUBSTR(HIREDATE,1,4) DESC;

SELECT   DATE_FORMAT(HIREDATE, '%Y') AS HIRE_YEAR,
		 DEPTNO,
		 COUNT(*) AS CNT
FROM   	 EMP
GROUP BY DATE_FORMAT(HIREDATE, '%Y'), DEPTNO
ORDER BY DATE_FORMAT(HIREDATE, '%Y') DESC;

-- Q4
SELECT   IF(COMM IS NOT NULL, 'O' , 'X') AS EXIST_COMM,
		 COUNT(*)
FROM     EMP
GROUP BY IF(COMM IS NOT NULL, 'O' , 'X');

SELECT CASE WHEN COMM IS NULL THEN 'X'
			ELSE 'O'
		END AS EXIST_COMM,
       COUNT(*) AS CNT
  FROM EMP
GROUP BY CASE WHEN COMM IS NULL THEN 'X'
			ELSE 'O'
		 END ;

-- Q5
SELECT DEPTNO,
       DATE_FORMAT(HIREDATE, '%Y') AS HIRE_YEAR,
       COUNT(*) AS CNT,
       MAX(SAL) AS MAX_SAL,
       SUM(SAL) AS SUM_SAL,
       AVG(SAL) AS AVG_SAL
  FROM EMP
GROUP BY DEPTNO, DATE_FORMAT(HIREDATE, '%Y') WITH ROLLUP;







